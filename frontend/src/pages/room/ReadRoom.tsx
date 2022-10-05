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
import { withdrawWallet } from "../../api/profile";

const ReadRoom = () => {
  const { meeting_id } = useParams<{ meeting_id: string }>();
  const [ isDone, setIsDone ] = useState(false)
  const [userAuth] = useRecoilState(auth);
  const [islogining, setLogining] = useState<boolean>(false);

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
    hostId: '',
    hostName: '',
    members: [{
      name: '',
      userId: 0,
      email: '',
      hostScore: 0,
      memberScore: 0
    }]
  });

  // 현재 유저가 이 모임 참여자 목록에 있는 지 없는 지 판단
  const [isInThisRoom, setIsInThisRoom] = useState(false);

  const getIsInThisRoom = () => {
    room.members.forEach((member: members) => {
      if (member.userId === userAuth.userId) {
        setIsInThisRoom(true);
      } else {
        setIsInThisRoom(false);
      }
    })
  }

  // 출금 
  const getWithdraw = () => {
    if ((userAuth.userId === room.hostId )&& (room.members.length >= room.minUser)) {
      Swal.fire({
        title: "더 이상 모집하지 않고 마감하시겠습니까?",
        text:"마감하시면 스마트 컨트랙트가 체결되고, 모인 돈이 내 지갑으로 들어옵니다.",
        icon: 'info',
        showCancelButton: true,
        cancelButtonText: '취소'
      }).then(()=>{
        // axios
        withdrawWallet(room.roomId).then(()=> {
          navigate('/wallet')
          // 해당 모임 모집 마감 처리
          setIsDone(true)
        })
      })
    }
  }


  useEffect(() => {
    if (meeting_id) {
      readRoom(meeting_id)
        .then(({ data }) => {
          setRoom({ ...data.roomInfo });
        })
        .catch((e) => {
        });
    };
    // 2. create될 때 한 번만 현재 유저가 이 방에 참여하고 있는 지 판별
      getIsInThisRoom();

    /* 3. 출금 
      - 현재 유저 === 방 호스트 && 오늘이 모집 마감일 && 전체 참여자 수가 최소 모집 인원 이상
      - alert(더 이상 모집하지 않고 마감하시겠습니까? 마감하시면 스마트 컨트랙트가 체결되고, 모인 돈이 내 지갑으로 들어옵니다)
      - ok 누르면 돈 출금 axios + 내 지갑으로 redirect 
      - 모집 마감 처리(변수 하나 만들어서, 방장이 출금했을 때, 인원이 다 찼을 때, 모집 마감일이 지났을 때 true이도록 )
    */
    getWithdraw();

  }, []);

  const navigate = useNavigate();

  const onClickInAndOut = () => {
    // 참여자 늘리는 axios 요청 보내기
    // 로그인이 안되어 있으면 로그인 페이지로 넘기기
    // 로그인이 되어 있으면 axios 요청
    // 로그인 막기
    if (!userAuth.isLogined) {
      Swal.fire({
        position: "center",
        icon: "error",
        title: "로그인 후에 모임 참여가 가능합니다.",
        showConfirmButton: false,
        timer: 1500,
      }).then(() => {
        navigate("/login");
      });
    } // 퇴장: 로그인은 되어 있지만 이미 참여하고 있는 경우
    else if (userAuth.isLogined && isInThisRoom) {
      Swal.fire({
        position: "center",
        icon: "info",
        title: "이미 참여하고 있는 모임입니다. 퇴장하시겠습니까?",
        showConfirmButton: true,
        showCancelButton: true,
        cancelButtonText: "취소",
        timer: 1500,
      }).then(() => {
        //axios로 퇴장 시키기
        try {
          setLogining(true);
          outRoom(room.roomId).then(() => {
            setLogining(false);
            Swal.fire({
              position: "center",
              title: `${room.title}에서 퇴장하셨습니다.`,
              timer: 1500,
            }).then(() => navigate(0));
          });
        } catch {
          setLogining(false);
          Swal.fire({
            toast: true,
            position: "center",
            showConfirmButton: true,
            title: `입장 실패하였습니다.`,
          });
        }
      });
    }
    // 입장: 로그인이 되어 있는 데 현재 방에 참가하고 있지 않은 경우
    else if (userAuth.isLogined && !isInThisRoom) {
      if (room.roomId) {
        Swal.fire({
          title: `${room.title}에 참여하시겠습니까?`,
          showCancelButton: true,
          confirmButtonText: "참여하기",
          cancelButtonText: "취소",
        }).then((result) => {
          if (result.isConfirmed) {
            try {
              setLogining(true);
              intoRoom(room.roomId).then(() => {
                setLogining(false);
                navigate(0);
              });
            } catch {
              setLogining(false);
              Swal.fire({
                toast: true,
                position: "center",
                showConfirmButton: true,
                title: `퇴장 실패하였습니다.`,
              });
            }
          }
        });
      }
    }
  };

  return (
    <div>
      <div>
        <Logo />
        <div className="px-3">
          <MeetingCard name="readRoom" room={room} />

          {islogining ? (
            <div className="w-full flex flex-col justify-center items-center">
              <div className="flex flex-row">
                <img
                  className="w-5 h-5 animate-gelatine mr-1"
                  alt="userBadge3"
                  src={process.env.PUBLIC_URL + `/img/userBadge3.png`}
                />
                <img
                  className="w-5 h-5 animate-gelatine mr-1"
                  alt="userBadge2"
                  src={process.env.PUBLIC_URL + `/img/userBadge2.png`}
                />
                <img
                  className="w-5 h-5 animate-gelatine"
                  alt="userBadge1"
                  src={process.env.PUBLIC_URL + `/img/userBadge1.png`}
                />
              </div>
              <span className="font-semibold text-xl">처리중...</span>
            </div>
          ) : isInThisRoom ? (
            <button
              type="button"
              onClick={onClickInAndOut}
              className="w-full text-center text-white font-bold bg-blue-500 rounded p-1">
              퇴장하기
            </button>
          ) : (
            <button
              type="button"
              onClick={onClickInAndOut}
              className="w-full text-center font-bold bg-yellow-100 rounded p-1">
              참여하기
            </button>
          )}
        </div>
      </div>
      {/* 탭 구현 */}
      <div>
        <RoomTabs room={room} isInThisRoom={isInThisRoom} />
      </div>
    </div>
  );
};

export default ReadRoom;
