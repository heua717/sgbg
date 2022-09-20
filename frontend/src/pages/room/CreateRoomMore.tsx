import { Link } from "react-router-dom";
import MeetingCard from "../../components/cards/MeetingCard"
import BtnExitToHome from "../../components/buttons/BtnExitToHome";

const CreateRoomMore = () => {
  return (
    // markup 0915 임지민
    <div className="mt-8 mx-6">
      <div>
      {/* 
        카테고리
        - 아래 줄 자체를 클릭하면 카테고리 선택 페이지로 넘어가게
        - 클릭하면 회색 그림자?가 나오도록
      */}
        <BtnExitToHome></BtnExitToHome>
        <h2 className="inline-block text-xl font-black ml-5">모임 만들기</h2>
      </div>
      <div className="mt-5">
        <div className="flex justify-between">
          <p className="font-bold mt-3 mb-3 pl-2">모임 기본 정보</p>
          <Link to="/meeting/create">
            <div className="py-3">
              {/* 
              다시 작성하기 클릭하면 기본 정보 작성 페이지로 넘어가는데 
              이 때 작성한 정보가 기본으로 떠 있도록 처리하기
              0920 임지민
              */}
              <button type="button" className="text-white text-xs bg-gray-400 px-2 py-1 rounded-xl">다시 작성하기</button>
            </div>
          </Link>
        </div>
        <MeetingCard />
      </div>

    <div className="my-8">
      {/* 기본 정보 작성 */}
      <h3 className="text-lg font-semibold">상세 정보</h3>
      <hr className="my-5" />
      
      {/* 경고 문구 */}
      <p className="text-red-600 text-sm">⚠ 모집 마감일, 예약 날짜, 최소 태도 점수는(은) 수정이 불가하니 신중하게 작성해주시기 바랍니다. </p>
      <hr className="my-5" />

      {/* 모집 마감일 */}
      <div className="flex justify-between">
        <label htmlFor="dueDate" className="flex mr-2">모집 마감일 </label>
        <input type="date" id="dueDate" className="flex" />
      </div>
      <hr className="my-5" />

      {/* 예약 날짜 */}
      <div className="flex justify-between">
        <label htmlFor="bookingDate" className="flex mr-2">예약 날짜 </label>
        <input type="date" id="bookingDate" className="flex" />
      </div>
      <hr className="my-5" />
      
        {/* <div className="grid col-start-3 col-end- "> */}
      {/* 금액 */}
      <div className="grid grid-cols-6">
        <label htmlFor="meetingPrice" className="mr-2 col-span-1 gap-1">금액 </label>
        <input type="number" id="meetingPrice" 
          className="col-start-4 col-end-6"
          placeholder="0"/>
        <p className="text-center">원</p>
      </div>
      <hr className="my-5" />
      
      {/* 최소 태도 점수 */}
      <div className="grid grid-cols-6">
        <label htmlFor="attituteScore" className="mr-2 col-span-3 gap-1">최소 태도 점수</label>
        <input type="number" id="attituteScore" 
        className="col-start-4 col-end-6"
        placeholder="50"/>
        <p className="text-center">점</p>
      </div>
    </div>


    <div className="grid grid-cols-1 mt-3">
      <button type="button"
        className="text-center font-bold bg-gray-200 rounded py-1">모임 만들기</button>
    </div>

  </div>);
};

export default CreateRoomMore;
