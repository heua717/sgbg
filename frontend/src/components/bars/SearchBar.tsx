import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";

const SearchBar = () => {
  return (
    <div className="w-full flex flex-row justify-between items-center p-2">
      <input
        className="w-per90 bg-gray-300 rounded-lg outline-gray-400 p-1"
        placeholder="검색어를 입력해주세요"
      />
      <FontAwesomeIcon className="text-2xl" icon={faSearch} />
    </div>
  );
};

export default SearchBar;
