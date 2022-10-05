import CategoriesBar from "../components/bars/CategoriesBar";
import BtnCreateRoom from "../components/buttons/BtnCreateRoom";
import MeetingCard from "../components/cards/MeetingCard";
import Logo from "../components/etc/Logo";
// import MeetingReviewModal from "../components/modals/MeetingReviewModal";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { getRoomList } from "../api/main";
import { roomMore } from "../util/room";


// const PAGE_SIZE = 10 * Math.ceil(visualViewport.width / 100)

const Main = () => {
  // axios 통신 0929 임지민
  const [mainRoomList, setMainRoomList] = useState<roomMore[]>([]);

  const fetchMainRoomList = async () => {
    // setFetching(true)

    await getRoomList("roomId, DESC").then(({ data }) => {
      console.log(data.roomListInfo);
      setMainRoomList(mainRoomList.concat(data.roomListInfo));
    });
  };

  // 화면에 띄우기 위한 임시 리스트
  useEffect(() => {
    // console.log(isFetching);
    fetchMainRoomList();
  }, []);

  return (
    <div>
      {/* 로고 */}
      <Logo />
      {/* 카테고리 */}
      <CategoriesBar />
      {/* 필터바 */}
      {/* <FilterBar /> */}
      {/* 모임리스트 */}
      <div className="w-per95 m-auto grid grid-cols-1 gap-1">
        {mainRoomList.map((room: roomMore) => (
          <Link to={`/meeting/${room.roomId}`} key={room.roomId}>
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
