import { combineReducers } from "redux";
import auth from "./auth";
import message from "./message";
import demand from "./demand";

export default combineReducers({
  auth,
  message,
  demand,
});