// import React, { useState, useEffect } from 'react';
// import logo from './commons/images/icon.png';
// // import { useNavigate } from 'react-router-dom';
// import { useHistory } from "react-router-dom";
// import {
//     Button,
//     DropdownItem,
//     DropdownMenu,
//     DropdownToggle,
//     Nav,
//     Navbar,
//     NavbarBrand,
//     NavLink,
//     UncontrolledDropdown
// } from 'reactstrap';

// const textStyle = {
//     color: 'white',
//     textDecoration: 'none'
// };

// const NavigationBar = () => {
//     const [isAuthenticated, setIsAuthenticated] = useState(false);
//     const [userRole, setUserRole] = useState(null);

//     useEffect(() => {
//         const token = localStorage.getItem('token'); // Exemplu pentru token
//         const role = localStorage.getItem('role');   // Exemplu pentru rolul utilizatorului

//         setIsAuthenticated(!!token); // Se autentifică dacă există un token
//         setUserRole(role); // Setează rolul utilizatorului
//     }, []);

//     const handleLogout = () => {
//         localStorage.removeItem('token');
//         localStorage.removeItem('role');
//         setIsAuthenticated(false);
//         setUserRole(null);
//         window.location.href = "/"; // Opțional, redirecționează utilizatorul la pagina de start
//     };

//     // TODO:
//     // const history = useHistory();

//     // Funcția pentru a naviga înapoi la pagina anterioară
//     const handleBack = () => {
//     //     history.goBack();
//     };

//     return (
//         <div>
//             <Navbar color="dark" light expand="md">
//                 <NavbarBrand href="/">
//                     <img src={logo} width={"50"} height={"35"} alt="Logo" />
//                 </NavbarBrand>
//                 <Nav className="mr-auto" navbar>
//                     <UncontrolledDropdown nav inNavbar>
//                         <DropdownToggle style={textStyle} nav caret>
//                             Menu
//                         </DropdownToggle>
//                         <DropdownMenu right>
//                             <DropdownItem>
//                                 <NavLink href="/administrator">Admin</NavLink>
//                             </DropdownItem>
//                             {/* <DropdownItem>
//                                 <NavLink href="/admin/users">Admin - Users</NavLink>
//                             </DropdownItem>
//                             <DropdownItem>
//                                 <NavLink href="/admin/devices">Admin - Devices</NavLink>
//                             </DropdownItem>
//                             <DropdownItem>
//                                 <NavLink href="/client/devices">Client - Devices</NavLink>
//                             </DropdownItem> */}
//                         </DropdownMenu>
//                     </UncontrolledDropdown>
//                 </Nav>

//                 <Button color="secondary" onClick={handleBack} style={{ marginLeft: 'auto' }}>
//                     Back
//                 </Button>
//             </Navbar>
//         </div>
//     );
// };

// export default NavigationBar


// // return (
// //     <div>
// //         <Navbar color="dark" light expand="md">
// //             <NavbarBrand href="/">
// //                 <img src={logo} width={"50"} height={"35"} alt="Logo" />
// //             </NavbarBrand>
// //             <Nav className="mr-auto" navbar>
// //                 <UncontrolledDropdown nav inNavbar>
// //                     <DropdownToggle style={textStyle} nav caret>
// //                         Menu
// //                     </DropdownToggle>
// //                     <DropdownMenu right>
// //                         {!isAuthenticated ? (
// //                             // Cazul în care utilizatorul nu este autentificat
// //                             <DropdownItem>
// //                                 <NavLink href="/login">Login</NavLink>
// //                             </DropdownItem>
// //                         ) : userRole === 'admin' ? (
// //                             // Cazul în care utilizatorul este admin
// //                             <>
// //                                 <DropdownItem>
// //                                     <NavLink href="/users">Users</NavLink>
// //                                 </DropdownItem>
// //                                 <DropdownItem>
// //                                     <NavLink href="/devices">Devices</NavLink>
// //                                 </DropdownItem>
// //                                 <DropdownItem>
// //                                     <NavLink href="/admin/users">Admin - Users</NavLink>
// //                                 </DropdownItem>
// //                                 <DropdownItem>
// //                                     <NavLink href="/admin/devices">Admin - Devices</NavLink>
// //                                 </DropdownItem>
// //                                 <DropdownItem>
// //                                     <NavLink href="/client/devices">Client - Devices</NavLink>
// //                                 </DropdownItem>
// //                             </>
// //                         ) : userRole === 'client' ? (
// //                             // Cazul în care utilizatorul este client
// //                             <DropdownItem>
// //                                 <NavLink href="/devices">Devices</NavLink>
// //                             </DropdownItem>
// //                         ) : null}
// //                     </DropdownMenu>
// //                 </UncontrolledDropdown>
// //             </Nav>

// //             {isAuthenticated && (
// //                 <Button color="secondary" onClick={handleLogout} style={{ marginLeft: 'auto' }}>
// //                     Logout
// //                 </Button>
// //             )}
// //         </Navbar>
// //     </div>
// // );