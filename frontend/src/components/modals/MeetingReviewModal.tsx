import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
const MeetingReviewModal = () => {
  return (
    <div className="z-50 w-full h-[25vh] flex flex-col bg-slate-200 border-t p-2 ">
      {/* 닫기 버튼 */}
      <div className="flex justify-end">
        <FontAwesomeIcon className="text-2xl m-1" icon={faXmark} />
      </div>

      {/* 멘트 */}
      <div className="flex flex-col justify-center items-center">
        <span>{`[이색놀거리] 블루 하와이안 만들기 모임이`}</span>
        <span>{`완료되었어요!`} </span>
        <span>{`모임을 성공적으로 마치셨나요?`}</span>
      </div>
      {/* 성공여부 버튼 */}
      <div className="mt-2 mx-auto">
        <button className="bg-yellow-100 rounded-lg p-2 mr-5">성공했어요 😆</button>
        <button className="bg-blue-200 rounded-lg p-2">실패했어요 😞</button>
      </div>
    </div>
  );
};

export default MeetingReviewModal;
