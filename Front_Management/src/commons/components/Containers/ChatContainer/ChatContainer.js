import React, { useEffect, useState, useRef } from 'react';
import SockJS from 'sockjs-client';
// import { Stomp } from '@stomp/stompjs';
import { Client as StompClient } from '@stomp/stompjs';

import { Avatar } from '@material-ui/core'; 
import './ChatContainer.css';
// import ChatService from '../../../../services/ChatService';
import UserService from '../../../../services/UserService';
// import ConversationService from '../../../../services/ConversationService';

const ChatContainer = ({ userId }) => {
    // const [notifications, setNotifications] = useState([]);
    let [users, setUsers] = useState([]);

    let [recipientIdSelected, setRecipientIdSelected] = useState("");
    let [recipientNameSelected, setRecipientNameSelected] = useState("");
    let [sessionSelected, setSessionSelected] = useState("");
    
    let [messages, setMessages] = useState([]);
    // const [messages, setMessages] = useState({});
    let [inputMessage, setInputMessage] = useState("");
    
    let messagesRef = useRef([]);
    let [recipientTyping, setRecipientTyping] = useState(false);
    let stompClient = useRef(null);
    
    useEffect(() => {
        handleShowUsersList();

    }, [userId]);

    useEffect(() => {
        console.log("messages updated:", messages.length);
        console.log("messages:", messages);
        messagesRef.current = messages;
    }, [messages]);

    useEffect(() => {
        console.log("recipientNameSelected updated:", recipientNameSelected);
    }, [recipientNameSelected]);

    useEffect(() => {
        console.log("stompClientRef updated:", stompClient);
    }, [stompClient]);

    useEffect(() => {
        setMessages([]);
        if (sessionSelected) {
            if (stompClient.current) stompClient.current.deactivate();
            initializeWebSocket();
        }
        return () => {
            if (stompClient.current) stompClient.current.deactivate();
        };
    }, [sessionSelected]);

    const initializeWebSocket = () => {
        const client = new StompClient({
            brokerURL: `ws://localhost:8083/chat`,
            debug: (str) => console.log(str),
            onConnect: () => {
                console.log("Connected to WebSocket:", sessionSelected);
                stompClient.current = client;

                client.publish({
                    destination: "/app/chat",
                    body: JSON.stringify({
                        senderId: userId,
                        receiverId: `${recipientIdSelected}`,
                        message: "Connected",
                        messageType: "messageStatus",
                    }),
                });

                client.subscribe(
                    `/queue/messages/${sessionSelected}`,
                    (receivedMessage) => {
                        const parsedMessage = JSON.parse(receivedMessage.body);
                        console.log("Received message:", parsedMessage);

                        if(parsedMessage.messageType === 'messageStatus' && parsedMessage.message === 'Connected') {
                            if(messagesRef.current.find(
                                (msg) => msg.messageType === 'messageStatus' && msg.message === 'sent'
                            )) {  // este status sent in conversatie
                                cleanMessageStatus();
    
                                setMessages((prev) => [
                                    ...prev,
                                    {
                                        senderId: userId,
                                        receiverId: `${recipientIdSelected}`,
                                        message: "seen",
                                        messageType: "messageStatus",
                                    },
                                    parsedMessage,
                                ]);
                            } 
                            else {
                                setMessages((prev) => [...prev, parsedMessage]);
                            }
                        } else if (parsedMessage.messageType === 'messageTyping') {
                            if (parsedMessage.message === 'active') {
                                setRecipientTyping(true);
                            } else if (parsedMessage.message === 'inactive') {
                                setRecipientTyping(false)
                            }
                        } else if (parsedMessage.messageType === 'messageStatus' && parsedMessage.message === 'seen') {
                            cleanMessageStatus();
    
                            setMessages((prev) => [
                                ...prev,
                                {
                                    senderId: userId,
                                    receiverId: `${recipientIdSelected}`,
                                    message: "seen",
                                    messageType: "messageStatus",
                                },
                            ]);
                        } else {
                            client.publish({
                                destination: "/app/chat",
                                body: JSON.stringify({
                                    senderId: userId,
                                    receiverId: `${recipientIdSelected}`,
                                    message: "seen",
                                    messageType: "messageStatus",
                                }),
                            });

                            setMessages((prev) => [...prev, parsedMessage]);
                        }
                    }
                );
            },
            onDisconnect: () => {
                client.publish({
                    destination: "/app/chat",
                    body: JSON.stringify({
                        senderId: userId,
                        receiverId: `${recipientIdSelected}`,
                        message: "Disconnected",
                        messageType: "messageStatus",
                    }),
                });
                console.log("mesaj disconnect")
            },
            onUnhandledMessage: () => {

            },
            onStompError: (frame) => {
                console.error("STOMP Error:", frame.headers['message']);
            },
            reconnectDelay: 5000,
        });
        client.activate();
    };

    const handleShowUsersList = async () => {
        try {
            // Fetch the current user details
            const currentUser = await UserService.getUserById(userId);
    
            if (!currentUser || !currentUser.role) {
                throw new Error('Failed to fetch current user role.');
            }
    
            // Fetch all users
            const allUsers = await UserService.getAllUsers();
    
            let filteredUsers;
    
            if (currentUser.role === 'Administrator') {
                // If the current user is an Administrator, exclude themself from the user list
                filteredUsers = allUsers.filter(user => String(user.id) !== String(userId) && user.username && user.role);
            } else if (currentUser.role === 'Client') {
                // If the current user is a Client, include only Administrators
                filteredUsers = allUsers.filter(
                    user => String(user.id) !== String(userId) && user.role === 'Administrator' && user.username && user.role
                );
            } else {
                throw new Error('Unknown role.');
            }
            console.log("filteredUsers: ", filteredUsers)
            setUsers(filteredUsers);
        } catch (error) {
            alert('Failed to update user list.');
            console.error('Users update failed. Error: ', error);
        }
    };

    // const handleShowUsersList = async () => {
    //     try {
    //         const data = await UserService.getAllUsers();
    //         const filteredUsers = data.filter(user => user.username && user.role && user.id);
    //         setUsers(filteredUsers);
    //     } catch (error) {
    //         alert('Failed to update user list.');
    //         console.error('Users update failed. Error: ', error);
    //     }
    // }

    const connectSession = (userId2, userName2) => {
        if (recipientIdSelected !== userId2) {
            setRecipientIdSelected(userId2);
            setRecipientNameSelected(userName2);
            setSessionSelected(`${userId}and${userId2}`);
        } else {
            console.log("Already connected to this session.");
        }
    };

    const disconnectSession = () => {
        if (stompClient.current) {
            stompClient.current.deactivate();
            stompClient.current = null;
            setRecipientIdSelected('');
            setRecipientNameSelected('');
            setSessionSelected('');
        }
    };

    const sendMessage = () => {
        if (stompClient.current && inputMessage.trim()) {
            const payload = {
                senderId: userId,
                receiverId: `${recipientIdSelected}`,
                message: inputMessage.trim(),
                messageType: "messageText",
            };

            stompClient.current.publish({
                destination: "/app/chat",
                body: JSON.stringify(payload),
            });

            setMessages((prev) => [...prev, payload]);
            cleanMessageStatus();

            setMessages((prev) => [
                ...prev,
                {
                    senderId: userId,
                    receiverId: `${recipientIdSelected}`,
                    message: "sent",
                    messageType: "messageStatus",
                },
            ]);

            setInputMessage("");

            stompClient.current.publish({
                destination: "/app/chat",
                body: JSON.stringify({
                    senderId: userId,
                    receiverId: `${recipientIdSelected}`,
                    message: "inactive",
                    messageType: "messageTyping",
                }),
            });
        }
    };

    const cleanMessageStatus = () => {
        setMessages((prev) => prev.filter((msg) => msg.messageType !== "messageStatus"));
        console.log("Cleaned messageStatus messages.");
    };

    const handleInputChange = (e) => {
        const value = e.target.value;
    
        // Actualizează valoarea din input
        setInputMessage(value);
    
        // Trimite mesaj de typing
        if (stompClient.current) {
            if (value.trim() !== '') {
                // Trimite mesaj "active" dacă există text
                stompClient.current.publish({
                    destination: "/app/chat",
                    body: JSON.stringify({
                        senderId: userId,
                        receiverId: `${recipientIdSelected}`,
                        message: "active",
                        messageType: "messageTyping",
                    }),
                });
            } else {
                // Trimite mesaj "inactive" dacă input-ul este gol
                stompClient.current.publish({
                    destination: "/app/chat",
                    body: JSON.stringify({
                        senderId: userId,
                        receiverId: `${recipientIdSelected}`,
                        message: "inactive",
                        messageType: "messageTyping",
                    }),
                });
            }
        }
    };
    
    return (
        <div className='chat-container'>
            <h1>Users</h1>
            <div className='users-container'>
                {users.map(user => (
                    <div 
                        className={`user-container ${user.id === recipientIdSelected ? 'selected' : ''}`}
                        key={user.id}
                        onClick={() => connectSession(user.id, user.username)}> 
                        <div className="user-photo">
                            <Avatar>{user.username ? user.username.charAt(0) : '?'}</Avatar>
                        </div>
                        <div className="user-name">{user.username}</div>
                        <div className="user-role">{user.role}</div>
                    </div>
                ))}
            </div>            

            {sessionSelected === '' && <div className='conversation-selected'>No session connected.</div>}

            {sessionSelected !== '' && messages.length === 0 && (
                <>
                    <h3>Conversation with {recipientNameSelected ? recipientNameSelected : 'Unknown user'}</h3>
                    <div className='conversation-selected'>No messages in this session.</div>
                    <div className='conversation-typing'>{recipientTyping ? 'Typing' : ''}</div>
                </>
            )}

            {sessionSelected !== '' && messages.length > 0 && (
                <>
                    <h3>Conversation with {recipientNameSelected ? recipientNameSelected : 'Unknown user'}</h3>
                </>
            )}

            {sessionSelected !== '' && messages.length > 0 && (
                <div className='conversation-selected'>
                    <div className='conversation-view'>
                        {messages.map((msg, index) => {
                            // console.log("msg: ", msg)
                            if(msg.messageType === 'messageText') {
                                return (
                                    <div 
                                        key={index} 
                                        className={`message ${msg.senderId === userId ? 'message-sent' : 'message-received'}`}
                                    >
                                        <div className='user-details'>
                                            <div className='user-photo'>
                                                <Avatar>{msg.senderId === userId ? 'Y' : recipientNameSelected.charAt(0)}</Avatar>
                                            </div>
                                            <div className='user-name'>{msg.senderId === userId ? 'You' : recipientNameSelected}</div>
                                        </div>
                                        <div className='message-text'>{msg.message}</div>
                                    </div>
                                )
                            } else if (msg.messageType === 'messageStatus'){
                                let statusClass = '';

                                switch (msg.message) {
                                    case 'seen':
                                        statusClass = 'status-seen';
                                        break;
                                    case 'sent':
                                        statusClass = 'status-sent';
                                        break;
                                    case 'Connected':
                                        statusClass = 'status-connected';
                                        break;
                                    case 'Disconnected':
                                        statusClass = 'status-disconnected';
                                        break;
                                }

                                return (
                                    <div 
                                        key={index} 
                                        className={`status ${statusClass}`}
                                    >
                                        <div className='status-text'>{msg.message}</div>
                                    </div>
                                )
                            }
                            return null;
                        })}

                        <div className='conversation-typing'>{recipientTyping ? 'Typing' : ''}</div>
                    </div>
                </div>
            )}

            <input
                type="text"
                value={inputMessage}
                onChange={handleInputChange}
                placeholder="Type your message..."
            />
            <button onClick={() => sendMessage()}>Send Message</button>
        </div>
    );
};

export default ChatContainer;