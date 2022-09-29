import MeetingCard from "../../components/cards/MeetingCard";
import Logo from "../../components/etc/Logo";
import RoomTabs from "../../components/tabs/RoomTabs";

const ReadRoom = () => {
  return (
    <div>
      <div>
        <Logo />
        <div className="px-3">
          <MeetingCard />
          <button type="button" className="w-full text-center font-bold bg-yellow-100 rounded p-1">
            참여하기
          </button>
        </div>
      </div>
      {/* 탭 구현 */}
      <div>
        <RoomTabs />
      </div>
    </div>
  );
};

export default ReadRoom;
