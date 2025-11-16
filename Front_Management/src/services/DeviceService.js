import axios from 'axios';

const API_URL = "http://localhost:8081/devices";

const DeviceService = {

    getDevicesByUserId: async (userId) => {
        try {
            const response = await axios.get(`${API_URL}/user/${userId}`);
            console.log("API Response:", response.data);
            return response.data;
        } catch (error) {
            console.error("Error fetching devices for user ID:", userId, error);
            throw error;
        }
    },

    getAllDevices: async () => {
        const response = await axios.get(`${API_URL}/get-all`);
        return response.data;
    },

    add: async (device) => {
        const response = await axios.post(`${API_URL}/add-device`, device);
        return response.data;
    },

    delete: async (deviceId) => {
        const response = await axios.delete(`${API_URL}/delete/${deviceId}`);
        return response.data;
    },

    deleteDevicesByUserId: async (userId) => {
        try {
            const response = await axios.delete(`${API_URL}/user/${userId}/delete-all`);
            return response.data;
        } catch (error) {
            console.error("Error deleting devices for user ID:", userId, error);
            throw error;
        }
    },

    update: async (device) => {
        const response = await axios.put(`${API_URL}/update`, device);
        return response.data;
    },
};

export default DeviceService;
