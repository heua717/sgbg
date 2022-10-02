import MeetingCard from "../cards/MeetingCard";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { getMyPageHostList } from "../../api/profile";

type room = {
  roomId: number;
  hostId: number;
  hostName: string;
  parentCategory: string;
  childCategory: string;
  title: string;
  maxUser: number;
  minUser: number;
  price: number;
  location: {
    locationId: string;
    name: string;
    latitude: string;
    hardness: string;
    roadAddress: string;
  };
  reservationDate: string;
  endDate: string;
  minMemberScore: number;
  description: string;
  members: [
    {
      kakaoId: string;
      name: string;
      email: string;
      hostScore: number;
      memberScore: number;
    }
  ];
};
const MeetingListHost = () => {

//   const room:roomMore = {
//     "roomId": '',
//     "title":"만든 모임",
//     "parentCategory":"기타",
//     "childCategory":"기타",
//     "minUser":2,
//     "maxUser":10,
//     "location":{
//         "locationId": "17573702",
//         "name": "코엑스",
//         "latitude": "127.05882784390123",
//         "hardness": "37.51266138067201",
//         "roadAddress":"서울 강남구"
//     },
//     "description":"ㅎㅎㅎㅎㅎ",
//     "endDate":"2022-09-30T15:21:00",
//     "reservationDate":"2022-10-01T15:21:00",
//     "price":20000,
//     "minMemberScore":50
// }
  const list = [0,0,0,0,0]
  
  const [roomList, setRoomList] = useState<room[]>([]);
  useEffect(() => {
    getMyPageHostList()
      .then(({ data }) => {
        setRoomList([...data.roomListInfo]);
      })
      .catch();
  }, []);

  return (
    <div className="w-full">
      {roomList.map((room) => (
        <MeetingCard key={room.roomId} name="meetingListParticipant" room={room} />
      ))}
    </div>
  );
};

export default MeetingListHost;
