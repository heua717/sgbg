import SearchBar from "../../components/bars/SearchBar";
import AllCatetoryList from "../../components/etc/AllCatetoryList";

const Search = () => {
  return (<div className="mt-5">
    <SearchBar />
    <div className="mx-2 mt-5">
      <AllCatetoryList />
    </div>
  </div>);
};

export default Search;