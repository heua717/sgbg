import { useEffect } from "react";
import FilterBar from "../../components/bars/FilterBar";
import SearchBar from "../../components/bars/SearchBar";
import SubCategoriesBar from "../../components/bars/SubCategoriesBar";
import MeetingCard from "../../components/cards/MeetingCard";

const SearchResult = () => {
  useEffect(() => {
    const url = window.location.href.split('?')
    const params = url[1].split("=")
    console.log(params);
    
  })
  const list = [0, 0, 0, 0, 0, 0];
  return (
    <div className="w-full h-full flex flex-col">
      <div className="h-[13vh] flex flex-col">
        {/* 검색바 */}
        <SearchBar handleKeyword={() => {}}/>
        {/* 세부 카테고리 바 */}
        <SubCategoriesBar />
        {/* 필터링 바 */}
        <FilterBar />
      </div>

      {/* 검색 결과 리스트 */}
      <div className="w-per95 max-h-[80vh] m-auto grid grid-cols-1 gap-1 overflow-y-auto">
        {list.map(() => (
          <MeetingCard />
        ))}
      </div>
    </div>
  );
};

export default SearchResult;
