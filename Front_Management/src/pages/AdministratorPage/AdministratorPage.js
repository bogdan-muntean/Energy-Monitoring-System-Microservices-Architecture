import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import "./AdministratorPage.css"
import UserService from '../../services/UserService';
import DeviceService from '../../services/DeviceService';
import UserListTable from '../../commons/components/Tables/UserListTable'
import DeviceListTable from '../../commons/components/Tables/DeviceListTable'
import ChatContainer from '../../commons/components/Containers/ChatContainer/ChatContainer';

const Administrator = () => {
    const { userId } = useParams();
    const navigate = useNavigate();
    
    const [userIdSelected, setUserIdSelected] = useState('');
    const [username, setUsername] = useState('');
    const [userRole, setUserRole] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [users, setUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState(null);

    const [deviceId, setDeviceId] = useState('');
    const [description, setDescription] = useState('');
    const [address, setAddress] = useState('');
    const [maxHourlyEnergyConsumption, setMaxHourlyEnergyConsumption] = useState('');
    const [userIdForDevice, setUserIdForDevice] = useState('');
    const [devices, setDevices] = useState([]);
    const [selectedDevice, setSelectedDevice] = useState(null);

    // User functions
    const handleShowUserList = async () => {
        try {
            const response = await UserService.getAllUsers();
            const filteredUsers = response.filter(user => user.username && user.role && user.id);
            setUsers(filteredUsers);
        } catch (error) {
            console.error('Error fetching users', error);
        }
    };

    const handleAddUser = async () => {
        if (!username || !userRole || !userPassword) {
            alert('Please fill in all fields!');
            return;
        }
        const user = { username: username, role: userRole, password: userPassword };
        try {
            await UserService.register(user);
            setUsername('');
            setUserRole('');
            setUserPassword('');
            handleShowUserList();
            alert('User registered successfully!');
        } catch (error) {
            console.error('Error adding user', error);
        }
    };

    const handleDeleteUser = async (userIdSelected) => {
        try {
            await UserService.delete(userIdSelected);
            
            handleShowUserList();
            handleShowDeviceList();
            
            alert('User and associated devices deleted successfully!');
        } catch (error) {
            console.error('Error deleting user and associated devices:', error);
        }
    };

    const handleSelectUser = (user) => {
        setUserIdSelected(user.id);
        setUsername(user.username);
        setUserRole(user.role);
        setUserPassword('');
        setSelectedUser(user);
    };
    
    const handleUpdateUser = async () => {
        if (!userIdSelected || !userRole || !userPassword) {
            alert('Please fill in all fields for updating!');
            return;
        }
        const userToUpdate = {
            id: userIdSelected,
            username: username,
            role: userRole,
            password: userPassword
        };
        try {
            await UserService.update(userToUpdate);
            handleShowUserList();
            setUserIdSelected('');
            setUsername('');
            setUserRole('');
            setUserPassword('');
            setSelectedUser(null);
        } catch (error) {
            console.error('Error updating user: ', error);
        }
    };

    // Device functions
    const handleShowDeviceList = async () => {
        try {
            const response = await DeviceService.getAllDevices();
            const filteredDevices = response.filter(device => device.description && device.address && device.maxHourlyEnergyConsumption);
            setDevices(filteredDevices);
        } catch (error) {
            console.error('Error fetching devices', error);
        }
    };

    const handleAddDevice = async () => {
        if (!description || !address || !maxHourlyEnergyConsumption || !userIdForDevice) {
            alert('Please fill in all fields!');
            return;
        }
        const device = {
            description,
            address,
            maxHourlyEnergyConsumption,
            userIdSelected: userIdForDevice
        };

        try {
            const userExists = users.some(user => user.id+"" === userIdForDevice);
            console.log(users)
            if (!userExists) {
                alert('This user does not exist!');
                return;
            }

            await DeviceService.add(device);
            setDescription('');
            setAddress('');
            setMaxHourlyEnergyConsumption('');
            setUserIdForDevice('');
            handleShowDeviceList();
            alert('Device registered successfully');
        } catch (error) {
            console.error('Error adding device', error);
        }
    };

    const handleDeleteDevice = async (deviceId) => {
        try {
            await DeviceService.delete(deviceId);
            handleShowDeviceList();
        } catch (error) {
            console.error('Error deleting device', error);
        }
    };

    const handleSelectDevice = (device) => {
        setDeviceId(device.id);
        setDescription(device.description);
        setAddress(device.address);
        setMaxHourlyEnergyConsumption(device.maxHourlyEnergyConsumption);
        setUserIdForDevice(device.userIdSelected);
        setSelectedDevice(device);
    };

    const handleUpdateDevice = async () => {
        if (!description || !address || !maxHourlyEnergyConsumption || !userIdForDevice) {
            alert('Please fill in all fields for updating!');
            return;
        }
        const deviceToUpdate = {
            id: deviceId,
            description,
            address,
            maxHourlyEnergyConsumption,
            userIdSelected: userIdForDevice
        };
        try {
            await DeviceService.update(deviceToUpdate);
            handleShowDeviceList();
            setDeviceId('');
            setDescription('');
            setAddress('');
            setMaxHourlyEnergyConsumption('');
            setUserIdForDevice('');
            setSelectedDevice(null);
        } catch (error) {
            console.error('Error updating device: ', error);
        }
    };

    useEffect(() => {
        handleShowUserList();
        handleShowDeviceList();
    }, []);

    const handleLogout = () => {
        localStorage.clear();
        navigate('/');
    };

    return (
        <div className="administrator-container">
            <Button
                variant="contained"
                color="primary"
                onClick={handleLogout}
                style={{ position: 'absolute', left: '30px', top: '2px' }}
            >
                Logout
            </Button>
            
            <div className="management-container">
                <div className="management-section">
                    <h1>User Management</h1>
                    <div className="user-management">
                        <TextField label="Name" variant="outlined" value={username} onChange={(e) => setUsername(e.target.value)} />
                        <Select value={userRole} onChange={(e) => setUserRole(e.target.value)} displayEmpty variant="outlined">
                            <MenuItem value="" disabled>Select Role</MenuItem>
                            <MenuItem value="Administrator">Administrator</MenuItem>
                            <MenuItem value="Client">Client</MenuItem>
                        </Select>
                        <TextField label="Password" variant="outlined" type="password" onChange={(e) => setUserPassword(e.target.value)} />
                        <div className="buttons-container">
                            <Button variant="outlined" onClick={handleShowUserList}>Show all users</Button>
                            <Button variant="outlined" onClick={handleAddUser}>Add User</Button>
                            <Button variant="outlined" onClick={handleUpdateUser}>Update User</Button>
                        </div>
                    </div>
                    
                    <div className="list-container">
                        <UserListTable users={users} onDeleteUser={handleDeleteUser} onEditUser={handleSelectUser} />
                    </div>
                </div>

                <div className="management-section">
                    <h1>Device Management</h1>
                    <div className="device-management">
                        <TextField label="Description" variant="outlined" value={description} onChange={(e) => setDescription(e.target.value)} />
                        <TextField label="Address" variant="outlined" value={address} onChange={(e) => setAddress(e.target.value)} />
                        <TextField label="Max Hourly Energy Consumption" variant="outlined" value={maxHourlyEnergyConsumption} onChange={(e) => setMaxHourlyEnergyConsumption(e.target.value)} />
                        <TextField label="User ID" variant="outlined" value={userIdForDevice} onChange={(e) => setUserIdForDevice(e.target.value)} />
                        <div className="buttons-container">
                            <Button variant="outlined" onClick={handleAddDevice}>Add Device</Button>
                            <Button variant="outlined" onClick={handleUpdateDevice}>Update Device</Button>
                        </div>
                    </div>

                    <div className="list-container">
                        <DeviceListTable devices={devices} onDeleteDevice={handleDeleteDevice} onEditDevice={handleSelectDevice} />
                    </div>
                </div>
                <div className='management-section chat-app'>
                    <ChatContainer userId = {userId}/>
                </div>
            </div>
        </div>
    );
};

export default Administrator;