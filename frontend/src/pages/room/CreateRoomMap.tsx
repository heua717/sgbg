import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck } from "@fortawesome/free-solid-svg-icons";
import { useEffect, useState } from "react";
import { searchMap } from "../../api/map";
import SearchBar from "../../components/bars/SearchBar";
import SearchMapCard from "../../components/cards/SearchMapCard";

type SearchMapCardProps = {
  id: string;
  place_name: string;
  place_url: string;
  road_address_name: string;
  category_name: string;
  phone: string;
  x: string;
  y: string;
};

type SelectedLocation = {
  id: string;
  name: string;
  x: string;
  y: string;
  road_address: string;
};

const CreateRoomMap = () => {
  const [selected, setSelected] = useState<SelectedLocation>();
  const [searchResults, setSearchResults] = useState<SearchMapCardProps[]>([]);
  const [page, setPage] = useState<number>(1);
  const [isEnd, setIsEnd] = useState<boolean>(false);
  const [keyword, setKeyword] = useState<string>("");

  useEffect(() => {
    handleSearch(keyword, page);
  }, [keyword, page]);

  const handleSearch = (keyword: string, page: number) => {
    searchMap(keyword, page)
      .then(({ data }) => {
        const newList = data.documents;
        const newIsEnd = data.meta.is_end;
        setSearchResults([...newList]);
        setIsEnd(newIsEnd);
      })
      .catch((e) => console.error(e));
  };

  const selectMapCard = (id: string, name: string, x: string, y: string, road_address: string) => {
    setSelected({ id, name, x, y, road_address });
  };

  return (
    <div className="flex flex-col p-2">
      <SearchBar handleKeyword={setKeyword} />
      {selected ? (
          <div className="flex flex-col bg-yellow-100 rounded border border-gray-200 p-2 mb-2">
            <span className="flex flex-row items-center">
              <FontAwesomeIcon className="text-2xl text-blue-300 mr-2" icon={faCheck} />
              <span className="font-semibold">{selected.name}</span>
            </span>
            <span className="font-light text-sm">{selected.road_address}</span>
            <button className="w-full bg-blue-300 text-white rounded p-1">위치 선택하기</button>
          </div>
          
      ) : (
        ""
      )}
      {searchResults.length !== 0 ? (
        <div className="flex flex-col justify-center">
          {searchResults.map((result) => (
            <SearchMapCard
              key={result.id}
              id={result.id}
              road_address_name={result.road_address_name}
              place_name={result.place_name}
              category_name={result.category_name}
              phone={result.phone}
              x={result.x}
              y={result.y}
              selectMapCard={selectMapCard}
            />
          ))}
          <div className="flex flex-row justify-center items-center">
            {page !== 1 ? (
              <span
                className="mr-5"
                onClick={() => {
                  setPage(page - 1);
                }}>{`< 이전`}</span>
            ) : (
              ""
            )}
            {!isEnd ? (
              <span
                onClick={() => {
                  setPage(page + 1);
                }}>{`다음 >`}</span>
            ) : (
              ""
            )}
          </div>
        </div>
      ) : (
        <span className="text-center mt-2 ">검색 결과가 없습니다.</span>
      )}
    </div>
  );
};

export default CreateRoomMap;
