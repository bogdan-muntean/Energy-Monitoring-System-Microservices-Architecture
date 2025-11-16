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
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  fontWeight: 'bold',
  backgroundColor: theme.palette.background.default,
}));

const UserListTable = ({ users = [], onDeleteUser, onEditUser }) => {
  return (
    <Box sx={{ flexGrow: 1, mt: 2 }}>
      <Typography variant="h6" component="div" sx={{ mb: 2 }}>
        User List
      </Typography>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <StyledTableCell align="center">Avatar</StyledTableCell>
              <StyledTableCell align="center">Name</StyledTableCell>
              <StyledTableCell align="center">UserID</StyledTableCell>
              <StyledTableCell align="center">Role</StyledTableCell>
              <StyledTableCell align="center">Actions</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {users && users.length > 0 ? (
              users.map(user => (
                <TableRow key={user.id}>
                  <TableCell align="center">
                    <Avatar>{user.username ? user.username.charAt(0) : '?'}</Avatar>
                  </TableCell>
                  <TableCell align="center">{user.username || "Unknown Name"}</TableCell>
                  <TableCell align="center">{user.id}</TableCell>
                  <TableCell align="center">{user.role || "Unknown Role"}</TableCell>
                  <TableCell align="center">
                    <IconButton edge="end" aria-label="edit" onClick={() => onEditUser(user)}>
                      <EditIcon />
                    </IconButton>
                    <IconButton aria-label="delete" onClick={() => onDeleteUser(user.id)}>
                      <DeleteIcon />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))
            ) : (
              <TableRow>
                <TableCell colSpan={5} align="center">
                  No users available
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default UserListTable;
