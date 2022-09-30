import { Link, useNavigate, useParams } from "react-router-dom";
import ProfileCard from "../../components/cards/ProfileCard";
import Logo from "../../components/etc/Logo";
import MyPageTab from "../../components/tabs/MyPageTab";
import { auth } from "../../store/auth";
import { useRecoilState } from "recoil";
import { useEffect } from "react";

const Profile = () => {
  const user = {
    participantScore: 75,
    hostScore: 30,
    userId: "namm",
  };
  const { user_id } = useParams<{ user_id: string }>();
  const [userAuth, setUserAuth] = useRecoilState(auth);
  const navigator = useNavigate();
  const handleLogout = () => {
    //recoil 초기화
    setUserAuth({ isLogined: false, email: "" });
    navigator("/");
  }

  useEffect(() => {
    const decodedId = user_id && decodeURI(user_id);
    if (decodedId) {
      // 마이페이지 정보 불러오기
      console.log(decodedId);
    } else {
      navigator("/");
    }
  })

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
            {userAuth.isLogined && user_id && userAuth.email === decodeURI(user_id) ? (
              <button className="bg-slate-400 rounded p-1" onClick={handleLogout}>로그아웃</button>
            ) : (
              ""
            )}
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
