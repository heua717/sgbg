
const UserReviewResultCard = () => {
    return (
        <div className="flex flex-row justify-end items-center">
            <div className="bg-gray-300 rounded-lg px-1 font-light text-xs mr-1">
                <span className="mr-1">😍</span>
                <span className="mr-1">최고에요!</span>
                <span>{ 3 }</span>
            </div>
            <div className="bg-gray-300 rounded-lg px-1 font-light text-xs mr-1">
                <span className="mr-1">😁</span>
                <span className="mr-1">좋아요!</span>
                <span>{ 3 }</span>
            </div>
            <div className="bg-gray-300 rounded-lg px-1 font-light text-xs">
                <span className="mr-1">😑</span>
                <span className="mr-1">별로에요</span>
                <span>{ 3 }</span>
            </div>
        </div>
    );
};

export default UserReviewResultCard;