const MeetingCard = (props:any) => {
  const getStateBadgeColor = () => {
    return 'bg-yellow-100';
  }
  const getMemberBadgeColor = () => {
    return 'bg-gray-100';
  }
  return (
    <div className="w-per100 h-per100 flex flex-row border rounded-lg p-2">
      {/* 모임 이미지, 뱃지 */}
      <div className="w-per45 mr-5 relative">
        <div className={`absolute top-0 left-0 rounded ${getStateBadgeColor()} text-sm px-2`}>{`마감 임박`}</div>
        <img
          className="w-full h-full rounded"
          src={process.env.PUBLIC_URL + `/img/이색놀거리_방탈출.png`}
          alt="소분류 카테고리 사진"></img>

        <div className={`absolute bottom-0 right-0 rounded ${getMemberBadgeColor()} text-sm px-1`}>{`${3}/${5}`}</div>
      </div>
      {/* 모임 설명 */}
      <div className="w-per55 flex flex-col justify-between">
        {/* 카테고리, 모임명, 위치 */}
        <div className="flex flex-col">
          <span className="font-semibold text-sm">방탈출</span>
          <span className="text-sm">싱글벙글 모임명</span>
          <span className="text-xs font-light">[서울시 강남구]</span>
        </div>
        {/* 모임일자, 가격 */}
        <div className="flex flex-col">
          <span className="text-sm">2022.09.15</span>
          <span className="text-sm">50,000원</span>
        </div>
      </div>
    </div>
  );
};

export default MeetingCard;
