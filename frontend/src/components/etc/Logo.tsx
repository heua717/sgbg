import { Link } from "react-router-dom";

const Logo = () => {
    return (
    <div className="w-full h-20 my-3">
        <Link to="/">
            <img className="max-h-20 m-auto" src={process.env.PUBLIC_URL + `/img/sgbg-logo.png`} alt="SgBg 로고"/>
        </Link>
    </div>
    );
};

export default Logo;