import FinishedMeetingCard from "../cards/FinishedMeetingCard";

const FinishedMeetingListParticipant = () => {
  const list = [1, 2, 3, 4, 5];
  return (
    <div className="w-full">
      {list.map((index) => (
        <FinishedMeetingCard key={index} />
      ))}
    </div>
  );
};

export default FinishedMeetingListParticipant;
