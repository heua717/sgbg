import { api } from "./API";


export const getMypage = (user_id: string) => {
    const url = `/user/${user_id}`;
    return api.get(url);
};
