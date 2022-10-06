
const FilterBar = () => {
    return (
        <div className="w-full">
            <div className="w-per90 flex flex-row justify-start items-center border-t border-gray-300 mx-auto px-1 py-2">
                <div className="font-light text-xs mr-5">최신순</div>
                <div className="font-light text-xs mr-5">찜많은순</div>
                <div className="font-light text-xs mr-5">최신등록순</div>
                <div className="font-light text-xs mr-5">가격대별 ▼</div>
            </div>
        </div>
    );
};

export default FilterBar;