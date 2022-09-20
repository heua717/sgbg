import { faSearch, faWallet, faUser } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Navbar = () => {
    return (
        <div className="w-full h-[60px] fixed bottom-0 flex flex-row justify-between items-center bg-gray-100 border-t border-gray-200 px-5">
            <div className="w-[1.875rem]">
                <img src={process.env.PUBLIC_URL + `/img/home.png`}alt="홈아이콘" />
            </div>
            <FontAwesomeIcon icon={faSearch} className="text-blue-600 text-3xl"/>
            <FontAwesomeIcon icon={faWallet} className="text-blue-600 text-3xl"/>
            <FontAwesomeIcon icon={faUser} className="text-blue-600 text-3xl"/>
        </div>
    );
};

export default Navbar;