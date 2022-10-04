import MeetingCard from "../cards/MeetingCard";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getMyPageHostList } from "../../api/profile";
import UnevalMeetingCard from "../cards/UnevalMeetingCard";
import MeetingReviewModal from "../modals/MeetingReviewModal";

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
  hostReview: boolean;
  memberReview: boolean;
};

const MeetingListHost = () => {
  const navigator = useNavigate();
  const [isVisibleModal, setIsVisibleModal] = useState<boolean>(false);
  const [selectedRoomId, setSelectedRoomId] = useState<number>(-1);
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
      {roomList.map((room) => {
        return room.hostReview && room.memberReview ? (
          <MeetingCard key={room.roomId} name="meetingListParticipant" room={room} />
        ) : (
          <UnevalMeetingCard key={room.roomId} name="meetingListParticipant" room={room} />
        );
      })}
      <MeetingReviewModal isVisible={isVisibleModal} setIsVisible={setIsVisibleModal} roomId={selectedRoomId} />
    </div>
  );
};

export default MeetingListHost;
