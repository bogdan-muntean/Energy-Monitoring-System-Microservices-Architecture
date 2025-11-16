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
    
    // let [messages, setMessages] = useState([]);
    const [messages, setMessages] = useState({});
    let [inputMessage, setInputMessage] = useState("");
    let stompClient = useRef(null);
    // let stompClientRef = useRef(null);
    // let [stompClient, setStompClient] = useState<StompClient>(null);

    // const stompClient = Stomp.over(function(){
    //     return new WebSocket('ws://localhost:8083/ws/chat')
    //   });

    // let webSocketURL = 'ws://localhost:8083/chat?userId=';
    
    useEffect(() => {
        handleShowUsersList();

    }, [userId]);

    // useEffect(() => {
    //     console.log("recipientIdSelected updated:", recipientIdSelected);
    // }, [recipientIdSelected]);

    useEffect(() => {
        console.log("recipientNameSelected updated:", recipientNameSelected);
    }, [recipientNameSelected]);

    useEffect(() => {
        console.log("stompClientRef updated:", stompClient);
    }, [stompClient]);

    useEffect(() => {
        console.log("sessionSelected updated:", sessionSelected);

        const client = new StompClient({
            brokerURL: `ws://localhost:8083/chat`,
            // brokerURL: `ws://localhost:8083/chat?userId=${userId}`,
            // connectHeaders: {
            //     userId: sessionSelected
            // },
            debug: (str) => console.log(str), // Debugging activat
            onConnect: () => {
                console.log("Connected to WebSocket");
                stompClient.current = client;
                console.log("stompClient.current: ", stompClient.current);
                console.log("stompClient: ", stompClient);

                client.subscribe(`/queue/messages/${sessionSelected}`, (message) => {
                    console.log("Received message:", JSON.parse(message.body));
                    // setMessages((prev) => [...prev, JSON.parse(message.body)]);
                });

                // client.subscribe(`/user/queue/messages`, (message) => {
                //     console.log("Received message:", JSON.parse(message.body));
                //     setMessages((prev) => [...prev, JSON.parse(message.body)]);
                // });
                let recipientIdSelectedValue = "" + recipientIdSelected;
                const payload = {
                    senderId: recipientIdSelectedValue,
                    receiverId: userId,
                    message: "test",
                    messageType: "messageText"
                };
                // const payload = {
                //     senderId: userId,
                //     receiverId: "" + recipientIdSelected,
                //     message: "test",
                //     messageType: 'messageText'
                // };
                console.log("stompClient:", stompClient.current)
                console.log("payload:", payload)
            
                stompClient.current.publish({
                    destination: "/app/chat",
                    body: JSON.stringify(payload),
                });
                
                client.publish({
                    destination: "/app/chat",
                    body: JSON.stringify(payload),
                });
                
            },
            onStompError: (frame) => {
                console.error("STOMP Error:", frame.headers['message']);
            },
            reconnectDelay: 5000, // Reîncercare la fiecare 5 secunde
        });
        client.activate();

    }, [sessionSelected]);

    const handleShowUsersList = async () => {
        try {
            // await ChatService.syncAllUsers();
            const data = await UserService.getAllUsers();
            console.log(data)
            const filteredUsers = data.filter(user => user.username && user.role && user.id);
            console.log(filteredUsers)
            setUsers(filteredUsers);
        } catch (error) {
            alert('Failed to update user list.');
            console.error('Users update failed. Error: ', error);
        }
    }

    const connectSession = (userId2, userName2) => {
        if(stompClient != null){
            if (recipientIdSelected === userId2) {
                console.log("Already connected to a session.");
                return;
            } else {
                console.log("A fost conectat initial la userul ", recipientIdSelected);
                disconnectSession(stompClient);

                setRecipientIdSelected(userId2);
                setRecipientNameSelected(userName2);
                setSessionSelected(`${userId}and${userId2}`);
            }
        } else {
            if (recipientIdSelected === "") {
                console.log("Prima conectare");
                setRecipientIdSelected(userId2);
                setRecipientNameSelected(userName2);
                setSessionSelected(`${userId}and${userId2}`);
            } else {
                console.log("A fost conectat initial la user ", recipientIdSelected);
            
                setRecipientIdSelected(userId2);
                setRecipientNameSelected(userName2);
                setSessionSelected(`${userId}and${userId2}`);
            }
        }
    };

    // const disconnectSession = () => {
    //     stompClient.unsubscribe();
    //     console.log("Disconnect from current session!");
    // }

    const disconnectSession = () => {
        if(stompClient != null) {
            stompClient = null;
            setRecipientIdSelected('');
            setRecipientNameSelected('');
            setSessionSelected('');
        }
    }

    const sendMessage = () => {
        let recipientIdSelectedValue = "" + recipientIdSelected;

            const payload = {
                senderId: recipientIdSelectedValue,
                receiverId: userId,
                message: "test",
                messageType: 'messageText'
            };

            // const payload = {
            //     senderId: userId,
            //     receiverId: "" + recipientIdSelected,
            //     message: inputMessage,
            //     messageType: 'messageText'
            // };
            console.log("stompClient:", stompClient.current)
            console.log("payload:", payload)
        
            stompClient.current.publish({
                destination: "/app/chat",
                body: JSON.stringify(payload),
            });
        // if (stompClient.current && inputMessage.trim()) {
        //     let recipientIdSelectedValue = "" + recipientIdSelected;

        //     const payload = {
        //         senderId: recipientIdSelectedValue,
        //         receiverId: userId,
        //         message: "test",
        //         messageType: 'messageText'
        //     };

        //     // const payload = {
        //     //     senderId: userId,
        //     //     receiverId: "" + recipientIdSelected,
        //     //     message: inputMessage,
        //     //     messageType: 'messageText'
        //     // };
        //     console.log("stompClient:", stompClient.current)
        //     console.log("payload:", payload)
        
        //     stompClient.current.publish({
        //         destination: "/app/chat",
        //         body: JSON.stringify(payload),
        //     });
        
        //     // Update UI immediately with the new message
        //     console.log("inputMessage: ", inputMessage);

        //     setMessages((prev) => [...prev, payload]);
        //     console.log("messages: ", messages);

        //     setInputMessage("");
        // }
    };

    const handleShowSessionMessages = async () => {

    }

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
                
                {/* {messages.map((msg, index) => (
                    <div key={index} className={msg.type === 'public' ? 'public' : 'private'}>
                        {msg.content}
                    </div>
                ))} */}
            </div>            

            {sessionSelected === '' && <div className='conversation-selected'>No session connected.</div>}

            {sessionSelected !== '' && messages.length == 0 && (
                <>
                    <h3>Conversation with {recipientNameSelected ? recipientNameSelected : 'Unknown user'}</h3>
                    <div className='conversation-selected'>No messages in this session.</div>
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
                        {messages.map((msg, index) => (
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
                                {/* <div className='message-timestamp'>01.04.2025 12:00:00</div> */}
                                <div className='message-text'>{msg.message}</div>
                            </div>
                        ))}
                    </div>
                </div>
            )}

            <input
                type="text"
                // value={message}
                onChange={(e) => setInputMessage(e.target.value)}
                placeholder="Type your message..."
            />
            <button onClick={() => sendMessage()}>Send Message</button>
            {/* <button onClick={}>Send Message</button> */}
        </div>
    );
};

