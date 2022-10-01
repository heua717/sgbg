import { roomMore } from "../../util/room";
import MeetingCard from "../cards/MeetingCard";
import { Link } from "react-router-dom";


const MeetingListParticipant = () => {
    const room:roomMore = {
        "title":"하이하이",
        "parentCategory":"야외활동",
        "childCategory":"놀이공원",
        "minUser":2,
        "maxUser":20,
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
                {list.map(() => 
                    <MeetingCard name="meetingListParticipant" room={room} />)
                }
            </Link>
        </div>
    );
};

export default MeetingListParticipant;