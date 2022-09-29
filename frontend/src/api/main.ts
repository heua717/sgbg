import { api } from "./API";

// type params = {
//   page? : number;
//   size? : number;
//   sort? : string;
// }

// 메인 페이지에서 모임 전체 
export const getRoomList = (pageNum:number, pageSize:number, sort: string) => {
  const url = `/room?page=${pageNum}&size=${pageSize}&sort=${sort}`;
  return api.get(url);
};
