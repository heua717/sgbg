import axios from "axios";
import { defaultURL, mapURL, KAKAO_REST_API_KEY } from "./config";

export const api = axios.create({
  baseURL: defaultURL,
});

export const mapApi = axios.create({
  baseURL: mapURL,
  headers : { 'Authorization': `KakaoAK ${KAKAO_REST_API_KEY}` }
})

