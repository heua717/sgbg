import UserReviewResultCard from "./UserReviewResultCard";

const FinishedMeetingCard = () => {
  return (
    <div className="w-full h-full flex flex-col border rounded-lg p-2">
      {/* 미팅 기본 정보, 성공률 */}
      <div className="w-full h-full grid grid-cols-2">
        {/* 미팅 기본 정보 */}
        <div className="flex flex-col">
          {/* 카테고리, 위치 */}
          <div>
            <span className="font-semibold text-sm mr-1">{`방탈출`}</span>
            <span className="font-light text-xs">{`서울시 강남구`}</span>
          </div>
          {/* 미팅 제목 */}
          <span className="font-semibold text-xs">{`싱글벙글 벙글빵글 빵글싱글`}</span>
        </div>
        {/* 성공률 */}
        <div className="flex flex-col justify-end items-end">
          <span className="text-sm">성공률 {`90`}%</span>
          <span className="font-extralight text-[10px]">{`${10}명 중 ${9}명이 성공을 선택했습니다.`}</span>
        </div>
      </div>
      {/* 미팅 세부 정보 */}
      <div className="flex flex-col mt-1">
        <span className="font-light text-xs">{`2022.09.15`}</span>
        <span className="font-light text-xs">{`50,000원`}</span>
      </div>
      {/* 평가 정보 */}
      <div className="self-end">
        <UserReviewResultCard />
      </div>
    </div>
  );
};

export default FinishedMeetingCard;