export default ChatContainer;


{/* <h1>Conversations</h1>
            <div className='conversations-container'>

                <div className='conversation'>
                    <div className='user-photo'>
                        <Avatar>{users.username ? users.username.charAt(0) : '?'}</Avatar>
                    </div>
                    <div className='user-name'>Ioana</div>
                    <div className='notification'>2 messages</div>
                </div> 
                <div className='conversation'>
                    <div className='user-photo'>
                        <Avatar>{users.username ? users.username.charAt(0) : '?'}</Avatar>
                    </div>
                    <div className='user-name'>Andrei</div>
                    <div className='notification'></div>
                </div> 
            </div> */}

{/* <h3>Conversation with Andrei</h3>
            <div className='conversation-selected'>
                <div className='conversation-view'>
                    <div className='message message-sent'>
                        <div className='user-details'>
                            <div className='user-photo'>
                                <Avatar>{users.username ? users.username.charAt(0) : 'Y'}</Avatar>
                            </div>
                            <div className='user-name'>You</div>
                        </div>
                        <div className='message-timestamp'>01.04.2025 12:00:00</div>
                        <div className='message-text'>Test test test test test test. </div>
                    </div>
                    <div className='message message-received'>
                        <div className='user-details'>
                            <div className='user-photo'>
                                <Avatar>{users.username ? users.username.charAt(0) : 'A'}</Avatar>
                            </div>
                            <div className='user-name'>Andrei</div>
                        </div>
                        <div className='message-timestamp'>01.04.2025 13:00:00</div>
                        <div className='message-text'>
                            Test test test test test test. Test test test test test test. 
                            Test test test test test test. Test test test test test test. Test test test test test test. 
                            Test test test test test test. Test test test test test test. Test test test test test test. 
                            Test test test test test test. Test test test test test test. Test test test test test test. 
                            Test test test test test test. Test test test test test test. Test test test test test test. 
                            Test test test test test test. Test test test test test test. Test test test test test test. 
                            Test test test test test test
                        </div>
                    </div>
                </div>
            </div> */}

            // useEffect(() => {
            //     // setConversationSelected("2");
                
        
            //     handleShowUsersList();
        
            //     // stompClient.connect({}, () => {
            //     //     console.log("Connected to WebSocket Chat");
        
            //     //     // Abonare la mesaje publice
            //     //     // stompClient.subscribe('/topic/public', (message) => {
            //     //     //     setMessages((prev) => [...prev, { type: 'public', content: message.body }]);
            //     //     // });
        
            //     //     // Abonare la mesaje private
            //     //     // stompClient.subscribe(`/chat/${conversationSelected}`, (message) => {
            //     //     //     console.log(message.body);
        
            //     //     //     // setNotifications((prev) => [...prev, { content: message.body }]);
            //     //     // });
            //     // }, (error) => {
            //     //     console.error('WebSocket Chat connection error:', error);
            //     // });
        
            //     // return () => {
            //     //     // if (stompClient.connected) {
            //     //     //     stompClient.disconnect(() => {
            //     //     //         console.log("Disconnected from WebSocket Devices Alerts");
            //     //     //     });
            //     //     // }
            //     // };
            // }, [userId]);

