import { Link } from "react-router-dom";

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
      <Link to="/meeting/category">
        <div className="flex justify-between">
            <p className="flex">카테고리</p>
            <button className="flex">&gt;</button>
        </div>
      </Link>
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
    <hr className="my-5" />

    <div className="flex justify-between">
      <p className="flex">모임 위치</p>
      <button className="flex">▷</button>
    </div>
    <hr className="my-5" />

    <div className="grid">
      <label htmlFor="roomExplanation" className="mb-3">모임 설명</label>
      <textarea name="exp" id="roomExplanation" cols={30} rows={8}
        style={{resize: "none"}}
        placeholder="모임에 대한 설명을 입력하세요"></textarea>
    </div>

    <div className="grid grid-cols-1 mt-3">
      <button type="button"
        className="text-center bg-gray-200 rounded py-1">다음으로</button>
    </div>

  </div>
  );
};

export default CreateRoom;
