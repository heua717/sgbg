import MeetingCard from "../../components/cards/MeetingCard";
import Logo from "../../components/etc/Logo";
import RoomTabs from "../../components/tabs/RoomTabs";
import { roomMore } from "../../util/room";

const ReadRoom = () => {
  const room:roomMore = {
    "title":"만든 모임",
    "parentCategory":"기타",
    "childCategory":"기타",
    "minUser":2,
    "maxUser":10,
    "location":{
        "locationId": "17573702",
        "name": "코엑스",
        "latitude": "127.05882784390123",
        "hardness": "37.51266138067201",
        "roadAddress":"서울 강남구"
    },
    "description":"ㅎㅎㅎㅎㅎ",
    "endDate":"2022-09-30T15:21:00",
    "reservationDate":"2022-10-01T15:21:00",
    "price":20000,
    "minMemberScore":50
}
  return (
    <div>
      <div>
        <Logo />
        <div className="px-3">
          <MeetingCard name="readRoom" room={room} />
          <button type="button" className="w-full text-center font-bold bg-yellow-100 rounded p-1">
            참여하기
          </button>
        </div>
      </div>
      {/* 탭 구현 */}
      <div>
        <RoomTabs />
      </div>
    </div>
  );
};

export default ReadRoom;
