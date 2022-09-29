const CreateWallet = () => {
  const warning = [
    {
      order: 1,
      accent: '지갑 비밀키',
      content: '를 잃어버리지 마세요! 한 번 잃어버리면 복구 할 수 없습니다.'
    },
    {
      order: 2,
      accent: '공유하지 마세요!',
      content: ' 비밀키가 악위적인 사이트에 노출되면 당신의 자산이 유실될 수 있습니다.'
    },
    {
      order: 3,
      accent: '백업',
      content: '을 만들어 두세요! 종이에 적어서 오프라인으로 관리하세요.'
    }
  ];
  return (<div>
  {/* 로고 */}
  <div className="w-per85 mx-auto">
        <img
          className="max-w-full"
          src={process.env.PUBLIC_URL + `/img/sgbg-logo.png`}
          alt="로고"
        />
  </div>
  {/* 캐치프레이즈2 */}
  <div className="mb-10 mx-5 p-3 rounded-lg bg-red-50">
    <p className='text-xl mb-2'>
      <strong>⚠ warning</strong>
    </p>
    <div className='flex flex-col justify-start font-semibold'>
      {warning.map(item => 
        <div className='flex justify-start mb-1'>
          <p className='mr-2'>{item.order}.</p>
          <p>
            <strong className='text-red-100 underline'>{item.accent}</strong>
            {item.content}</p>
        </div>
      )}
    </div>
  </div>
  {/* 지갑 생성하기 버튼 */}
  <div className="my-15 grid grid-cols-1 mx-5">
  <button 
    className='bg-blue-200 rounded py-2 text-white font-semibold'>
      지갑 생성하기</button>
  </div>    

  </div>);
};

export default CreateWallet;