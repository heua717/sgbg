import { Routes, Route } from "react-router-dom";
import Main from "./pages/Main";

function Router() {
  return (
    <Routes>
      {/* 메인 */}
      <Route path="/" element={<Main />} />
    </Routes>
  );
}

export default Router;
