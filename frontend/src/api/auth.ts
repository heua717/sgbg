import { api } from "./API";

//로그인 Post
export const login = (_params: string) => {
  const url = `auth/login`;
  return api.get(url, { params: _params });
};
