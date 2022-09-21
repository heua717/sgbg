import { useState } from "react";
import RoomInfoTab from "./RoomInfoTab";
import ParticipantListTab from "./ParticipantListTab";
import CommunityTab from "./CommunityTab";

const RoomTabs = () => {
    const data = [
        {
          id: 0,
          title: "모임 정보",
          component: <RoomInfoTab/>
        },
        {
          id: 1,
          title: "참여자 목록",
          component: <ParticipantListTab/>
        },
        {
          id: 2,
          title: "커뮤니티",
          component: <CommunityTab/>
        }
    ];
    const [index, setIndex] = useState(0);

    return (<div className="mt-6">
      {/* 탭 제목 부분 */}
        <ul className="grid grid-cols-3 mb-5">
            {data.map(item => (
                <li key={item.id}
                onClick={()=> setIndex(item.id)}
                className="text-center font-semibold"
                > {item.title}</li>
            ))}
        </ul>
        {/* 탭 내용 부분 */}
        {data.filter(item => index === item.id).map(item => (
            <div className="mx-5">{item.component}</div>
        ))}
        

    </div>);
};

export default RoomTabs;