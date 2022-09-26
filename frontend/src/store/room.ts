import { atom } from "recoil";

export interface room {
  title: string;
  category: string;
  minPerson: number;
  maxPerson: number;
  location: string;
  explanation: string;
}

export const inputRoomInfo = atom<room>({
  key: 'inputRoomInfo',
  default: {
    title: '',
    category: '',
    minPerson: 0,
    maxPerson: 0,
    location: '',
    explanation: ''
  },
})


