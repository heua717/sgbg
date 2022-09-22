import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "react-router-dom";

const BtnCreateRoom = () => {
  return (
    <div className="fixed bottom-[65px] right-[5px] w-14 h-14 bg-yellow-100 rounded-full flex justify-center items-center">
      <Link to="/meeting/create">
        <FontAwesomeIcon icon={faPlus} className="text-4xl text-blue-200" />
      </Link>
    </div>
  );
};

export default BtnCreateRoom;
