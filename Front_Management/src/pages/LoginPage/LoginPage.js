import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import UsernameInputField from '../../commons/components/UsernameInputField/UsernameInputField';
import PasswordInputField from '../../commons/components/PasswordInputField/PasswordInputField';
import Button from '@mui/material/Button';
import axios from '../../commons/components/axiosConfig';
import './LoginPage.css';
import UserService from '../../services/UserService';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    localStorage.setItem('role', '');
    localStorage.setItem('userId', '');

    const handleLogin = async () => {
        try {
            // const response = await UserService.login({ username, password });
            const response = await axios.post('http://localhost:8080/authentication/login', {
                name: username,
                password: password
              });

            // const { message, role, userId } = data;
            const { token, user } = response.data;
            const { role, id: userId } = user;

            alert("Autentificare reusita!");
            console.log("token =", token);

            if (token) {
                localStorage.setItem('authToken', token); 
            }

            localStorage.setItem('role', role);
            localStorage.setItem('userId', userId);

            if (role === 'Administrator') {
                navigate(`/administrator/${userId}`);
            } else if (role === 'Client') {
                navigate(`/user/${userId}`);
            }
        } catch (error) {
            alert('Login failed. Please check your credentials.');
            console.error('Login error:', error);
        }
    };

    const handleRegister = () => navigate('/register');

    return (
        <div className="login-page-container">
            <div className="login-input-container">
                <h2>Login Now</h2>
                <UsernameInputField
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <PasswordInputField
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <div className="button-group">
                    <Button variant="contained" onClick={handleLogin}>
                        Login
                    </Button>
                    <Button variant="contained" onClick={handleRegister}>
                        Register
                    </Button>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
