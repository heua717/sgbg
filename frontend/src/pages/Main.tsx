import CategoriesBar from "../components/bars/CategoriesBar";
import FilterBar from "../components/bars/FilterBar";
import BtnCreateRoom from "../components/buttons/BtnCreateRoom";
import MeetingCard from "../components/cards/MeetingCard";
import Logo from "../components/etc/Logo";
// import MeetingReviewModal from "../components/modals/MeetingReviewModal";
import { Link } from "react-router-dom";
import { useCallback, useEffect, useState } from "react";
import { getRoomList } from "../api/main";
import { roomMore } from "../util/room";

// const PAGE_SIZE = 10 * Math.ceil(visualViewport.width / 100)

const Main = () => {
  // axios 통신 0929 임지민
  const [mainRoomList, setMainRoomList] = useState<roomMore[]>([])

  // 인피니트 스크롤 0930 임지민
  // 필요한 변수 설정
  const [ page, setPage ] = useState(1)
  const [ isFetching, setFetching ] = useState(false)
  const [ hasNextPage, setNextPage ] = useState(true)

  // 인피니트 스크롤 메인 함수
  useEffect(()=> {

    getRoomList(page, 5, 'roomId,DESC')
    .then((res) =>{
      // const list:object = res.data.roomListRes
      // console.log(list, typeof(list), typeof(mainRoomList));
      console.log(res.data);
      
      setMainRoomList(mainRoomList.concat(res.data.roomListRes))
      setPage(page+1)
      setFetching(false)
    });
    // console.log('mainroomlist', mainRoomList, typeof(mainRoomList));
  
  }, [page])

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
          <Link to={`/meeting/${room.roomId}`}>
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
