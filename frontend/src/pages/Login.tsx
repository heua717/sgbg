const Login = () => {
  return (
    <div className="w-full h-full">
      {/* 캐치프레이즈1 */}
      <div className="flex justify-center items-center my-10">
        <span className="font-bold text-3xl">happy together! sgbg,</span>
      </div>
      {/* 로고 */}
      <div className="w-per85 mx-auto">
        <img
          className="max-w-full"
          src={process.env.PUBLIC_URL + `/img/sgbg-logo.png`}
          alt="로고"
        />
      </div>
      {/* 캐치프레이즈2 */}
      <div className="flex flex-col justify-center items-center text-lg my-10">
        <span>혼자 하기 힘든,</span>
        <span>혼자 하기 싫은 사람들을 위한</span>
        <span>블록체인 기반 모임 서비스</span>
      </div>
      {/* 카카오 로그인 */}
      <div className="flex justify-center items-center my-20">
        <img
          className="max-w-full"
          src={process.env.PUBLIC_URL + `/img/kakao_login.png`}
          alt="로고"
        />
      </div>
    </div>
  );
};

export default Login;
