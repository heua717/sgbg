import React from 'react';

const RoomInfoTabs = () => {
  return (
    <div>
      <h3 className='font-semibold'>[이색 놀거리] 블루 하와이안 만들기</h3>
      <hr className="mt-5 mb-2" />

      <div className="text-sm grid grid-cols-10">
        <p className="col-span-4 font-semibold">모집 인원</p>
        {/* grid grid-cols-3 */}
        <div className="col-start-5 col-end-8 grid grid-cols-3">
          <span>2 명</span>
          <span className="text-center">~</span>
          <span>5 명</span>
        </div>
      </div>
      <hr className="my-2" />

      <div className="text-sm grid grid-cols-10">
        <p className='col-span-4 font-semibold'>모집 마감일</p>
        <p className="col-start-5 col-end-9">2022년 10월 7일</p>
      </div>
      <hr className="my-2" />

      <div className="text-sm grid grid-cols-10">
        <p className='col-span-4 font-semibold'>예약 날짜</p>
        <p className="col-start-5 col-end-9">2022년 10월 9일</p>
      </div>
      <hr className="my-2" />

    </div>
  );
};

export default RoomInfoTabs;