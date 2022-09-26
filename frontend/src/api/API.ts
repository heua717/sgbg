import axios from "axios";
import { defaultURL } from "./config";

export const api = axios.create({
  baseURL: defaultURL,
});
