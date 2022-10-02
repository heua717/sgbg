import MeetingCard from "../../components/cards/MeetingCard";
import Logo from "../../components/etc/Logo";
import RoomTabs from "../../components/tabs/RoomTabs";
import { roomMore } from "../../util/room";
import { useEffect, useState } from "react";
import { readRoom, intoRoom } from "../../api/room";
import { useParams, useNavigate } from "react-router-dom";
import { auth } from "../../store/auth";
import { useRecoilState } from "recoil";
import Swal from "sweetalert2";


const ReadRoom = () => {
  const { meeting_id } = useParams<{ meeting_id: string }>();
  const [userAuth, setUserAuth] = useRecoilState(auth);

  const [room, setRoom] = useState<roomMore>({
    roomId: 0,
    title: "",
    parentCategory: "",
    childCategory: "",
    minUser: 0,
    maxUser: 0,
    location: {
      locationId: "",
      name: "",
      latitude: "",
      hardness: "",
      roadAddress: "",
    },
    description: "",
    endDate: "",
    reservationDate: "",
    price: 0,
    minMemberScore: 0,
  });

  useEffect(() => {
    //axios
    // console.log(meeting_id); // ok
    if (meeting_id) {
      readRoom(meeting_id)
        .then(({ data }) => {
          // console.log(data);
          setRoom({ ...data.roomInfo });
        })
        .catch((e) => {
          
        });
    }
  }, []);

  const navigate = useNavigate()

  const onClick = () => {
    // 참여자 늘리는 axios 요청 보내기
    // 로그인이 안되어 있으면 로그인 페이지로 넘기기
    // 로그인이 되어 있으면 axios 요청
    console.log(userAuth);
    // if (!userAuth.isLogined) {
    //   Swal.fire({
    //     position: "center",
    //     icon: "error",
    //     title: "로그인 후에 모임 참여가 가능합니다.",
    //     showConfirmButton: false,
    //     timer: 1500,
    //   }).then(() => {
    //     navigate("/login")
    //   });
    // } else {
      if (room.roomId) {
        Swal.fire({
          title: `${room.title}에 참여하시겠습니까?`,
          showCancelButton: true,
          confirmButtonText: '참여하기',
          cancelButtonText: '취소',
        }).then((result)=>{
          if(result.isConfirmed){
            console.log(room.roomId);
            
            intoRoom(room.roomId)
            // .then(()=>{
            //   navigate(0)
            // })
          }
        })
      }
    // }
    
  };

  return (
    <div>
      <div>
        <Logo />
        <div className="px-3">
          <MeetingCard name="readRoom" room={room} />
          <button type="button" onClick={onClick}
            className="w-full text-center font-bold bg-yellow-100 rounded p-1">
            참여하기
          </button>
        </div>
      </div>
      {/* 탭 구현 */}
      <div>
        <RoomTabs room={room}/>
      </div>
    </div>
  );
};

export default ReadRoom;
