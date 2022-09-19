import { Routes, Route } from "react-router-dom";
import Main from "./pages/Main";
import CreateRoom from "./pages/room/CreateRoom";
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
    </Routes>
  );
}

export default Router;
