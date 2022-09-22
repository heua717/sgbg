import FinishedMeetingCard from "../../components/cards/FinishedMeetingCard";
import ProfileCard from "../../components/cards/ProfileCard";
import Logo from "../../components/etc/Logo";

const ProfileHistory = () => {
  const user = {
    participantScore: 75,
    hostScore: 30,
    userId: "namm",
  };
  let list = [
    {
      roomId: 1,
      category: {
        name: "방탈출"
      },
      host: {
        userId: "namm",
        name: "namju",
      },
      title: "싱글벙글 방탈출",
      price: 20000,
      location: "서울시 강남구",
      reservationDate: "2022.09.10",
      endDate: "2022.09.30",
      successRate: 90,
      userCount: 10,
      eval: {
        best: 5,
        good: 3,
        wordt: 2,
      }
    },
    {
      roomId: 2,
      category: {
        name: "방탈출"
      },
      host: {
        userId: "namm",
        name: "namju",
      },
      title: "싱글벙글 방탈출",
      price: 20000,
      location: "서울시 강남구",
      reservationDate: "2022.09.10",
      endDate: "2022.09.30",
      successRate: 90,
      userCount: 10,
      eval: {
        best: 5,
        good: 3,
        wordt: 2,
      }
    },
    {
      roomId: 3,
      category: {
        name: "방탈출"
      },
      host: {
        userId: "namm",
        name: "namju",
      },
      title: "싱글벙글 방탈출",
      price: 20000,
      location: "서울시 강남구",
      reservationDate: "2022.09.10",
      endDate: "2022.09.30",
      successRate: 90,
      userCount: 10,
      eval: {
        best: 5,
        good: 3,
        wordt: 2,
      }
    },
    {
      roomId: 4,
      category: {
        name: "방탈출"
      },
      host: {
        userId: "namm",
        name: "namju",
      },
      title: "싱글벙글 방탈출",
      price: 20000,
      location: "서울시 강남구",
      reservationDate: "2022.09.10",
      endDate: "2022.09.30",
      successRate: 90,
      userCount: 10,
      eval: {
        best: 5,
        good: 3,
        wordt: 2,
      }
    },
    {
      roomId: 5,
      category: {
        name: "방탈출"
      },
      host: {
        userId: "namm",
        name: "namju",
      },
      title: "싱글벙글 방탈출",
      price: 20000,
      location: "서울시 강남구",
      reservationDate: "2022.09.10",
      endDate: "2022.09.30",
      successRate: 90,
      userCount: 10,
      eval: {
        best: 5,
        good: 3,
        wordt: 2,
      }
    }
  ];
  return (
    <div className="w-full h-full">
      {/* 로고 */}
      <Logo />
      {/* 유저 프로필(아이디, 매너온도, 모임 성공률) */}
      <div className="h-per25">
        <ProfileCard user={user} />
      </div>
      {/* 완료 모임 내역 카드 */}
      <div className="w-per90 h-per75 m-auto grid grid-cols-1 gap-1">
        {
          list.map(() => <FinishedMeetingCard/>)
        }
      </div>
    </div>
  );
};

export default ProfileHistory;
