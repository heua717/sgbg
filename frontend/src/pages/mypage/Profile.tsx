import ProfileCard from "../../components/cards/ProfileCard";
import Logo from "../../components/etc/Logo";
import ProfileTab from "../../components/tabs/ProfileTab";

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
      <div className="h-per25">
        <ProfileCard user={user} />
      </div>
      {/* 참여한 모임 탭 */}
      <div className="w-full h-per70">
        <ProfileTab />
      </div>
    </div>
  );
};

export default Profile;
