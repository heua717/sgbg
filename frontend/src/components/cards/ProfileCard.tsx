import { Link } from "react-router-dom";
import {
  getParticipantNickname,
  getParticipantBadge,
  getHostNickname,
  getProgressColor,
  getHostBadge,
} from "../../util/profile";
import UserIdBadge from "../etc/UserIdBadge";
const ProfileCard = (props: any) => {
  const { user } = props;
  return (
    <div className="flex flex-col p-2">
      {/* 유저 뱃지, 아이디 */}
      <UserIdBadge />
      {/* More Info */}
      <Link
        to={`/profile/history/${user.userId}`}
        className="self-end mt-1 mr-2 font-light text-sm"
      >
        More+
      </Link>

      {/* 참여자 정보 */}
      <div className="grid grid-cols-3 mb-1">
        <span className="text-lg">
          {getParticipantNickname(user.participantScore)}
        </span>
        <div className=" col-span-2 my-auto">
          <div className="w-full bg-gray-300 rounded-full">
            <div
              className={`${getProgressColor(
                user.participantScore
              )} text-xs font-medium text-blue-100 text-center p-0.5 leading-none rounded-full`}
              style={{ width: `${user.participantScore}%` }}
            >
              {user.participantScore}%
            </div>
          </div>
        </div>
      </div>
      {/* 방장 정보 */}
      <div className="grid grid-cols-3">
        <span className="text-lg">{getHostNickname(user.hostScore)}</span>
        <div className="col-span-2 flex flex-row justify-end my-auto">
          <span className="font-light text-lg">{user.hostScore}%</span>
          <span className="text-xl">{getHostBadge(user.hostScore)}</span>
        </div>
      </div>
    </div>
  );
};

export default ProfileCard;
