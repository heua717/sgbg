import React from 'react';

const RoomInfoTabs = () => {
  return (
    <div>
      <h3 className='font-semibold'>[이색 놀거리] 블루 하와이안 만들기</h3>
      <hr className="my-2" />

      <div className="grid grid-cols-6">
      <p className="col-span-2">모집인원</p>
      {/* grid grid-cols-3 */}
      <div className="col-span-4 grid grid-cols-5">
        <input type="number" id="minPerson" className="" />
        <label htmlFor="minPerson" className="text-center">명</label>
        <span className="text-center">~</span>
        <input type="number" id="maxPerson" className="" />
        <label htmlFor="maxPerson" className="text-center">명 </label>
      </div>
    </div>
    <hr className="my-2" />
    </div>
  );
};

export default RoomInfoTabs;