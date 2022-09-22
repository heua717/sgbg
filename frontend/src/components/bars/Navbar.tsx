import { faSearch, faWallet, faUser } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "react-router-dom";


const Navbar = () => {
  return (
    <div className="w-full h-[7vh] fixed bottom-0 flex flex-row justify-between items-center bg-gray-100 border-t border-gray-200 px-5">
      <Link to="/">
        <div className="w-[1.875rem]">
            <img src={process.env.PUBLIC_URL + `/img/home.png`} alt="홈아이콘" />
        </div>
      </Link>
      <Link to="search/">
        <FontAwesomeIcon icon={faSearch} className="text-blue-600 text-3xl" />
      </Link>
      <Link to="/wallet">
        <FontAwesomeIcon icon={faWallet} className="text-blue-600 text-3xl" />
      </Link>
      <Link to="profile/jimin">
        <FontAwesomeIcon icon={faUser} className="text-blue-600 text-3xl" />
      </Link>
    </div>
  );
};

export default Navbar;
