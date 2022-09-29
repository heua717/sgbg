import { api } from "./API";
import { roomMore } from "../util/room";

export const createRoom = (data: roomMore) => {
  const url = "room/create";
  return api.post(url, data);
};

export const readRoom = (_id: string) => {
  const url = "room/" + _id;
  return api.get(url);
};
