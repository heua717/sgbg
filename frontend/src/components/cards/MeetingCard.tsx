const MeetingCard = () => {
  return (
    <div className="w-per100 h-per100 flex flex-row border rounded-lg p-3">
      {/* 모임 이미지, 뱃지 */}
      <div className="w-per40 mr-3">
        <div>{`마감 임박`}</div>
        <img
          className=""
          src={process.env.PUBLIC_URL + `/img/이색놀거리_방탈출.png`}
          alt="소분류 카테고리 사진"
        />
        <div>{`${3}/${5}`}</div>
      </div>
      {/* 모임 설명 */}
      <div className="w-per60 flex flex-col justify-between">
        {/* 카테고리, 모임명, 위치 */}
        <div className="flex flex-col">
          <span className="font-semibold text-sm">방탈출</span>
          <span className="text-sm">싱글벙글 모임명</span>
          <span className="text-xs">서울시 강남구</span>
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
