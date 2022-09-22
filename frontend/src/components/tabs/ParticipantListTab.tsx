import {
  getParticipantBadge,
  getProgressColor,
} from "../../util/profile";
import { Link } from "react-router-dom";


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
      participantScore: 19,
      userId: "user4",
    },
  ];
  return (  
    <div className="ml-3">
      <p className="text-lg font-semibold mb-4">참여자 ({users.length})</p>

        {users.map(user => 
          <div className="grid grid-cols-2 my-3">

            {/* 
              유저 뱃지, 아이디 
              - 클릭하면 마이페이지로 이동할 수 있도록 연결해두었음 0922 임지민
            */}
            <Link to={`/profile/${user.userId}`}>
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
            </Link>

            {/* 
              태도 점수 바 
              - progress bar가 차오르는 효과 넣기(priority low) 0922 임지민
            */}
            <div>
              <div className="w-full bg-gray-300 rounded-full">
                <div
                  className={`${getProgressColor(user.participantScore)} text-xs font-medium text-white text-right p-0.5 leading-none rounded-full py-1`}
                  style={{ width: `${user.participantScore}%` }}>
                  {user.participantScore}%
                </div>
              </div>
            </div>
          </div>
        )}


    </div>);
};

export default ParticipantListTab;