import { Link } from "react-router-dom";
import MeetingCard from "../../components/cards/MeetingCard"

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
        <button className="text-2xl font-black mr-3">X</button>
        <h2 className="inline-block text-xl font-black ml-5">모임 만들기</h2>
      </div>
      <div className="mt-5">
        <MeetingCard />
      </div>

    <div className="mt-8">
      {/* 기본 정보 작성 */}
      <h3 className="text-lg font-semibold">상세 정보</h3>
      <hr className="my-5" />
      
      {/* 경고 문구 */}
      <p className="text-red-600 text-sm">⚠ 모집 마감일, 예약 날짜, 최소 태도 점수는(은) 수정이 불가하니 신중하게 작성해주시기 바랍니다. </p>
      <hr className="my-5" />

      {/* 모집 마감일 */}
      <label htmlFor="dueDate" className="mr-2">모집 마감일 </label>
      <input type="date" id="dueDate" />
      <hr className="my-5" />

      {/* 예약 날짜 */}
      <label htmlFor="bookingDate" className="mr-2">예약 날짜 </label>
      <input type="date" id="bookingDate" />
      <hr className="my-5" />
      
      {/* 금액 */}
      <label htmlFor="meetingPrice" className="mr-2">금액 </label>
      <input type="number" id="meetingPrice" />
      <p>원</p>
      <hr className="my-5" />

      <label htmlFor="attituteScore" className="mr-2">최소 태도 점수</label>
      <input type="number" id="attituteScore"/>
      <p>점</p>
    </div>


    <div className="grid grid-cols-1 mt-3">
      <button type="button"
        className="text-center bg-gray-200 rounded py-1">모임 만들기</button>
    </div>

  </div>);
};

export default CreateRoomMore;
