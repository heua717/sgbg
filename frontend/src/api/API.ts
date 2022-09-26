import axios from "axios";
import { defaultURL } from "./config";

export const api = axios.create({
  baseURL: defaultURL,
});

//Refresh token 만료 관련 처리 추가 예정
