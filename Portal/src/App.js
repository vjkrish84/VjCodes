import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Router, Switch, Route, Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import logo from './images/logo1.jpg';
import Login from "./components/login";
import Register from "./components/register";
import Home from "./components/Home";
import Landing from "./components/landing";
import DemandUser from "./components/DemandUser";
import DemandAdmin from "./components/DemandAdmin";

import { logout } from "./actions/auth";
import { clearMessage } from "./actions/messages";

import { history } from "./helpers/history";

const App = () => {

  const [showAdminBoard, setShowAdminBoard] = useState(false);

  const { user: currentUser } = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  useEffect(() => {
    history.listen((location) => {
      dispatch(clearMessage()); // clear message when changing location
    });
  }, [dispatch]);

  useEffect(() => {
    if (currentUser) {
      //setShowModeratorBoard(currentUser.roles.includes("ROLE_MODERATOR"));
      setShowAdminBoard(currentUser.roles.includes("DEMANDMGMT_ADMIN"));
    }
  }, [currentUser]);

  const logOut = () => {
    dispatch(logout());
  };

  return (
    <Router history={history}>
      <div>
        <nav className="navbar navbar-expand navbar-custom">         
         <left> <img        
        src={logo}
        alt="profile-img"
        className="profile-img-card-header"
      /></left> <Link to={"/"} className="navbar-brand">
            Demand Management
          </Link>
          <div className="navbar-nav mr-auto">           

            

            {showAdminBoard && (
              <li className="nav-item">
                <Link to={"/admin"} className="nav-link">
                  Admin
                </Link>
              </li>
            )}

            {currentUser && (
              <li className="nav-item">
                <Link to={"/user"} className="nav-link">
                  User
                </Link>
              </li>
            )}
          </div>

          {currentUser ? (
            <div className="navbar-nav ml-auto">

              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={logOut}>
                  LogOut
                </a>
              </li>
              {showAdminBoard && (
              <li className="nav-item">
                <Link to={"/register"} className="nav-link">
                  Sign Up
                </Link>
              </li>)}
            </div>
            
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link">
                  Login
                </Link>
              </li>
           
            </div>
          )}
        </nav>

        <div className="mt-3">
          <Switch>
            <Route exact path={["/", "/home"]} component={Home} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/landing" component={Landing} />
            <Route path="/user" component={DemandUser} />
            <Route path="/admin" component={DemandAdmin} />
          </Switch>
        </div>
      </div>
    </Router>
  );
};

export default App;