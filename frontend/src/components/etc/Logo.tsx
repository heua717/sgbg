import { Link } from "react-router-dom";

const Logo = () => {
  return (
    <div className="w-full h-[55px] my-2">
      <Link to="/">
        <img
          className="max-h-full mx-auto"
          src={process.env.PUBLIC_URL + `/img/sgbg-logo.png`}
          alt="SgBg 로고"
        />
      </Link>
    </div>
  );
};

export default Logo;
