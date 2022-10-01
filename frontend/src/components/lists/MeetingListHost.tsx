import MeetingCard from "../cards/MeetingCard";
import { roomMore } from "../../util/room";
import { Link } from "react-router-dom";


const MeetingListHost = () => {
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
  const list = [0,0,0,0,0]
  
  return (
    <div className="w-full">
      <Link to={`/meeting/${room.roomId}`}>
        {list.map((index) => (
          // <MeetingCard key={index} />
          <MeetingCard name="meetingListHost" room={room}/>
        ))}
      </Link>
    </div>
  );
};

export default MeetingListHost;
