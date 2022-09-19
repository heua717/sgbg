import { Routes, Route } from "react-router-dom";
import Main from "./pages/Main";
import CreateRoom from "./pages/room/CreateRoom";
import CreateRoomMore from "./pages/room/CreateRoomMore";

function Router() {
  return (
    <Routes>
      {/* 메인 */}
      <Route path="/" element={<Main />} />

      {/* 방 create */}
      <Route path="/meeting/create" element={<CreateRoom/>} />

      <Route path="/meeting/create/more" element={<CreateRoomMore/>} />
    </Routes>
  );
}

export default Router;
