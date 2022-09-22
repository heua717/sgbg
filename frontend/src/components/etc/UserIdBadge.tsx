import {
  getParticipantNickname,
  getParticipantBadge,
  getHostNickname,
  getProgressColor,
  getHostBadge
} from "../../util/profile";

const UserIdBadge = () => {
  return (
    <div>
      {/* 유저 뱃지, 아이디 */}
      <div className="flex flex-row justify-start border-b border-gray-300 pb-1">
      <div className="w-[35px] h-[35px] mr-2">
        <img
          className="w-full h-full"
          src={
            process.env.PUBLIC_URL + `/img/userBadge` + getParticipantBadge(99) + ".png"
          }
          alt="사용자 뱃지"
        />
      </div>
      <span className="font-bold text-xl leading-tightl">jimin</span>
      </div>
      
    </div>
  );
};

export default UserIdBadge;
