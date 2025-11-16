import axios from 'axios';

const API_URL = 'http://localhost:8080/users';
const AUTHENTICATION_URL = 'http://localhost:8080/authentication';

const UserService = {
    getAuthHeaders() {
        const token = localStorage.getItem('authToken');
        return token ? { Authorization: `Bearer ${token}` } : {};
    },
    
    getAllUsers: async () => {
        try {
            const response = await axios.get(`${API_URL}/get-all`,{
                headers: UserService.getAuthHeaders()  
            });
            return response.data;
        } catch (error) {
            console.error('Error fetching users:', error);
            throw error;
        }
    },

    getUserById: async (userId) => {
        try {
            const response = await axios.get(`${API_URL}/${userId}`, {
                headers: UserService.getAuthHeaders()  
            });
            return response.data;
        } catch (error) {
            console.error('Error fetching users:', error);
            throw error;
        }
    },

    delete: async (userId) => {
        try {
            const response = await axios.delete(`${API_URL}/delete/${userId}`, {
                headers: UserService.getAuthHeaders()  
            });
            return response.data;
        } catch (error) {
            console.error('Error deleting user:', error);
            throw error;
        }
    },

    update: async (user) => {
        try {
            const response = await axios.put(`${API_URL}/update`, user, {
                headers: UserService.getAuthHeaders()  
            });
            return response.data;
        } catch (error) {
            console.error('Error updating user:', error);
            throw error;
        }
    },

    register: async (user) => {
        try {
            console.log("user", user)
            const response = await axios.post(`${API_URL}/register`, {
                username: user.username,
                password: user.password,
                role: user.role
            },{
                headers: UserService.getAuthHeaders()  
            });
            console.log("response: ", response);
            return response.data;
        } catch (error) {
            console.error('Error registering user:', error);
            throw error;
        }
    },

    authenticate : async (user) => {
        try {
            const response = await axios.post(`${AUTHENTICATION_URL}/login`, {
                username: user.username,
                password: user.password
            });
            console.log("response", response);
            return response;
        } catch (error) {
            console.error('Error authenticate in user:', error);
            throw error;
        }
    }

    // login: async (user) => {
    //     try {
    //         const response = await axios.post(`${API_URL}/login`, {
    //             username: user.username,
    //             password: user.password
    //         });
    //         return response.data;
    //     } catch (error) {
    //         console.error('Error logging in user:', error);
    //         throw error;
    //     }
    // }
};

export default UserService;
