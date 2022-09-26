import { faMapMarkerAlt } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const MeetingCard = (props:any): JSX.Element => {
  const getStateBadgeColor = () => {
    return 'bg-yellow-100';
  }
  const getMemberBadgeColor = () => {
    return 'bg-gray-100';
  }
  return (
    // w-per100 h-per100 
    <div className="flex flex-row border rounded-lg p-2 my-2">
      {/* 모임 이미지, 뱃지 */}
      <div className="w-per45 mr-5 relative">
        <div className={`absolute top-0 left-0 rounded ${getStateBadgeColor()} text-sm font-semibold px-2 py-1`}>{`마감 임박`}</div>
        <img
          className="w-full h-full rounded"
          src={process.env.PUBLIC_URL + `/img/이색놀거리_방탈출.png`}
          alt="소분류 카테고리 사진"></img>

        <div className={`absolute bottom-0 right-0 rounded ${getMemberBadgeColor()} text-sm font-semibold px-2`}>{`${3}/${5}`}</div>
      </div>
      {/* 모임 설명 */}
      <div className="w-per55 flex flex-col justify-between"> {/* 설명 부분 전체를 묶는 div */}

        {/* 카테고리, 모임명, 위치 */}
        {/* 설명 1,2번째 줄을 묶는 div */}
        <div>
          <div className="flex justify-between">
            <span 
              className="text-xs text-blue-200 font-bold border border-blue-200 border-1 py-0.5 px-1.5 rounded-xl">
              드로잉/사진/글
            </span>
            <span className="text-xs pt-1">
              <FontAwesomeIcon icon={faMapMarkerAlt} className="text-xs mr-1" />        
              서울시 강남구
            </span>
          </div>
          <div className="text-sm font-semibold py-2">
            <span>
              일몰 출사 같이 가요
            </span>
          </div>
        </div>
        {/* 모임일자, 가격 */}
        <div className="flex justify-between">
          <span className="text-sm">2022.09.15</span>
          <span className="text-sm font-semibold">50,000원</span>
        </div>
      </div>
    </div>
  );
};

export default MeetingCard;