import CategoriesBar from "../components/bars/CategoriesBar";
import FilterBar from "../components/bars/FilterBar";
import BtnCreateRoom from "../components/buttons/BtnCreateRoom";
import MeetingCard from "../components/cards/MeetingCard";
import Logo from "../components/etc/Logo";
import MeetingReviewModal from "../components/modals/MeetingReviewModal";
import { Link } from "react-router-dom";

const Main = () => {
  let list = [0, 0, 0, 0, 0];
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
        {list.map(() => (
          <Link to="/meeting">
            <MeetingCard></MeetingCard>
          </Link>
        ))}
      </div>

      {/* 모임버튼 */}
      <div>
        <BtnCreateRoom />
      </div>

      <MeetingReviewModal />
    </div>
  );
};

export default Main;