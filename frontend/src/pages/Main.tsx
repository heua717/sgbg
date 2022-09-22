import CategoriesBar from "../components/bars/CategoriesBar";
import FilterBar from "../components/bars/FilterBar";
import BtnCreateRoom from "../components/buttons/BtnCreateRoom";
import MeetingCard from "../components/cards/MeetingCard";
import Logo from "../components/Logo";
import MeetingReviewModal from "../components/modals/MeetingReviewModal";


const Main = () => {
  let list = [0, 0, 0, 0, 0];
  return (
    <div>
      {/* 로고 */}
      <Logo />
      
      <div className="w-full h-20 my-3">
        <img
          className="max-h-20 m-auto"
          src={process.env.PUBLIC_URL + `/img/sgbg-logo.png`}
          alt="SgBg 로고"
        />
      </div>

      {/* 카테고리 */}
      <CategoriesBar />
      {/* 필터바 */}
      <FilterBar />
      {/* 모임리스트 */}
      <div className="w-per90 m-auto grid grid-cols-1 gap-1">
        {list.map(() => (
          <MeetingCard></MeetingCard>
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
