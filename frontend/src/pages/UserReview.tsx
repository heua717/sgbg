import UserReviewCard from "../components/cards/UserReviewCard";
import Logo from "../components/etc/Logo";

const UserReview = () => {
  const list = [0, 0, 0, 0, 0, 0];
  return (
    <div className="w-full h-full">
      <div className="h-[25vh] overflow-hidden">
        {/* 로고 */}
        <Logo />
        {/* 멘트 */}
        <div className="flex flex-col justify-center items-center">
          <span>{`[이색놀거리] 블루 하와이안 만들기 모임에서`}</span>
          <span>{`다른 사람들과 즐거우셨나요?`} </span>
          <span>{`의견을 남겨주세요! 🙂`}</span>
        </div>
        {/* 제출 버튼 */}
        <div className="flex justify-end px-2 mb-1">
          <button className=" bg-blue-300 text-white text-sm rounded-xl py-1 px-3">
            제출하기
          </button>
        </div>
      </div>
      {/* 참여자 내역 */}
      <div className="w-per95 h-[75vh] m-auto">
        {list.map(() => (
          <UserReviewCard />
        ))}
      </div>
    </div>
  );
};

export default UserReview;
