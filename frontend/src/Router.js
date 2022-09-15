import { Routes, Route } from "react-router-dom";
import Main from "./pages/Main";
import CreateRoom from "./pages/room/CreateRoom";

function Router() {
  return (
    <Routes>
      {/* 메인 */}
      <Route path="/" element={<Main />} />

      {/* 방 create */}
      <Route path="/meeting/create" element={<CreateRoom/>} />
    </Routes>
  );
}

export default Router;
