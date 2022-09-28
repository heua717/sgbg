import MeetingCard from "../../components/cards/MeetingCard";
import Logo from "../../components/etc/Logo";
import RoomTabs from "../../components/tabs/RoomTabs";


const ReadRoom = () => {
  return (<div>
    <div>
      <Logo/>

      <div className="mx-3">
        <MeetingCard/>
      </div>

      <div className=" mt-3 mx-3">
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
