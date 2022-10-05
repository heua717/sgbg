import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import Swal from "sweetalert2";
import { checkWallet, makeWallet } from "../../api/wallet";
import Logo from "../../components/etc/Logo";
import { auth } from "../../store/auth";

const CreateWallet = () => {
  const navigator = useNavigate();
  const reg = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8}/;
  const userAuth = useRecoilValue(auth);
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

  useEffect(() => {
    if (!userAuth.isLogined) {
      Swal.fire({
        position: 'center',
        icon: 'warning',
        title: '로그인이 필요합니다.',
        showConfirmButton: false,
        timer: 1500
      });
      navigator("/login");
      return;
    } else {
      checkWallet().then(({data}) => {
        if (data.statusCode === 2000) {
          Swal.fire({
            position: 'center',
            icon: 'warning',
            title: '이미 지갑이 존재합니다.',
            showConfirmButton: false,
            timer: 1500
          });
          navigator("/wallet");
          return;
        }
      })
    }
  },[]);

  const handleCreateWallet = () => {
    Swal.fire({
      title: "사용할 지갑 비밀번호를 정해주세요.",
      text:"영문,숫자를 합하여 8자가 되어야합니다.",
      input: "password",
      inputAttributes: {
        autocapitalize: "off",
      },
      showCancelButton: true,
      confirmButtonText: "제출",
      showLoaderOnConfirm: true,
      preConfirm: (pw) => {
        if (pw.match(reg)) {
          return makeWallet(pw)
          .then(({ data }) => {
            if (data.statusCode === 2000) {
              Swal.showValidationMessage(`지갑 생성에 성공했습니다.`);
              navigator("/wallet");
            } else {
              throw new Error(data.message);
            }
          }).catch((error) => {
            Swal.showValidationMessage(`지갑 생성에 실패했습니다.`);
          })
        } else {
          Swal.showValidationMessage(`비밀번호가 적절하지 않습니다.`);
        }
      },
      allowOutsideClick: false,
    });
  }
  return (<div>
  {/* 로고 */}
  <Logo/>
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
        className='bg-blue-200 rounded py-2 text-white font-semibold'
      onClick={handleCreateWallet}>
      지갑 생성하기</button>
  </div>    

  </div>);
};

export default CreateWallet;