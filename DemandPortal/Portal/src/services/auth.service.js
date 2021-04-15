import axios from "axios";
import { products, columns, searchfields,SERVER_URL } from "../helpers/data";

const API_URL = [SERVER_URL.url]+"/api/auth/";

const register = (username, email, password,role) => {
  return axios.post(API_URL + "signup", {
    username,
    email,
    password,
    role,
  });
};

const login = (username, password) => {
  return axios
    .post(API_URL + "signin", {
      username,
      password,
    })
    .then((response) => {
      if (response.data.accessToken) {
        sessionStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    });
};

const logout = () => {
  sessionStorage.removeItem("user");
};

export default {
  register,
  login,
  logout,
};