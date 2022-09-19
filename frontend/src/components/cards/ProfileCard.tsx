import { Link } from "react-router-dom";
import {
  getParticipantNickname,
  getParticipantBadge,
} from "../../util/profile";
const ProfileCard = (props: any) => {
  const { user } = props;
  return (
    <div className="flex flex-col p-3">
      {/* 유저 뱃지, 아이디 */}
      <div className="flex flex-row justify-start border-b border-gray-300 pb-1">
        <div className="w-[35px] h-[35px] mr-2">
          <img
            className="w-full h-full"
            src={
              process.env.PUBLIC_URL +
              `/img/userBadge` +
              getParticipantBadge(user.score) +
              ".png"
            }
            alt="사용자 뱃지"
          />
        </div>
        <span className="font-bold text-xl leading-tightl">{user.userId}</span>
      </div>
      {/* More Info */}
      <Link to="" className="self-end mt-1 mr-2 font-light text-sm">
        {" "}
        More+{" "}
      </Link>

      {/* 참여자 정보 */}
      <div className="flex flex-row justify-between">
        <span className="font-semibold text-lg">
          {getParticipantNickname(user.score)}
        </span>
        <progress className="" value={user.score} max="100"></progress>
      </div>
      {/* 방장 정보 */}
    </div>
  );
};

export default ProfileCard;
