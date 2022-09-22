import CategoriesBar from "../components/bars/CategoriesBar";
import FilterBar from "../components/bars/FilterBar";
import BtnCreateRoom from "../components/buttons/BtnCreateRoom";
import MeetingCard from "../components/cards/MeetingCard";
import Logo from "../components/Logo";


const Main = () => {
  let list = [0, 0, 0, 0, 0];
  return (
    <div>
      {/* 로고 */}
      <Logo />
      
      {/* 카테고리 */}
      <CategoriesBar />
      {/* 필터바 */}
      <FilterBar/>
      {/* 모임리스트 */}
      <div className="w-per90 m-auto grid grid-cols-1 gap-1">
        {
          list.map(() => <MeetingCard></MeetingCard>)
        }
      </div>
      {/* 모임버튼 */}
      <BtnCreateRoom/>
    </div>
  );
};

export default Main;
