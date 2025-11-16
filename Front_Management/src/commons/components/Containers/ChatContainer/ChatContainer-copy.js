import React, { useEffect, useState, useRef } from 'react';
import SockJS from 'sockjs-client';
// import { Stomp } from '@stomp/stompjs';
import { Client as StompClient } from '@stomp/stompjs';

import { Avatar } from '@material-ui/core'; 
import './ChatContainer.css';
import ChatService from '../../../../services/ChatService';
// import ConversationService from '../../../../services/ConversationService';

const ChatContainer = ({ userId }) => {
    // const [notifications, setNotifications] = useState([]);
    let [users, setUsers] = useState([]);

    let [recipientIdSelected, setRecipientIdSelected] = useState("");
    let [recipientNameSelected, setRecipientNameSelected] = useState("");
    let [sessionSelected, setSessionSelected] = useState("");
    
    let [messages, setMessages] = useState([]);
    let [message, setMessage] = useState("");
    // let stompClientRef = useRef(null);

    // const stompClient = Stomp.over(function(){
    //     return new WebSocket('ws://localhost:8083/ws/chat')
    //   });

    // let webSocketURL = 'ws://localhost:8083/chat?userId=';
    
    useEffect(() => {
        handleShowUsersList();

        const stompClient = new StompClient({
            brokerURL: "ws://localhost:8083/chat",
            connectHeaders: {
                userId: "1and2" // ID-ul sesiunii
            },
            onConnect: () => {
                console.log("Connected to WebSocket");
                // Trimite un mesaj
                stompClient.publish({
                    destination: "/app/chat",
                    body: JSON.stringify({
                        senderId: "1",
                        message: "Salut",
                        messageType: "messageText"
                    })
                });
            }
        });

        stompClient.activate();

        // Handle browser tab or window close
        // const handleBeforeUnload = () => {
        //     if (stompClientRef.current) {
        //         stompClientRef.current.disconnect(() => {
        //             console.log("WebSocket disconnected before window unload.");
        //         });
        //     }
        // };

        // window.addEventListener("beforeunload", handleBeforeUnload);

        // return () => {
        //     window.removeEventListener("beforeunload", handleBeforeUnload);

        //     if (stompClientRef.current) {
        //         stompClientRef.current.disconnect(() => {
        //             console.log("WebSocket disconnected on component unmount.");
        //         });
        //     }
        // };
        // handleShowUsersList();

        // return () => {
        //     // if (stompClient.connected) {
        //     //     stompClient.disconnect(() => {
        //     //         console.log("Disconnected from WebSocket Devices Alerts");
        //     //     });
        //     // }
        // };
    }, [userId]);

    // useEffect(() => {
    //     console.log("recipientIdSelected updated:", recipientIdSelected);
    // }, [recipientIdSelected]);

    // useEffect(() => {
    //     console.log("recipientNameSelected updated:", recipientNameSelected);
    // }, [recipientNameSelected]);

    // useEffect(() => {
    //     console.log("stompClientRef updated:", stompClientRef);
    // }, [stompClientRef]);

    // useEffect(() => {
    //     console.log("sessionSelected updated:", sessionSelected);

    //     if (sessionSelected !== "") {
    //         const webSocketURL = `ws://localhost:8083/chat?userId=${sessionSelected}`;
    //         const stompClient = Stomp.over(() => new WebSocket(webSocketURL));
    
    //         stompClient.connect({}, () => {
    //             console.log("Connected to WebSocket Chat: " + sessionSelected);
    
    //             stompClient.subscribe(`/chat?userId=${sessionSelected}`, (msg) => {
    //                 setMessages((prevMessages) => [...prevMessages, JSON.parse(msg.body)]);
    //             });
    //         }, (error) => {
    //             console.error("WebSocket connection error:", error);
    //         });
    
    //         return () => {
    //             stompClient.disconnect(() => {
    //                 console.log("Disconnected from WebSocket Chat ", sessionSelected);
    //             });
    //         };
    //     }
    // }, [sessionSelected]);

    useEffect(() => {
        console.log("sessionSelected updated:", sessionSelected);

        // if (sessionSelected) {
        //     // if (stompClientRef.current) {
        //     //     stompClientRef.current.disconnect(() => {
        //     //     });
        //     //     console.log("Disconnected from previous WebSocket session.");
        //     // }

        //     const webSocketURL = `ws://localhost:8083/chat?userId=${sessionSelected}`;
        //     const stompClient = Stomp.over(() => new WebSocket(webSocketURL));
    
        //     console.log("1.Connected to WebSocket Chat ", sessionSelected);
        //     stompClient.connect({}, () => {
        //         alert("conexiune noua creata");

        //         stompClient.subscribe(`ws://localhost:8083/chat?userId=${sessionSelected}`, (msg) => {
        //             console.log("msg.body:", msg.body);
        //             // let regex = /(Device ID\s*[\d+])(.*)/;
        //             // const match = regex.exec(message.body);

        //             // if (match) {
        //             //     const deviceId = match[1];
        //             //     const alertMessage = match[2];

        //             //     setAlerts((prevAlerts) => ({
        //             //         ...prevAlerts,
        //             //         [deviceId]: alertMessage, // Actualizează sau adaugă alerta pentru deviceId
        //             //     }));
        //             // }
        //             setMessages((prevMessages) => [...prevMessages, JSON.parse(msg.body)]);
        //         });

        //         stompClientRef.current = stompClient;

        //         console.log("11.Connected:", stompClientRef.current?.connected);
        //         console.log("11.stompClientRef", stompClientRef)
        //         console.log("11.stompClientRef.current", stompClientRef.current)

        //         // const messagePayload = {
        //         //     senderId: userId,
        //         //     messageText: message,
        //         // };
        //         // stompClient.send(`/chat?userId=${sessionSelected}`, {}, JSON.stringify(messagePayload));

        //     }, (error) => {
        //         console.error("WebSocket connection error:", error);
        //         setRecipientIdSelected('');
        //         setRecipientNameSelected('');
        //         setSessionSelected('');
        //         stompClientRef.current = null;
        //     });

        //     // stompClientRef.current = stompClient;

        //     // const messagePayload = {
        //     //     senderId: userId,
        //     //     messageText: message,
        //     // };
        //     // stompClient.send(`/chat?userId=${sessionSelected}`, {}, JSON.stringify(messagePayload));
        
        //     console.log("1.Connected:", stompClientRef.current?.connected);
        //     console.log("1.stompClientRef", stompClientRef)
        //     console.log("1.stompClientRef.current", stompClientRef.current)

        //     // console.log("Subscribtie realizata la stompClientRef.current", stompClientRef.current);
        //     console.log("1.Subscribtie realizata la stompClientRef.current", stompClientRef.current);
        // }
    }, [sessionSelected]);

    const handleShowUsersList = async () => {
        try {
            await ChatService.syncAllUsers();
            const data = await ChatService.getAllUsers();
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
        if(stompClientRef.current != null){
            if (recipientIdSelected === userId2) {
                console.log("Already connected to a session.");
                return;
            } else {
                console.log("A fost conectat initial la userul ", recipientIdSelected);
                disconnectSession(stompClientRef);

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

        // if (recipientIdSelected === "") {
        //     setRecipientIdSelected(userId2); // Update recipient ID
        //     setSessionSelected(`${userId}and${userId2}`); // Update session
        // } else {
        //     console.log("Switching to a new session.");
        // }
    };

    // const disconnectSession = (stompClientRef) => {
    //     stompClientRef.current.unsubscribe();
    //     console.log("Disconnect from current session!");
    // }

    const disconnectSession = () => {
        if(stompClientRef != null) {
            stompClientRef = null;
            setRecipientIdSelected('');
            setRecipientNameSelected('');
            setSessionSelected('');
        }
    }

    const sendMessage = () => {
        if (!sessionSelected) {
            alert('No session selected.');
            return;
        }

        if(!message.trim()) {
            alert("Please enter a message");
            return;
        }

        const messagePayload = {
            senderId: userId,
            messageText: message,
        };

        if (sessionSelected && stompClientRef.current != null) {
            // if (stompClientRef.current) {
            //     stompClientRef.current.disconnect(() => {
            //         console.log("Disconnected from previous WebSocket session.");
            //     });
            // }

            // const webSocketURL = `ws://localhost:8083/chat?userId=${sessionSelected}`;
            // const stompClient = Stomp.over(() => new WebSocket(webSocketURL));
            // stompClientRef.current = stompClient;
            // console.log("stompClientRef", stompClientRef)
            // console.log("stompClientRef.current", stompClientRef.current)

            console.log("Connected to WebSocket Chat to send message", sessionSelected);
            // stompClient.send(`/chat?userId=${sessionSelected}`, {}, JSON.stringify(messagePayload));
            stompClientRef.current.send(`/chat?userId=${sessionSelected}`, {}, JSON.stringify(messagePayload));
            // stompClientRef.current.connect({}, () => {
            //     alert("conexiune noua creata");

            //     // const messagePayload = {
            //     //     senderId: userId,
            //     //     messageText: message,
            //     // };
            //     // stompClient.send(`/chat?userId=${sessionSelected}`, {}, JSON.stringify(messagePayload));

            // }, (error) => {
            //     console.error("WebSocket connection error:", error);
            //     setRecipientIdSelected('');
            //     setRecipientNameSelected('');
            //     setSessionSelected('');
            //     stompClientRef.current = null;
            // });
            // stompClientRef.current.connect({}, () => {
            //     alert("conexiune noua creata")
            //     setMessages((prevMessages) => [...prevMessages, messagePayload]);
            //     setMessage("");
            //     // stompClient.subscribe(`/chat?userId=${sessionSelected}`, (msg) => {
            //     //     setMessages((prevMessages) => [...prevMessages, JSON.parse(msg.body)]);
            //     // });

            // }, (error) => {
            //     alert("WebSocket connection error:", error);
            //     setRecipientIdSelected('');
            //     setRecipientNameSelected('');
            //     setSessionSelected('');
            //     stompClientRef.current = null;
            // });
        } else {
            setRecipientIdSelected('');
            setRecipientNameSelected('');
            setSessionSelected('');
            stompClientRef.current = null;
        }

        // if (stompClientRef.current) {
        //     stompClientRef.current.send(`/app/chat?userId=${sessionSelected}`, {}, JSON.stringify(messagePayload));
        //     setMessages((prevMessages) => [...prevMessages, messagePayload]);
        //     setMessage("");
        // } else {
        //     alert("WebSocket is not saved");
        //     console.error("Cannot send message. WebSocket is not saved.");
        // }

        // if (stompClientRef.current && stompClientRef.current.connected) {
        //     stompClientRef.current.send(`/app/chat?userId=${sessionSelected}`, {}, JSON.stringify(messagePayload));
        //     setMessages((prevMessages) => [...prevMessages, messagePayload]);
        //     setMessage("");
        // } else {
        //     alert("WebSocket is not connected. Please reconnect.");
        //     console.error("Cannot send message. WebSocket is not connected.");
        // }
        // stompClient.send(`/app/chat?userId=${userId}and${recipientIdSelected}`, {}, JSON.stringify(messagePayload));
        // setMessages((prevMessages) => [...prevMessages, messagePayload]);
        // setMessage('');

        // stompClient.send("/app/sendPublic", {}, message);
        // setMessage("");
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

            {/* 
            if sessionSelected == "" => <div>Not session connected</div>
            if sessionSelected != "" && messages == empty => <div>Without messages in this session</div>
            if sessionSelected == "" && messages != empty => <div>rend messages</div> Folosim useEffect pe
            messages pentru a se actualiza cand vin mesaje noi prin web socket
            */}

            {sessionSelected === '' && <div className='conversation-selected'>No session connected.</div>}

            {sessionSelected !== '' && messages.length === 0 && (
                <>
                    <h3>Conversation with {recipientNameSelected ? recipientNameSelected : 'Unknown user'}</h3>
                    <div className='conversation-selected'>No messages in this session.</div>
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
                                        <Avatar>{msg.senderId === userId ? 'You' : msg.senderId.charAt(0)}</Avatar>
                                    </div>
                                    <div className='user-name'>{msg.senderId === userId ? 'You' : msg.senderId}</div>
                                </div>
                                {/* <div className='message-timestamp'>01.04.2025 12:00:00</div> */}
                                <div className='message-text'>{msg.messageText}</div>
                            </div>
                        ))}
                    </div>
                </div>
            )}

            <input
                type="text"
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                placeholder="Type your message..."
            />
            <button onClick={() => sendMessage()}>Send Message</button>
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