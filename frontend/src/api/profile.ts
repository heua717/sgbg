import { api } from "./API";


export const getMypage = (user_id: string) => {
    const url = `/user/${user_id}`;
    return api.get(url);
};

export const getMyPageParticipantList = () => {
    return api.get("/user/room?host=false");
}

export const getMyPageHostList = () => {
    return api.get("/user/room?host=true");

}