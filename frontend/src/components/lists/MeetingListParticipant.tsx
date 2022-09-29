import MeetingCard from "../cards/MeetingCard";

const MeetingListParticipant = () => {
    const list = [ 1, 2, 3, 4, 5];
    return (
        <div className="w-full">
            {
                list.map((index) => <MeetingCard key={ index } />)
            }
        </div>
    );
};

export default MeetingListParticipant;