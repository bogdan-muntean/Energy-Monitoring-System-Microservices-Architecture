// import React from 'react'
// import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
// import NavigationBar from './navigation-bar'
// import Home from './home/home';
// // import LoginContainer from './person/person-container'
// import AdministratorContainer from './administrator/administrator-container'
// // import ClientContainer from './client/client-container'

// import ErrorPage from './commons/errorhandling/error-page';
// import styles from './commons/styles/project-style.css';

// class App extends React.Component {


//     render() {

//         return (
//             <div className={styles.back}>
//             <Router>
//                 <div>
//                     <NavigationBar />
//                     <Switch>

//                         <Route
//                             exact
//                             path='/'
//                             render={() => <Home/>}
//                         />

//                         {/* <Route
//                             exact
//                             path='/person'
//                             render={() => <PersonContainer/>}
//                         /> */}

//                         {/* <Route
//                             exact
//                             path='/login'
//                             render={() => <LoginContainer/>}
//                         /> */}

//                         <Route
//                             exact
//                             path='/administrator'
//                             render={() => <AdministratorContainer/>}
//                         />

//                         {/* <Route
//                             exact
//                             path='/client'
//                             render={() => <ClientContainer/>}
//                         /> */}


//                         {/*Error*/}
//                         <Route
//                             exact
//                             path='/error'
//                             render={() => <ErrorPage/>}
//                         />

//                         <Route render={() =><ErrorPage/>} />
//                     </Switch>
//                 </div>
//             </Router>
//             </div>
//         )
//     };
// }

// export default App
