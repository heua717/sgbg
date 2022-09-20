import MeetingCard from "../../components/cards/MeetingCard";
import RoomTabs from "../../components/tabs/RoomTabs";
import Logo from "../../components/Logo"

const ReadRoom = () => {
  

  return (<div>
    <div className="mx-6">
      <Logo/>

      <div>
        <MeetingCard/>
      </div>

      <div className="grid grid-cols-1 mt-3">
        <button type="button" className="text-center font-bold bg-yellow-100 rounded py-1">참여하기</button>
      </div>
    </div>

    {/* 탭 구현 */}
    <div>
      <RoomTabs/>
    </div>

  </div>);
};

export default ReadRoom;
