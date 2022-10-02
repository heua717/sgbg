import { api } from "./API";
// import { roomMore } from "../util/room";

type comments = {
  content: string;
  room_id: number
}

export const createComment = (data: comments) => {
  const url = "comment/create";
  return api.post(url, data);
};
