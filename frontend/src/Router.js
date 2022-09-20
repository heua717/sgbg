import { Routes, Route } from "react-router-dom";
import Main from "./pages/Main";
import CreateRoom from "./pages/room/CreateRoom";
import CreateRoomCategory from "./pages/room/CreateRoomCategory";
import CreateRoomMore from "./pages/room/CreateRoomMore";
import ReadRoom from "./pages/room/ReadRoom";

import Profile from "./pages/mypage/Profile";

function Router() {
  return (
    <Routes>
      {/* 메인 */}
      <Route path="/" element={<Main />} />

      {/* 방 create */}
      <Route path="/meeting/create" element={<CreateRoom />} />

      {/* 마아페이지(프로필) */}
      <Route path="/profile/:userId" element={<Profile />} />

      {/* 방 create -- 카테고리 */}
      <Route path="/meeting/create/category" element={<CreateRoomCategory/>} />

      {/* 방 create -- 상세정보 */}
      <Route path="/meeting/create/more" element={<CreateRoomMore/>} />

      {/* 방 read */}
      <Route path="/meeting" element={<ReadRoom/>} />
    </Routes>
  );
}

export default Router;