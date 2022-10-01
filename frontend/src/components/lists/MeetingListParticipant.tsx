import MeetingCard from "../cards/MeetingCard";
import { useEffect, useState } from "react";
import { getMyPageParticipantList } from "../../api/profile";

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

const MeetingListParticipant = () => {
  const [roomList, setRoomList] = useState<room[]>([]);
  useEffect(() => {
    getMyPageParticipantList()
        .then(({ data }) => {
          console.log(data);
          
        setRoomList([...data.roomListInfo]);
      })
      .catch();
  });
  return (
    <div className="w-full">
      {roomList.map((room) => (
        <MeetingCard key={room.roomId} name="meetingListParticipant" room={room} />
      ))}
    </div>
  );
};

export default MeetingListParticipant;
