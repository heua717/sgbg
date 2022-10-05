<<<<<<< HEAD
import React from "react";


=======
>>>>>>> c1b642565ab9f7bd5948dde53597bb912c7d4582
const BigLogo = () => {
  return (
    // 도로록 열리게  bg-blue-600
    <div className="top-0 w-full px-5 h-[40vh] text-white">
      <img
        className="w-full h-full"
        src={process.env.PUBLIC_URL + `/img/sgbg-logo.png`}
        alt="SgBg 로고"
      />
    </div>

  );
};

export default BigLogo;