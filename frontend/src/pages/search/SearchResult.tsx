import { useEffect, useState } from "react";
import FilterBar from "../../components/bars/FilterBar";
import SearchBar from "../../components/bars/SearchBar";
import SubCategoriesBar from "../../components/bars/SubCategoriesBar";
import MeetingCard from "../../components/cards/MeetingCard";
import { getSearchCategoryResult } from "../../api/search";


const SearchResult = () => {
  const [key, setKey] = useState('')
  const [results, setResults] = useState([])

  // key 종류: pc/cc/q
  useEffect(() => {
    const params = window.location.href.split('?')[1].split('=')
    // console.log(params);
    setKey(params[0])
    console.log(decodeURI(params[1]));

    // axios 요청
    // decoding을 해주지 않으면 인코딩된 형태로 나옴
    const value = decodeURI(params[1])
    getSearchCategoryResult(key, value).then((res)=>{
      // 엑쇼스 후 결과 저장
      console.log(res);
      setResults(results.concat(res.data))
    })
  }, [])
  // React Hook useEffect has missing dependencies: 'key' and 'results'. Either include them or remove the dependency array. You can also do a functional update 'setResults(r => ...)' if you only need 'results' in the 'setResults' call  react-hooks/exhaustive-deps
  // 위에 같은 에러가 떠서 key,results를 넣었더니 무한으로 업데이트가 되어서 지워줬더니 해결됨

  const list = [0, 0, 0, 0, 0, 0];
  return (
    <div className="w-full h-full flex flex-col">
      <div className="h-[13vh] flex flex-col">
        {/* 검색바 */}
        <SearchBar handleKeyword={() => {}}/>
        {/* 세부 카테고리 바 */}
        {key==='pc'? <SubCategoriesBar /> : ''}
        
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
