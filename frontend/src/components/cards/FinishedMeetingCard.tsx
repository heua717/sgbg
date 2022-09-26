import UserReviewResultCard from "./UserReviewResultCard";
import { faMapMarkerAlt } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";


const FinishedMeetingCard = () => {
  return (
    <div className="border rounded-lg p-2">
      {/* 미팅 기본 정보, 성공률 */}
      {/* 미팅 기본 정보 */}
      <div className="flex flex-col">
        {/* 카테고리, 위치 */}
        <div className="flex justify-between">
          <div>
            <span className="text-xs text-blue-200 font-bold border border-blue-200 border-1 py-0.5 px-1.5 rounded-xl mr-1">{`드로잉/사진/글`}</span>
            <span className="font-light text-xs ml-1">
              <FontAwesomeIcon icon={faMapMarkerAlt} className="text-xs mr-1" />
              {`서울시 강남구`}</span>
          </div>    
          <p className="font-light text-xs">{`2022.09.15`}</p>
        </div>
        {/* 미팅 제목 */}
        <span 
        className="font-semibold text-sm my-2">
          {`서해 을왕리 해수욕장으로 같이 출사가실 분 구해요 `}
        </span>
      </div>
      {/* 미팅 세부 정보 */}
      {/* flex flex-col justify-start items-end */}

      {/* 평가 정보 */}
      <div className="">
        <UserReviewResultCard />
        <div className="text-end">
          <p className="text-sm font-semibold">성공률 {`90`}%</p>
          <p className="text-xs">{`${10}명 중 ${9}명이 성공을 선택했습니다.`}</p>
        </div>
      </div>
      
      {/* 성공률 */}
  </div>
  );
};

export default FinishedMeetingCard;
