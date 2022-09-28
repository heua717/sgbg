import { Link } from "react-router-dom";
import { useRecoilState } from "recoil";
import { roomMore } from "../../store/room";
import { inputRoomInfo } from "../../store/room";
import MeetingCard from "../../components/cards/MeetingCard"
import BtnExitToHome from "../../components/buttons/BtnExitToHome";
import { useEffect, useState } from "react";


const CreateRoomMore = () => {
  const [room, setRoom] = useRecoilState<roomMore>(inputRoomInfo)

  // created 될 때
  useEffect(() => {
    console.log(room.dueDate);
    
  })

  // recoil에 작성한 모임 정보 저장하기 0927 임지민
  const [isValidated, setIsValidated] = useState(false)
  const onChangeValidation = () => {
    if (room.cost && room.minAttituteNum ){
      setIsValidated((isValidated:boolean) => isValidated = true)
      
    } else {
      setIsValidated((isValidated:boolean) => isValidated = false)
      // console.log('else로 빠짐');
      // console.log(room.title.trim());
    }
  };

  const onChange = (e: React.ChangeEvent<HTMLInputElement>): void => {
    const { name, value } = e.target;

    setRoom({
      ...room,
      [name]: value
    });
    onChangeValidation();
  };

  // 날짜 출력 잘되나 테스트 0927 임지민
  

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
        <MeetingCard room={room} />
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
        <input type="datetime-local" id="dueDate" name="dueDate" className="flex" onChange={onChange} />
      </div>
      <hr className="my-5" />

      {/* 예약 날짜 */}
      <div className="flex justify-between">
        <label htmlFor="bookingDate" className="flex mr-2">예약 날짜 </label>
        <input type="datetime-local" id="bookingDate" name="bookingDate" className="flex" onChange={onChange} />
      </div>
      {/* <p>{room.bookingDate.toDateString()}</p> */}
      <hr className="my-5" />
      
        {/* <div className="grid col-start-3 col-end- "> */}
      {/* 금액 */}
      <div className="grid grid-cols-6">
        <label htmlFor="cost" className="mr-2 col-span-1 gap-1">금액 </label>
        <input type="number" id="cost" name="cost" 
          className="col-start-4 col-end-6"
          placeholder="0" onChange={onChange}/>
        <p className="text-center">원</p>
      </div>
      <p>{room.cost}</p>
      <hr className="my-5" />
      
      {/* 최소 태도 점수 */}
      <div className="grid grid-cols-6">
        <label htmlFor="minAttituteNum" className="mr-2 col-span-3 gap-1">최소 태도 점수</label>
        <input type="number" id="minAttituteNum" name="minAttituteNum"
        className="col-start-4 col-end-6"
        placeholder="50" onChange={onChange}/>
        <p className="text-center">점</p>
      </div>
      <p>{room.minAttituteNum}</p>
    </div>


    {isValidated && (
      <Link to="/meeting/create/more">
        {/* <p>{}</p> */}
        <div className="grid grid-cols-1 mt-3">
          {/* 모든 칸이 추가된 경우 0927 임지민 */}
            <button type="button"
            className="text-center text-white font-semibold bg-blue-200 rounded py-1">모임 만들기</button>
        </div>
      </Link>)}
      {/* 한 칸이라도 빈 경우 0927 임지민 */}
      {!isValidated && (
        <div className="grid grid-cols-1 mt-3">
          <button type="button"
          className="text-center bg-gray-200 rounded py-1">모임 만들기</button>
        </div> )}

  </div>);
};

export default CreateRoomMore;
