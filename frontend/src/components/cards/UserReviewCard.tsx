import { getParticipantBadge } from "../../util/profile";

const UserReviewCard = () => {
  return (
    <div className="w-full flex flex-row justify-between items-center border rounded-lg p-2 mb-1">
      {/* 피평가자 정보 */}
      <div className="flex flex-row">
        {/* 사용자 뱃지 */}
        <div className="w-[2rem] h-[2rem] mr-2">
          <img
            className="w-full h-full"
            src={process.env.PUBLIC_URL + `/img/userBadge` + getParticipantBadge(60) + ".png"}
            alt="사용자 뱃지"
          />
        </div>
        <span className="font-light leading-loose">{`namm`}</span>
      </div>
      {/* 점수 버튼 */}
      <div>
        <button className="bg-gray-300 rounded-lg p-1 font-light text-xs mr-1">
          <span className="mr-1">😍</span>
          <span className="mr-1">최고에요!</span>
        </button>
        <button className="bg-gray-300 rounded-lg p-1 font-light text-xs mr-1">
          <span className="mr-1">😁</span>
          <span className="mr-1">좋아요!</span>
        </button>
        <button className="bg-gray-300 rounded-lg p-1 font-light text-xs mr-1">
          <span className="mr-1">😑</span>
          <span className="mr-1">별로에요</span>
        </button>
      </div>
    </div>
  );
};

export default UserReviewCard;
