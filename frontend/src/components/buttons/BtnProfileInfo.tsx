import {
  getParticipantNickname,
  getHostNickname,
  getParticipantBadge,
  getHostBadge,
} from "../../util/profile";

type BtnProfileInfoProps = {
  type: "Participant" | "Host";
  active: boolean;
  user: {
    participantScore: number;
    hostScore: number;
    userId: string;
  };
};

const BtnProfileInfo = ({ type, user, active}: BtnProfileInfoProps) => {
  return (
    <div className={`${active? "bg-blue-300 text-white": "text-black"} flex flex-col justify-center items-center rounded border border-gray-200 p-2 my-2`}>
      <span className="flex flex-row">
        {type === "Participant" ? (
          <img
            className="w-6 mr-1"
            src={
              process.env.PUBLIC_URL +
              `/img/userBadge${getParticipantBadge(user.participantScore)}.png`
            }
            alt="참여자 뱃지"
          />
        ) : (
          <span className="text-lg mr-1">{getHostBadge(user.hostScore)}</span>
        )}
        <span>
          {type === "Participant"
            ? getParticipantNickname(user.participantScore)
            : getHostNickname(user.hostScore)}
        </span>
      </span>
      <span>{type === "Participant" ? user.participantScore : user.hostScore}%</span>
    </div>
  );
};

export default BtnProfileInfo;
