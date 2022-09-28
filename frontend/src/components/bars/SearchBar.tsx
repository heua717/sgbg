import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { useState } from "react";

type SearchBarProps = {
  handleKeyword : (e: string) => void,
}

const SearchBar = (props:SearchBarProps) => {
  const [keyword, setKeyword] = useState<string>("");
  const handleChange = (e:React.ChangeEvent<HTMLInputElement>) => {
    setKeyword(e.target.value);
  }
  const handleSearch = () => {
    props.handleKeyword(keyword);
  }
  return (
    <div className="w-full flex flex-row justify-between items-center p-2">
      <input
        className="w-per90 bg-gray-300 rounded-lg outline-gray-400 p-1"
        onChange={handleChange}
        placeholder="검색어를 입력해주세요"
      />
      <FontAwesomeIcon className="text-3xl" icon={faSearch} onClick={handleSearch} />
    </div>
  );
};

export default SearchBar;
