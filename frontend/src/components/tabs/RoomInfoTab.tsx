import React from 'react';

const RoomInfoTabs = () => {
  return (
    <div>
      <h3 className='text-sm font-semibold'>[이색 놀거리] 블루 하와이안 만들기</h3>
      <hr className="mt-5 mb-2" />

      <div className="text-sm grid grid-cols-10">
        <p className="col-span-4 font-bold">모집 인원</p>
        {/* grid grid-cols-3 */}
        <div className="col-start-5 col-end-8 grid grid-cols-3">
          <span>2 명</span>
          <span className="text-center">~</span>
          <span>5 명</span>
        </div>
      </div>
      <hr className="my-2" />

      <div className="text-sm grid grid-cols-10">
        <p className='col-span-4 font-bold'>모집 마감일</p>
        <p className="col-start-5 col-end-9">2022년 10월 7일</p>
      </div>
      <hr className="my-2" />

      <div className="text-sm grid grid-cols-10">
        <p className='col-span-4 font-bold'>예약 날짜</p>
        <p className="col-start-5 col-end-9">2022년 10월 9일</p>
      </div>
      <hr className="my-2" />

      <div className="text-sm grid grid-cols-10">
        <p className='col-span-4 font-bold'>금액</p>
        <p className="font-bold col-start-5 col-end-9">50,000원</p>
      </div>
      <hr className="my-2" />

      <div className="text-sm">
        <p className='font-bold'>모임 설명</p>
        <p className="mt-2">Lorem ipsum dolor sit amet consectetur adipisicing elit. Maiores saepe corporis qui eaque labore deserunt necessitatibus inventore vitae repellendus tenetur, quidem unde libero tempore accusantium dolores ullam! Doloremque, debitis expedita.</p>
      </div>
      <hr className="my-3" />

      <div className="text-sm">
        <p className='font-bold'>모임 위치</p>
        <div className="mt-2">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem, provident ducimus ad alias atque odio ipsam harum possimus, quam ullam veritatis aliquid quo maiores aliquam non adipisci, voluptatum sed facilis.
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus, dicta nesciunt et ipsam ea molestiae qui unde fugiat impedit totam. Laboriosam, odit amet impedit hic non illo repellendus minus debitis!
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officiis, nostrum repudiandae. Accusamus, unde incidunt laborum ea sapiente quasi neque. Veniam explicabo id expedita ratione eaque, est autem consectetur! Nulla, fugit. 
        </div>
      </div>
      <hr className="my-2" />

    </div>
  );
};

export default RoomInfoTabs;