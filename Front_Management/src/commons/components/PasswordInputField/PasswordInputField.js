import React from 'react';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import OutlinedInput from '@mui/material/OutlinedInput';
import EyeIcon from '@mui/icons-material/RemoveRedEye';
import EyeOffIcon from '@mui/icons-material/VisibilityOff';
import './PasswordInputField.css';

export default function PasswordInputField({ value, onChange, placeholder = "Enter your password" }) {
  const [isPasswordVisible, setIsPasswordVisible] = React.useState(false);

  const togglePasswordVisibility = () => {
    setIsPasswordVisible((prev) => !prev);
  };

  const preventDefault = (event) => {
    event.preventDefault();
  };

  return (
    <div className="password-input-field-container">
      <Box className="input-container">
        <FormControl className="password-form-control" variant="outlined">
          <InputLabel htmlFor="outlined-password-input">{placeholder}</InputLabel>
          <OutlinedInput
            id="outlined-password-input"
            type={isPasswordVisible ? 'text' : 'password'}
            value={value}
            onChange={onChange}
            endAdornment={
              <InputAdornment position="end">
                <IconButton
                  className="icon"
                  aria-label="toggle password visibility"
                  onClick={togglePasswordVisibility}
                  onMouseDown={preventDefault}
                  edge="end"
                >
                  {isPasswordVisible ? <EyeOffIcon /> : <EyeIcon />}
                </IconButton>
              </InputAdornment>
            }
            label={placeholder}
          />
        </FormControl>
      </Box>
    </div>
  );
}