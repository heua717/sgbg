import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { postEvalHost } from "../../api/eval";

const MeetingReviewModal = ({ isVisible, setIsVisible, roomId, room }: any) => {
  const navigator = useNavigate();
  const handleSuccess = () => {
    //axios
    postEvalHost(roomId, true)
      .then(({ data }) => {
        if (data.statusCode === 2010) {
          setIsVisible(false);
          navigator(`/eval/${roomId}`);
        } else {
          Swal.fire({
            toast: true,
            position: "center",
            icon: "error",
            showConfirmButton: false,
            timer: 1000,
            title: `다시 리뷰해주세요.`,
          }).then(() => {
            setIsVisible(false);
          });
        }
      })
      .catch(() => {
        Swal.fire({
          toast: true,
          position: "center",
          icon: "error",
          showConfirmButton: false,
          timer: 1000,
          title: `다시 리뷰해주세요.`,
        }).then(() => {
          setIsVisible(false);
        });
      });
  };
  const handleFail = () => {
    //axios
    postEvalHost(roomId, false)
      .then(({ data }) => {
        if (data.statusCode === 2010) {
          setIsVisible(false);
          window.location.reload();
        } else {
          Swal.fire({
            toast: true,
            position: "center",
            icon: "error",
            showConfirmButton: false,
            timer: 1000,
            title: `다시 리뷰해주세요.`,
          }).then(() => {
            setIsVisible(false);
          });
        }
      })
      .catch(() => {
        Swal.fire({
          toast: true,
          position: "center",
          icon: "error",
          showConfirmButton: false,
          timer: 1000,
          title: `다시 리뷰해주세요.`,
        }).then(() => {
          setIsVisible(false);
        });
      });
  };
  const handleClose = () => {
    setIsVisible(false);
  };
  return (
    <div
      className={`${
        isVisible ? "" : "hidden"
      } fixed left-0 bottom-0 w-full h-[30vh] flex flex-col bg-slate-200 border-t p-4`}
    >
      {/* 닫기 버튼 */}
      <div className="flex justify-end" onClick={handleClose}>
        <FontAwesomeIcon className="text-2xl m-1" icon={faXmark} />
      </div>

      {/* 멘트 */}
      <div className="flex flex-col justify-center items-center font-semibold">
        <span>{`${[room.parentCategory]} ${room.title}`}</span>
        <span>{`완료되었어요!`} </span>
        <span>{`모임을 성공적으로 마치셨나요?`}</span>
      </div>
      {/* 성공여부 버튼 */}
      <div className="mt-4 mx-auto">
        <button
          className="bg-yellow-100 rounded-lg py-2 px-3 mr-8"
          onClick={handleSuccess}
        >
          성공했어요 😆
        </button>
        <button
          className="bg-blue-200 rounded-lg py-2 px-3"
          onClick={handleFail}
        >
          실패했어요 😞
        </button>
      </div>
    </div>
  );
};

export default MeetingReviewModal;
