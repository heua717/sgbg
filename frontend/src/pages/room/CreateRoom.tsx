const CreateRoom = () => {
  return (
    // markup 0915 임지민
  <div className="mt-8 mx-6">
    <div>
      <button className="text-2xl font-black mr-3">X</button>
      <h2 className="inline-block text-xl font-black ml-5">모임 만들기</h2>
    </div>

    <div className="mt-8">
      {/* 기본 정보 작성 */}
      <h3 className="text-lg font-semibold">기본 정보 작성</h3>
      <hr className="my-5" />

      {/* 방 제목 작성 */}
      <label htmlFor="meetingName" className="mr-2">방 제목 </label>
      <input type="text" id="meetingName" />
      <hr className="my-5" />
      
      {/* 경고 문구 */}
      <p className="text-red-600 text-sm">⚠ 카테고리, 모집인원, 모임위치는(은) 수정이 불가하니 신중하게 작성해주시기 바랍니다. </p>
      <hr className="my-5" />

      {/* 
        카테고리
        - 아래 줄 자체를 클릭하면 카테고리 선택 페이지로 넘어가게
        - 클릭하면 회색 그림자?가 나오도록
      */}
      <div className="flex justify-between">
        <p className="flex">카테고리</p>
        <button className="flex">&gt;</button>
      </div>
    </div>
    <hr className="my-5" />
    
    {/* 모집 인원 */}
    {/* <div>
      <p className="inline-block mr-3">모집인원</p>
      <div className="inline-block">
        <div className="w-per20 inline-block">
          <input type="number" id="minPerson" className="inline-block w-per30"/>
          <label htmlFor="minPerson" className="ml-2">명</label>
          <span className="ml-2">~</span>
        </div>
        <div className="w-per20 inline-block">
          <input type="number" id="maxPerson" className="inline-block w-per30" />
          <label htmlFor="maxPerson" className="ml-2">명 </label>
        </div>
      </div>
    </div> */}
    <div className="flex justify-evenly">
      <p className="flex">모집인원</p>
      <div className="flex justify-evenly">
        <div className="flex w-per50">
          <input type="number" id="minPerson" className="w-per70" />
          <label htmlFor="minPerson">명</label>
        </div>
        <span className="flex">~</span>
        <div className="flex w-per50">
          <input type="number" id="maxPerson" className="w-per70" />
          <label htmlFor="maxPerson">명 </label>
        </div>
      </div>
    </div>
  </div>
  );
};

export default CreateRoom;