// const connectSession = (userId2) => {
    //     if (recipientIdSelected == userId2) {
    //         console.log('Already connected to a session.');
    //         return;
    //     }

    //     if(recipientIdSelected == ""){
    //         setRecipientIdSelected(userId2);
    //         setSessionSelected(`${userId}and${userId2}`);

    //         // stompClient.connect({}, () => {
    //         //     console.log("Connected to WebSocket Chat");
    
    //         //     // Abonare la mesaje publice
    //         //     // stompClient.subscribe('/topic/public', (message) => {
    //         //     //     setMessages((prev) => [...prev, { type: 'public', content: message.body }]);
    //         //     // });
    
    //         //     // Abonare la mesaje private
    //         //     // stompClient.subscribe(`/chat/${conversationSelected}`, (message) => {
    //         //     //     console.log(message.body);
    
    //         //     //     // setNotifications((prev) => [...prev, { content: message.body }]);
    //         //     // });
    //         // }, (error) => {
    //         //     console.error('WebSocket Chat connection error:', error);
    //         // });
    
    //         // return () => {
    //         //     // if (stompClient.connected) {
    //         //     //     stompClient.disconnect(() => {
    //         //     //         console.log("Disconnected from WebSocket Devices Alerts");
    //         //     //     });
    //         //     // }
    //         // };

    //         // webSocketURL = `${webSocketURL}${sessionSelected}`;
    //         const stompClient = Stomp.over(function(){
    //             return new WebSocket(webSocketURL + `${userId}and${userId2}`)
    //           });

    //           stompClient.connect({}, () => {
    //             console.log("Connected to WebSocket Chat " + sessionSelected);
    
    //             // Abonare la mesaje publice
    //             // stompClient.subscribe('/topic/public', (message) => {
    //             //     setMessages((prev) => [...prev, { type: 'public', content: message.body }]);
    //             // });
    
    //             // Abonare la mesaje private
    //             // stompClient.subscribe(`/chat/${conversationSelected}`, (message) => {
    //             //     console.log(message.body);
    
    //             //     // setNotifications((prev) => [...prev, { content: message.body }]);
    //             // });
    //             stompClient.subscribe(`/chat?userId=${userId}and${recipientIdSelected}`, (msg) => {
    //                 setMessages((prevMessages) => [...prevMessages, JSON.parse(msg.body)]);
    //             });
    //         }, (error) => {
    //             console.error('WebSocket Chat ' + sessionSelected + ', connection error:', error);
    //         });

    //         console.log("recipientIdSelected ", recipientIdSelected)
    //         console.log("")
            
    //     } else {
    //         // stompClient.unsubscribe(`/chat?userId=${sessionSelected}`);
    //         // setRecipientIdSelected(userId2);
    //         // setSessionSelected(`${userId}and${recipientIdSelected}`);

    //         // stompClient.subscribe(`/chat?userId=${userId}and${recipientIdSelected}`, (msg) => {
    //         //     setMessages((prevMessages) => [...prevMessages, JSON.parse(msg.body)]);
    //         // });
    //     }
    // };