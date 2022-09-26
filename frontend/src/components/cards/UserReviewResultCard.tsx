
const UserReviewResultCard = () => {
    return (
        <div className="flex justify-start items-center my-2">
            <div className="bg-gray-300 rounded-lg p-1 font-light text-xs mr-1">
                <span className="mr-1">😍</span>
                <span className="mr-1">최고에요!</span>
                <span className="ml-1 font-bold">{ 3 }</span>
            </div>
            <div className="bg-gray-300 rounded-lg p-1 font-light text-xs mr-1">
                <span className="mr-1">😁</span>
                <span className="mr-1">좋아요!</span>
                <span className="ml-1 font-bold">{ 3 }</span>
            </div>
            <div className="bg-gray-300 rounded-lg p-1 font-light text-xs">
                <span className="mr-1">😑</span>
                <span className="mr-1">별로에요</span>
                <span className="ml-1 font-bold">{ 3 }</span>
            </div>
        </div>
    );
};

export default UserReviewResultCard;