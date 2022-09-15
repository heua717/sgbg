const MeetingCard = () => {
  return (
    <div>
      {/* 모임 이미지, 뱃지 */}
      <div></div>
      {/* 모임 설명 */}
      <div>
        {/* 카테고리, 모임명, 위치 */}
        <div>
          <span>방탈출</span>
          <span>싱글벙글모임명</span>
          <span>서울시 강남구</span>
        </div>
        {/* 모임일자, 가격 */}
        <div>
          <span>2022.09.15</span>
          <span>50,000원</span>
        </div>
      </div>
    </div>
  );
};

export default MeetingCard;
