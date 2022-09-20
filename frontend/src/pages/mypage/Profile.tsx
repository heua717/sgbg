import ProfileCard from "../../components/cards/ProfileCard";
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
      <div className="w-full mt-2">
        <img
          className="max-h-16 m-auto"
          src={process.env.PUBLIC_URL + `/img/sgbg-logo.png`}
          alt="SgBg 로고"
        />
      </div>
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
