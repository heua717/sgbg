import { api } from "./API";
// import { roomMore } from "../util/room";

type comments = {
  content: string;
  roomId: number;
}

export const createComment = (data: comments) => {
  const url = "comment/create";
  return api.post(url, data);
};

export const readComment = (roomId: number) =>{
  const url = `comment/${roomId}`
  return api.get(url)
}