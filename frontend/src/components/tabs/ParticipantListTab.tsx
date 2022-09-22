import {
  getParticipantBadge,
  getProgressColor,
} from "../../util/profile";


const ParticipantListTab = () => {
  const users = [
    {
      participantScore: 99,
      userId: "host",
    },
    {
      participantScore: 23,
      userId: "user1",
    },
    {
      participantScore: 72,
      userId: "user2",
    },
    {
      participantScore: 52,
      userId: "user3",
    },
    {
      participantScore: 46,
      userId: "user4",
    },
  ];
  return (  
    <div className="ml-3">
      <p className="text-lg font-semibold mb-4">참여자 ({users.length})</p>

        {/* 유저 뱃지, 아이디 */}
        {users.map(user => {
          <div className="grid grid-cols-2">
            <div className="flex flex-row justify-start border-b border-gray-300 pb-1">
              <div className="w-[25px] h-[25px] mr-2">
                <img
                  className="w-full h-full"
                  src={
                    process.env.PUBLIC_URL + `/img/userBadge` + getParticipantBadge(user.participantScore) + ".png"
                  }
                  alt="사용자 뱃지"
                />
              </div>
              <span className="font-semibold leading-tightl">{user.userId}</span>
            </div>

            {/* 태도 점수 바 */}
            <div>
              <div className="w-full bg-gray-300 rounded-full">
                <div
                  className={`${getProgressColor(user.participantScore)} text-xs font-medium text-blue-100 text-center p-0.5 leading-none rounded-full`}
                  style={{ width: `${user.participantScore}%` }}>
                  {user.participantScore}%
                </div>
              </div>
            </div>
          </div>
        })}


    </div>);
};

export default ParticipantListTab;