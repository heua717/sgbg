import FinishedMeetingCard from "../cards/FinishedMeetingCard";

const FinishedMeetingListHost = () => {
  const list = [1, 2, 3, 4, 5];
  return (
    <div className="w-full">
      {list.map((index) => (
        <FinishedMeetingCard key={index} />
      ))}
    </div>
  );
};

export default FinishedMeetingListHost;
