import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import Button from '@mui/material/Button';
import UserService from '../../services/UserService'; 
import UsernameInputField from '../../commons/components/UsernameInputField/UsernameInputField';
import PasswordInputField from '../../commons/components/PasswordInputField/PasswordInputField';
import "./RegisterPage.css";

const RegisterPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('');
    const navigate = useNavigate();

    const handleRegister = async () => {
        if (!username || !password || !role) {
            alert('Please fill in all fields!');
            return;
        }

        try {
            const data = await UserService.register({
                username: username,
                password: password,
                role: role
            });
            alert(data);

            navigate('/'); 
        } catch (error) {
            alert('Registration failed. Please try again.');
            console.error('Error during registration:', error);
        }
    };

    return (
        <div className="register-container"> 
            <div className="register-form">
                <h2>Create New Account</h2>
                <UsernameInputField
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <PasswordInputField
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <Select 
                    className="select-role"
                    value={role} 
                    onChange={(e) => setRole(e.target.value)} 
                    displayEmpty 
                    variant="outlined"
                    fullWidth
                >
                    <MenuItem value="" disabled>Select role</MenuItem>
                    <MenuItem value="Administrator">Administrator</MenuItem>
                    <MenuItem value="Client">Client</MenuItem>
                </Select>
                <Button 
                    variant="contained" 
                    color="primary" 
                    onClick={handleRegister}
                >
                    Register
                </Button>
            </div>
        </div>
    );
}

export default RegisterPage;