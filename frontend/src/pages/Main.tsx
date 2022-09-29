import CategoriesBar from "../components/bars/CategoriesBar";
import FilterBar from "../components/bars/FilterBar";
import BtnCreateRoom from "../components/buttons/BtnCreateRoom";
import MeetingCard from "../components/cards/MeetingCard";
import Logo from "../components/etc/Logo";
import MeetingReviewModal from "../components/modals/MeetingReviewModal";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { getRoomList } from "../api/main";
import { roomMore } from "../util/room";

const Main = () => {
  const [mainRoomList, setMainRoomList] = useState<roomMore[]>([])
  // axios 통신 0929 임지민
  useEffect(()=> {

    getRoomList(1, 5, 'roomId,DESC').then((res) =>{
      // const list:object = res.data.roomListRes
      // console.log(list, typeof(list), typeof(mainRoomList));
      setMainRoomList(mainRoomList.concat(res.data.roomListRes))
    });

    console.log('mainroomlist', mainRoomList, typeof(mainRoomList));
    
  }, [])

  // 화면에 띄우기 위한 임시 리스트
  return (
    <div>
      {/* 로고 */}
      <Logo />
      {/* 카테고리 */}
      <CategoriesBar />
      {/* 필터바 */}
      <FilterBar />
      {/* 모임리스트 */}
      <div className="w-per95 m-auto grid grid-cols-1 gap-1">
        {mainRoomList.map((room:roomMore) => (
          <Link to="/meeting">
            {/* <p>{room.title}</p> */}
            <MeetingCard name="main" room={room}></MeetingCard>
          </Link>
        ))}
      </div>

      {/* 모임버튼 */}
      <div>
        <BtnCreateRoom />
      </div>

      {/* <MeetingReviewModal /> */}
    </div>
  );
};

export default Main;
