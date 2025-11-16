import React from 'react';
import { createBrowserRouter } from "react-router-dom";
import LoginPage from '../../pages/LoginPage/LoginPage';
import RegisterPage from '../../pages/RegisterPage/RegisterPage';
import AdministratorPage from '../../pages/AdministratorPage/AdministratorPage';
import ClientPage from '../../pages/ClientPage/ClientPage';
import ProtectedRoute from './ProtectedRoute'; 

const routes = [
    {
        path: "/",  
        element: <LoginPage/>
    },
    {
        path:"/register",
        element:<RegisterPage/>
    },
    {
        path: "/administrator/:userId",
        element: (
            <ProtectedRoute allowedRoles={['Administrator']}>
                <AdministratorPage/>
            </ProtectedRoute>
        )
    },
    {
        path: "/user/:userId",
        element: (
            <ProtectedRoute allowedRoles={['Client']}>
                <ClientPage/>
            </ProtectedRoute>
        )
    }
];

export const router = createBrowserRouter(routes);
