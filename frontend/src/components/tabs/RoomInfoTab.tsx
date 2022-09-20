import React from 'react';

const RoomInfoTabs = () => {
  return (
    <div>
      <h3 className='font-semibold'>[이색 놀거리] 블루 하와이안 만들기</h3>
      <hr className="mt-5 mb-2" />

      <div className="text-sm flex justify-between">
      <p className="flex">모집 인원</p>
      {/* grid grid-cols-3 */}
      <div className="grid grid-cols-3">
        <span>2 명</span>
        <span className="text-center">~</span>
        <span>5 명</span>
      </div>
    </div>
    <hr className="my-2" />
    </div>
  );
};

export default RoomInfoTabs;