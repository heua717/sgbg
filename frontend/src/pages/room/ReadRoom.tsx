import MeetingCard from "../../components/cards/MeetingCard";
import Logo from "../../components/etc/Logo";
import RoomTabs from "../../components/tabs/RoomTabs";
import { roomMore } from "../../util/room";
import { useEffect, useState } from "react";
import { readRoom, intoRoom, outRoom } from "../../api/room";
import { useParams, useNavigate } from "react-router-dom";
import { auth } from "../../store/auth";
import { useRecoilState } from "recoil";
import Swal from "sweetalert2";
import { members } from "../../util/room";


const ReadRoom = () => {
  const { meeting_id } = useParams<{ meeting_id: string }>();
  const [userAuth ] = useRecoilState(auth);

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
    members: [{
      name: '',
      userId: 0,
      email: '',
      hostScore: 0,
      memberScore: 0
    }]
  });


  // 현재 유저가 이 모임 참여자 목록에 있는 지 없는 지 판단
  const [isInThisRoom, setIsInThisRoom] = useState(false)

  const getIsInThisRoom = () =>{
    room.members.forEach((member:members) => {
      if(member.userId === userAuth.userId) {
        setIsInThisRoom(true)
      } else {
        setIsInThisRoom(false)
      }
    })
  }


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
    };
    // create될 때 한 번만 현재 유저가 이 방에 참여하고 있는 지 판별
    getIsInThisRoom();
  }, []);

  const navigate = useNavigate()

  const onClickInAndOut = () => {
    // 참여자 늘리는 axios 요청 보내기
    // 로그인이 안되어 있으면 로그인 페이지로 넘기기
    // 로그인이 되어 있으면 axios 요청
    console.log(userAuth);
    // 로그인 막기
    if (!userAuth.isLogined ) {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "로그인 후에 모임 참여가 가능합니다.",
        showConfirmButton: false,
        timer: 1500,
      }).then(() => {
        navigate("/login")
      });
    } // 퇴징: 로그인은 되어 있지만 이미 참여하고 있는 경우
    else if (userAuth.isLogined && isInThisRoom) {
      Swal.fire({
        position: "center",
        icon: "info",
        title: "이미 참여하고 있는 모임입니다. 퇴장하시겠습니까?",
        showConfirmButton: true,
        showCancelButton: true,
        cancelButtonText: '취소',
        timer: 1500,
      }).then(() => {
        //axios로 퇴장 시키기
        outRoom(room.roomId)
        .then(()=> {
          Swal.fire({
            position: 'center',
            title: `${room.title}에서 퇴장하셨습니다.`,
            timer: 1500,
          }).then(()=> navigate(0))
        })
      });
    }
    // 입장: 로그인이 되어 있는 데 현재 방에 참가하고 있지 않은 경우
    else if (userAuth.isLogined && !isInThisRoom) {
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
      };
    };
  };

  

  return (
    <div>
      <div>
        <Logo />
        <div className="px-3">
          <MeetingCard name="readRoom" room={room} />
          {isInThisRoom && (
            <button type="button" onClick={onClickInAndOut}
              className="w-full text-center font-bold bg-yellow-100 rounded p-1">
              참여하기
            </button>
          )}
          {!isInThisRoom && (
            <button type="button" onClick={onClickInAndOut}
              className="w-full text-center font-bold bg-yellow-100 rounded p-1">
              퇴장하기
          </button>
          )}
        </div>
      </div>
      {/* 탭 구현 */}
      <div>
        <RoomTabs room={room} isInThisRoom={isInThisRoom}/>
      </div>
    </div>
  );
};

export default ReadRoom;
