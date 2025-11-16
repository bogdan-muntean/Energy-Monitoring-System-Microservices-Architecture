import axios from 'axios';

const API_URL = 'http://localhost:8080/users';
// const API_URL = 'http://localhost:8083/users';
const WEB_SOCKET_URL = 'ws://localhost:8083/chat';

const ChatService = {
    getAuthHeaders() {
        const token = localStorage.getItem('authToken');
        return token ? { Authorization: `Bearer ${token}` } : {};
    },
    getAllUsers: async () => {
        try {
            const response = await axios.get(`${API_URL}/get-all`);
            return response.data;
        } catch (error) {
            console.error('Error fetching users:', error);
            throw error;
        }
    },
    // syncAllUsers: async (token) => {
    //     try {
    //         const response = await axios.post(`${API_URL}/sync-all-users`,null,{
    //             headers: ChatService.getAuthHeaders()  
    //         });
    //         return response.data;
    //     } catch (error) {
    //         console.error('Error fetching users:', error);
    //         throw error;
    //     }
    // },
    // connectToSession: async (userId1, userId2) => {
    //     try {
    //         const webSocket = WEB_SOCKET_URL + '?userId=' + userId1 + 'and' + userId2; 
    //         const response = await axios.post(`${API_URL}/sync-all-users`);
    //         return response.data;
    //     } catch (error) {
    //         console.error('Error fetching users:', error);
    //         throw error;
    //     }
    // }
}

export default ChatService;