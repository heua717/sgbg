import {
  getParticipantBadge,
  getProgressColor,
} from "../../util/profile";
import { Link } from "react-router-dom";
import { roomMore } from "../../util/room";
import { useEffect, useState } from "react";

// host가 무조건 0번째에 오도록 하고, 나머지는 뒤에 concat하기
const ParticipantListTab = (props: any) => {
  const [members, setMembers] = useState([{
    name: '',
    email: '',
    hostScore: 0,
    memberScore: 0
  }])

  useEffect(()=>{
    console.log("participants list tab = ",props.room.members);
    setMembers(props.room.members)
  }, [])

  return (  
    <div className="ml-3">
      <p className="text-lg font-semibold mb-4">참여자 ({members.length + 1})</p>

        {members.map((member) => 
          <div className="grid grid-cols-2 my-3">

            {/* 
              유저 뱃지, 아이디 
              - 클릭하면 마이페이지로 이동할 수 있도록 연결해두었음 0922 임지민
            */}
            {members.length && (
              <div>
                <Link to={`/profile/${member.name}`}>
                  <div className="flex flex-row justify-start border-b border-gray-300 pb-1">
                    <div className="w-[25px] h-[25px] mr-2">
                      <img
                        className="w-full h-full"
                        src={
                          process.env.PUBLIC_URL + `/img/userBadge` + getParticipantBadge(member.memberScore) + ".png"
                        }
                        alt="사용자 뱃지"
                      />
                    </div>
                    <span className="font-semibold leading-tightl">{member.memberScore}</span>
                  </div>
                </Link>

                {/* 
                  태도 점수 바 
                  - progress bar가 차오르는 효과 넣기(priority low) 0922 임지민
                */}
                <div>
                  <div className="w-full bg-gray-300 rounded-full">
                    <div
                      className={`${getProgressColor(member.memberScore)} text-xs font-medium text-white text-right p-0.5 leading-none rounded-full py-1`}
                      style={{ width: `${member.memberScore}%` }}>
                      {member.memberScore}%
                    </div>
                  </div>
                </div>
              </div>
            )}
          </div>
        )}


    </div>);
};

export default ParticipantListTab;