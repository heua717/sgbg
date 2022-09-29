import { atom } from "recoil";

export const auth = atom({
  key: "auth",
  default: {
    isLogined: false,
    userId: "",
  },
});
