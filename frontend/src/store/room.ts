import { atom } from "recoil";

type location = {
  id: string;
  name: string;
  x: string;
  y: string;
  road_address: string;
}

export interface room {
  title: string;
  category: string;
  minUser: number;
  maxUser: number;
  location: location;
  description: string;
}

export interface roomMore extends room {
  endDate: string;
  reservationDate: string;
  price: number;
  minAttituteScore: number;
}


export const inputRoomInfo = atom<roomMore>({
  key: 'inputRoomInfo',
  default: {
    title: '',
    category: '',
    minUser: 0,
    maxUser: 0,
    location: {
      id: '',
      name: '',
      x: '',
      y: '',
      road_address: '',
    },
    description: '',
    endDate: '',
    reservationDate: '',
    price: 0,
    minAttituteScore: 50
  },
})


