import ProfileCard from "../../components/cards/ProfileCard";

const Profile = () => {
  const user = {
    score: 75,
    userId: "namm",
  };

  return (
    <div>
      {/* 로고 */}
      <div className="w-full h-20 my-3">
        <img
          className="max-h-20 m-auto"
          src={process.env.PUBLIC_URL + `/img/sgbg-logo.png`}
          alt="SgBg 로고"
        />
      </div>

      {/* 유저 프로필(아이디, 매너온도, 모임 성공률) */}
      <ProfileCard user={user} />
    </div>
  );
};

export default Profile;
