import { useEffect, useState } from "react";
// import FilterBar from "../../components/bars/FilterBar";
import SearchBar from "../../components/bars/SearchBar";
import SubCategoriesBar from "../../components/bars/SubCategoriesBar";
import MeetingCard from "../../components/cards/MeetingCard";
import { getSearchCategoryResult } from "../../api/search";
import data from "../../util/category";
import { getSearchKeywordResult } from "../../api/search";


const SearchResult = () => {
  const [key, setKey] = useState('')
  const [value, setValue] = useState('')
  const [childCategories, setChildCategories] = useState(Array<string>)
  const [results, setResults] = useState([])

  // key 종류: pc/cc/q
  useEffect(() => {
    const params = window.location.href.split('?')[1].split('=')
    // console.log(params);
    setKey(params[0])
    console.log(decodeURI(params[1]),'----', key);

    // axios 요청
    // decoding을 해주지 않으면 인코딩된 형태로 나옴
    const value = decodeURI(params[1])
    // 디코딩한 것을 저장
    setValue(value)

    // axios
    if (params[0] === 'parentCategory' || params[0] === 'childCategory') {
      getSearchCategoryResult(params[0], value).then((res)=>{
        // console.log(res);
        console.log(key, '===', value);
        
        setResults(res.data)
      }).catch((err) => {
        console.log(err);
        // console.log(value); 
      })
    } else if (params[0] === 'keyword') {
      getSearchKeywordResult(value).then((res) => {
        console.log(res);
        setResults(res.data)
      }).catch((err)=>{
        console.log(err);
        
      })
    }

    if (params[0] === 'parentCategory') {
      
      data.forEach((datum) => {
        if(value === datum.parent) {
          setChildCategories(datum.childs)
          // console.log('in if=', value, datum.childs);
        }
      })
    }
  }, [])

  const [keyword, setKeyword] = useState<string>("");

  const handleSearch = (keyword: string) => {
    console.log(keyword);
    
    getSearchKeywordResult(keyword)
    .then((res)=>{
      console.log('keyword search=', res.data);
    }).catch((err)=>{
      console.log('keyword search error=', err);
    })    
  };

  useEffect(() => {
    console.log('search result = ', keyword);
    
    // handleSearch(keyword);
  }, [])
  // React Hook useEffect has missing dependencies: 'key' and 'results'. Either include them or remove the dependency array. You can also do a functional update 'setResults(r => ...)' if you only need 'results' in the 'setResults' call  react-hooks/exhaustive-deps
  // 위에 같은 에러가 떠서 key,results를 넣었더니 무한으로 업데이트가 되어서 지워줬더니 해결됨

  
  return (      
    <div>
      <SearchBar name={"searchResult"} handleKeyword={setKeyword}/>
      <p className="ml-3 my-2 text-lg mr-1"><strong>{value}</strong>에 대한 검색결과</p>
      {/* 세부 카테고리 바 */} 
      {key==='parentCategory'? <SubCategoriesBar childCategories={childCategories} />  : ''}

      {/* 검색 결과 리스트 */}
      <div className="w-per95 max-h-[80vh] m-auto grid grid-cols-1 gap-1 overflow-y-auto">
        {results.map((index, room) => (
          <MeetingCard key={index} room={room}/>
        ))}
      </div>
    </div>
  );
};

export default SearchResult;
