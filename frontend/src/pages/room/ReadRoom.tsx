import MeetingCard from "../../components/cards/MeetingCard";
import Logo from "../../components/etc/Logo";
import RoomTabs from "../../components/tabs/RoomTabs";
import BtnAddOrDelete from "../../components/buttons/BtnAddOrDelete";
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
    members: [
      {
        name: "",
        kakaoId: 0,
        email: "",
        hostScore: 0,
        memberScore: 0,
      },
    ],
  });

  // 현재 유저가 이 모임 참여자 목록에 있는 지 없는 지 판단
  const [isInThisRoom, setIsInThisRoom] = useState(false);
  const [isHost, setIsHost] = useState(false)


  const getIsInThisRoom = (members:members[]) => {
    console.log('members= ', members); // ok
    // members 값이 제대로 저장이 안돼서 한 번더 저장해줌
    setRoom({
      ...room,
      members: members
    })
    
    // ishost; 현재 유저가 호스트이면
    if (members[0].kakaoId === userAuth.userId) {
      setIsHost(true)
    } else {
      setIsHost(false)
    }
    
    const newMembers = members.splice(1)
    newMembers.forEach((member: members) => {
      console.log(member);
      
      console.log('readroom kakaoid', member.kakaoId, 'readroom current user=', userAuth.userId);
      
      if (member.kakaoId === userAuth.userId) {
        setIsInThisRoom(true);
        return false
      } else {
        setIsInThisRoom(false);
      }
    });
  };


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
    } // 퇴징: 로그인은 되어 있지만 이미 참여하고 있는 경우
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


  useEffect(() => {
    if (meeting_id) {
      readRoom(meeting_id)
        .then(({ data }) => {
          setRoom(data.roomInfo);
          getIsInThisRoom(data.roomInfo.members);
        })
        .catch((e) => {});
    }
    console.log('readroommembers=',  room.members);
  }, [])

  const navigate = useNavigate();

  // const onClickInAndOut = () => {
  //   // 참여자 늘리는 axios 요청 보내기
  //   // 로그인이 안되어 있으면 로그인 페이지로 넘기기
  //   // 로그인이 되어 있으면 axios 요청
  //   // 로그인 막기
  //   if (!userAuth.isLogined) {
  //     Swal.fire({
  //       position: "center",
  //       icon: "error",
  //       title: "로그인 후에 모임 참여가 가능합니다.",
  //       showConfirmButton: false,
  //       timer: 1500,
  //     }).then(() => {
  //       navigate("/login");
  //     });
  //   } // 퇴징: 로그인은 되어 있지만 이미 참여하고 있는 경우
  //   else if (userAuth.isLogined && isInThisRoom) {
  //     Swal.fire({
  //       position: "center",
  //       icon: "info",
  //       title: "이미 참여하고 있는 모임입니다. 퇴장하시겠습니까?",
  //       showConfirmButton: true,
  //       showCancelButton: true,
  //       cancelButtonText: "취소",
  //       timer: 1500,
  //     }).then(() => {
  //       //axios로 퇴장 시키기
  //       try {
  //         setLogining(true);
  //         outRoom(room.roomId).then(() => {
  //           setLogining(false);
  //           Swal.fire({
  //             position: "center",
  //             title: `${room.title}에서 퇴장하셨습니다.`,
  //             timer: 1500,
  //           }).then(() => navigate(0));
  //         });
  //       } catch {
  //         setLogining(false);
  //         Swal.fire({
  //           toast: true,
  //           position: "center",
  //           showConfirmButton: true,
  //           title: `입장 실패하였습니다.`,
  //         });
  //       }
  //     });
  //   }
  //   // 입장: 로그인이 되어 있는 데 현재 방에 참가하고 있지 않은 경우
  //   else if (userAuth.isLogined && !isInThisRoom) {
  //     if (room.roomId) {
  //       Swal.fire({
  //         title: `${room.title}에 참여하시겠습니까?`,
  //         showCancelButton: true,
  //         confirmButtonText: "참여하기",
  //         cancelButtonText: "취소",
  //       }).then((result) => {
  //         if (result.isConfirmed) {
  //           try {
  //             setLogining(true);
  //             intoRoom(room.roomId).then(() => {
  //               setLogining(false);
  //               navigate(0);
  //             });
  //           } catch {
  //             setLogining(false);
  //             Swal.fire({
  //               toast: true,
  //               position: "center",
  //               showConfirmButton: true,
  //               title: `퇴장 실패하였습니다.`,
  //             });
  //           }
  //         }
  //       });
  //     }
  //   }
  // };

  return (
    <div>
      <div>
        <Logo />
        <div className="px-3">
          <MeetingCard name="readRoom" room={room} />
          {/* <BtnAddOrDelete room={room}/> */}
          {!isHost && (
            <div>
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
                입장하기
              </button>
          )} 
        </div>)}
        </div>
      </div>
      {/* 탭 구현 */}
      {/* {room.members.map((member: members)=> (
        <p>{member.kakaoId}</p>
      ))} */}
      <div>
        <RoomTabs room={room} isInThisRoom={isInThisRoom} isHost={isHost} />
      </div>
    </div>
  );
};

export default ReadRoom;
