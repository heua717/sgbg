import MeetingCard from "../cards/MeetingCard";

const ProfileTab = () => {
    // axios요청으로 모임 목록 가져오기
    const roomList = [
        {}, {}, {}, {}, {}
    ];
  return (
    <div className="w-full p-3 pt-0">
      <div className="w-full h-per10 grid grid-cols-2 bg-gray-100 mb-1">
        <button className="text-sm border border-gray-300 border-r-0 p-1">참여한 모임</button>
        <button className="text-sm border border-gray-300 p-1">만든 모임</button>
      </div>
      {/* 모임 카드 */}
          <div className="w-full max-h-per80 overflow-scroll">
              {
                  roomList.map((room) => <MeetingCard room={ room }/> )
              }
      </div>
    </div>
  );
};

export default ProfileTab;
