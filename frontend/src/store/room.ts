import { atom } from "recoil";

export interface room {
  title: string;
  category: string;
  minPerson: number;
  maxPerson: number;
  location: string;
  explanation: string;
}

export interface roomMore extends room {
  dueDate: Date;
  bookingDate: Date;
  cost: number;
  minAttituteNum: number;
}

const today = new Date();

export const inputRoomInfo = atom<roomMore>({
  key: 'inputRoomInfo',
  default: {
    title: '',
    category: '',
    minPerson: 0,
    maxPerson: 0,
    location: '',
    explanation: '',
    dueDate: today,
    bookingDate: today,
    cost: 0,
    minAttituteNum: 50
  },
})


