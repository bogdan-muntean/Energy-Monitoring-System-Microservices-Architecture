import React from "react"
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import PersonIcon from '@mui/icons-material/Person';
import "./UsernameInputField.css"

export default function UsernameInputField({ value, onChange, placeholder = "Enter your username" }) {
    return (
        <div className="username-input-field-container">
            <Box>
                <div className="input-container">
                    <PersonIcon className="icon"/>
                    <TextField
                        className="text-field"
                        value={value} 
                        onChange={onChange} 
                        placeholder={placeholder} 
                        variant="outlined" 
                    />
                </div>
            </Box>
        </div>
    )
}