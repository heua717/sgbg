import { Link } from "react-router-dom";
import ProfileCard from "../../components/cards/ProfileCard";
import Logo from "../../components/etc/Logo";
import MyPageTab from "../../components/tabs/MyPageTab";


const Profile = () => {
  const user = {
    participantScore: 75,
    hostScore: 30,
    userId: "namm",
  };



  return (
    <div className="w-full h-full">
      {/* 로고 */}
      <Logo />
      {/* 유저 프로필(아이디, 매너온도, 모임 성공률) */}
      <div className="h-per25 flex flex-col px-2 mx-2 mb-1">
        {/* 유저 뱃지, 아이디, 완료 이력 보기 */}
        <div className="flex flex-col border-b border-gray-200 pb-2">
          <div className="flex flex-row justify-between items-end">
            <span className="font-bold text-xl leading-tight">jimin</span>
            <button className="bg-slate-400 rounded p-1">로그아웃</button>
          </div>
          <Link className="font-light text-xs mt-2" to={`/profile/history/${user.userId}`}>
            {" "}
            {"> 완료한 모임 이력 보기"}
          </Link>
        </div>

        <ProfileCard user={user} />
      </div>
      {/* 참여한 모임 탭 */}
      <MyPageTab />
    </div>
  );
};

export default Profile;
