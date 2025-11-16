import React, { useState, useEffect } from 'react';
import DeviceService from '../../services/DeviceService';
import { List, ListItem, ListItemText, Button } from '@mui/material';
import { useParams, useNavigate } from 'react-router-dom';
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import './ClientPage.css'; 
import ChatContainer from '../../commons/components/Containers/ChatContainer/ChatContainer';

const ClientPage = () => {
    const { userId } = useParams();
    const navigate = useNavigate();
    const [devices, setDevices] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [alerts, setAlerts] = useState({});

    useEffect(() => {
        const fetchDevices = async () => {
            if (!userId) {
                console.warn("User ID is not valid.");
                setLoading(false);
                return;
            }
            try {
                const fetchedDevices = await DeviceService.getDevicesByUserId(userId);
                setDevices(fetchedDevices);
            } catch (err) {
                console.error('Error fetching devices:', err);
                setError("Failed to load devices.");
            } finally {
                setLoading(false);
            }
        };

        fetchDevices();
    }, [userId]);

    useEffect(() => {
        
        const stompClient = Stomp.over(function(){
            return new WebSocket('ws://localhost:8082/ws')
          });

        // Conectare la WebSocket
        stompClient.connect(
            {}, // Headers pentru conectare (dacă sunt necesare)
            (frame) => {
                console.log("Connected to WebSocket Devices Alerts");
                // console.log("Frame: ", frame);
                // stompClient.send("/app/connect", {}, userId);

                // Abonare la topic-ul '/alert'
                stompClient.subscribe(`/alert/${userId}`, (message) => {
                    console.log(message.body);
                    let regex = /(Device ID\s*[\d+])(.*)/;
                    const match = regex.exec(message.body);

                    if (match) {
                        const deviceId = match[1];
                        const alertMessage = match[2];

                        setAlerts((prevAlerts) => ({
                            ...prevAlerts,
                            [deviceId]: alertMessage, // Actualizează sau adaugă alerta pentru deviceId
                        }));
                    }
                });
            },
            (error) => {
                console.error("WebSocket Devices Alerts connection error:", error);
            }
        );

        // Cleanup: Deconectare la demontarea componentei
        return () => {
            // stompClient.disconnect(() => {
            //     console.log("Disconnected from WebSocket Devices Alerts");
            // });
        };
    }, []);

    const handleLogout = () => {
        localStorage.clear();
        navigate('/');
    };

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="client-page-container">
            <div className="device-list-container">
                <Button 
                    variant="contained" 
                    color="primary" 
                    onClick={handleLogout}
                    className="back-button"
                >
                    Logout
                </Button>

                <h1>Real-Time Alerts</h1>
                <div className="alert-container">
                    <ul>
                        {Object.entries(alerts).map(([deviceId, alertMessage]) => (
                            <li key={deviceId}>
                                <strong>{deviceId}:</strong> {alertMessage}
                            </li>
                        ))}
                    </ul>
                </div>

                <h1>Your Devices</h1>
                {devices.length === 0 ? (
                    <p>No associated devices found.</p>
                ) : (
                    <List>
                        {devices.map(device => (
                            <ListItem key={device.id}>
                                <ListItemText 
                                    primary={
                                        <>
                                            <strong>Device Description:</strong> {device.description || "Unknown description"}
                                        </>
                                    } 
                                    secondary={
                                        <>
                                            <span className="text-secondary">
                                                <strong>Device Address:</strong> {device.address || "Unknown address"}
                                            </span>
                                            <br />
                                            <span className="text-secondary">
                                                <strong>Max Consumption:</strong> {device.maxHourlyEnergyConsumption || "N/A"}
                                            </span>
                                        </>
                                    } 
                                />
                            </ListItem>
                        ))}
                    </List>
                )}
            </div>
            <div className='chat-app'>
                <ChatContainer userId = {userId}/>
            </div>
        </div>
    );
};

export default ClientPage;