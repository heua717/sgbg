import { api } from "./API";
import { roomMore } from "../util/room";


export const createRoom = (_params: roomMore) => {
  const url = 'room/create';
  return api.post(url, {params: _params});
}

export const readRoom = (_id:string) => {
  const url = 'room/' + _id;
  return api.get(url);
}

