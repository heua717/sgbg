import { atom } from "recoil";
import { roomMore } from "../util/room";

export const inputRoomInfo = atom<roomMore>({
  key: 'inputRoomInfo',
  default: {
    title: '',
    parentCategory: '',
    childCategory: '',
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


