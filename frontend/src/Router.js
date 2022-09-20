import { Routes, Route } from "react-router-dom";
import Main from "./pages/Main";
import Login from "./pages/Login";
import CreateRoom from "./pages/room/CreateRoom";
import CreateRoomCategory from "./pages/room/CreateRoomCategory";
import CreateRoomMore from "./pages/room/CreateRoomMore";
import ReadRoom from "./pages/room/ReadRoom";
import ProfileHistory from "./pages/mypage/ProfileHistory";
import Profile from "./pages/mypage/Profile";
import UserReview from "./pages/UserReview";
import Search from "./pages/search/Search";
import SearchResult from "./pages/search/SearchResult";
import Wallet from "./pages/Wallet";
import CreateRoomMap from "./pages/room/CreateRoomMap";


function Router() {
  return (
    <Routes>
      {/* 메인 */}
      <Route path="/" element={<Main />} />

      {/* 로그인 */}
      <Route path="/login" element={<Login />} />

      {/* 방 create */}
      <Route path="/meeting/create" element={<CreateRoom />} />

      {/* 방 create -- 카테고리 */}
      <Route path="/meeting/create/category" element={<CreateRoomCategory/>} />

      {/* 방 create -- 지도 */}
      <Route path="/meeting/create/location" element={<CreateRoomMap/>}

      {/* 방 create -- 상세정보 */}
      <Route path="/meeting/create/more" element={<CreateRoomMore/>} />

      {/* 방 read -- 추후 id 추가*/}
      <Route path="/meeting" element={<ReadRoom/>} />

      {/* 마아페이지(프로필) */}
      <Route path="/profile/:user_id" element={<Profile />} />

      {/* 마아페이지(성공 이력) */}
      <Route path="/profile/history/:user_id" element={<ProfileHistory />} />

      {/* 지갑 페이지 */}
      <Route path="/wallet" element={<Wallet />} />


      {/* 모임 완료 후 개인 평가 */}
      <Route path="/eval/:meeting_id" element={<UserReview />} />

      {/* 검색 페이지 */}
      <Route path="/search" element={<Search />} />

      {/* 검색 결과 페이지 */}
      <Route path="/search/result" element={<SearchResult />} />


    </Routes>
  );
}

export default Router;