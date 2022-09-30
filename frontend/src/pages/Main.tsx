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
  const fetchMainRoomList = useCallback(async () => {
    const {data} = await getRoomList(page, 5, 'roomId,DESC')
    
    setMainRoomList(mainRoomList.concat(data.roomListRes))
    setPage(page+1)
    setNextPage(true)
    console.log("page=", page);
    
    // setFetching(false) // 이 부분 주석을 풀면 첫페이지도 렌더링이 안됨

  }, [page])

  // create될 때 스크롤 이벤트 추가
  useEffect(()=> {  
    const { scrollTop, offsetHeight } = document.documentElement
    const handleScroll = () => {
      if ( (window.innerHeight + Math.ceil(scrollTop)) >= offsetHeight + 100) {
        console.log('바닥이다');
        // 한번만 내려가도록 하기
        // 여기서 1,2초 정도 딜레이시키기
        console.log(isFetching, hasNextPage);
        
        setFetching(true)
      }
    }
    console.log('created');
    setFetching(true)
    console.log("setfetching");
    window.addEventListener('scroll', handleScroll)
    return () => window.removeEventListener('scroll', handleScroll)
  }, [])

  useEffect(() => {
    if (isFetching && hasNextPage) {
      console.log('fetchmainroomlist');
      fetchMainRoomList()
    }
    else if(!hasNextPage) setFetching(false)
  }, [isFetching] )

  
  return (
    <div>
      {/* 로고 */}
      <Logo />
      {/* 카테고리 */}
      <CategoriesBar />
      {/* 필터바 */}
      <FilterBar />
      {/* 모임리스트 */}
      {isFetching && (
        <div className="w-per95 m-auto grid grid-cols-1 gap-1">
          {mainRoomList.map((room:roomMore) => (
            <Link to={`/meeting/${room.roomId}`}>
              {/* <p>{room.title}</p> */}
              <MeetingCard name="main" room={room}></MeetingCard>
            </Link>
          ))}
        </div>
      )}

      {/* 모임버튼 */}
      <div>
        <BtnCreateRoom />
      </div>

      {/* <MeetingReviewModal /> */}
    </div>
  );
};

export default Main;
