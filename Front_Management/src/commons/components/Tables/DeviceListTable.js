import * as React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Checkbox from '@mui/material/Checkbox';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  fontWeight: 'bold',
  backgroundColor: theme.palette.background.default,
}));

const DeviceListTable = ({ devices = [], onDeleteDevice, onEditDevice }) => {
  const [selectedDevices, setSelectedDevices] = React.useState([]);

  const handleSelectDevice = (deviceId) => {
    setSelectedDevices((prevSelected) =>
      prevSelected.includes(deviceId)
        ? prevSelected.filter((id) => id !== deviceId)
        : [...prevSelected, deviceId]
    );
  };

  const handleDeleteSelectedDevices = () => {
    selectedDevices.forEach((deviceId) => onDeleteDevice(deviceId));
    setSelectedDevices([]); 
  };

  return (
    <Box sx={{ flexGrow: 1, mt: 2 }}>
      <Typography variant="h6" component="div" sx={{ mb: 2 }}>
        Device List
      </Typography>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <StyledTableCell align="center">Select</StyledTableCell>
              <StyledTableCell align="center">Avatar</StyledTableCell>
              <StyledTableCell align="center">Description</StyledTableCell>
              <StyledTableCell align="center">Address</StyledTableCell>
              <StyledTableCell align="center">UserID</StyledTableCell>
              <StyledTableCell align="center">Actions</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {devices && devices.length > 0 ? (
              devices.map((device) => (
                <TableRow key={device.id}>
                  <TableCell align="center">
                    <Checkbox
                      checked={selectedDevices.includes(device.id)}
                      onChange={() => handleSelectDevice(device.id)}
                    />
                  </TableCell>
                  <TableCell align="center">
                    <Avatar>{device.description ? device.description.charAt(0) : '?'}</Avatar>
                  </TableCell>
                  <TableCell align="center">{device.description || 'Unknown Description'}</TableCell>
                  <TableCell align="center">{device.address || 'Unknown Address'}</TableCell>
                  <TableCell align="center">{device.userId || 'Unknown ID'}</TableCell>
                  <TableCell align="center">
                    <IconButton edge="end" aria-label="edit" onClick={() => onEditDevice(device)}>
                      <EditIcon />
                    </IconButton>
                    <IconButton aria-label="delete" onClick={() => onDeleteDevice(device.id)}>
                      <DeleteIcon />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))
            ) : (
              <TableRow>
                <TableCell colSpan={6} align="center">
                  No devices available
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
      {selectedDevices.length > 0 && (
        <Button
          variant="contained"
          color="secondary"
          onClick={handleDeleteSelectedDevices}
          sx={{ mt: 2 }}
        >
          Delete Selected Devices
        </Button>
      )}
    </Box>
  );
};

export default DeviceListTable;
