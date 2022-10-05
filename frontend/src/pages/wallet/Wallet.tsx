import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import Swal from "sweetalert2";
import { chargeWallet, getWalletHistory, postWallet } from "../../api/wallet";
import Logo from "../../components/etc/Logo";
import { auth } from "../../store/auth";

type WalletHistory = {
  id: string;
  totalMoneyBeforeTransaction: number;
  money: number;
  date: string;
  type: "charge" | "enter" | "exit";
  roomId: string;
  roomName: string;
};
type Wallet = {
  id: string;
  ownerId: string;
  address: string;
  cash: number;
};

const Wallet = (): JSX.Element => {
  const navigator = useNavigate();
  const [walletHistories, setWalletHistoies] = useState<WalletHistory[]>([]);
  const [cash, setCash] = useState<number>(0);
  const userAuth = useRecoilValue(auth);
  useEffect(() => {
    if (!userAuth.isLogined) {
      Swal.fire({
        position: "center",
        icon: "warning",
        title: "로그인이 필요합니다.",
        showConfirmButton: false,
        timer: 1500,
      });
      navigator("/login");
      return;
    }
    Swal.fire({
      title: "비밀번호를 입력하세요",
      input: "password",
      inputAttributes: {
        autocapitalize: "off",
      },
      showCancelButton: false,
      confirmButtonText: "제출",
      showLoaderOnConfirm: true,
      preConfirm: (pw: string) => {
        return postWallet(pw)
          .then(({ data }) => {
            if (data.statusCode === 2000) {
              setCash(data.cash);
              getWalletHistory()
                .then(({ data }) => {
                  console.log(data);

                  if (data.statusCode === 2000) {
                    setWalletHistoies([...data.walletHistoryList]);
                  } else {
                    throw new Error(data.message);
                  }
                })
                .catch((error) => {
                  Swal.showValidationMessage(`Request failed: ${error}`);
                  navigator("/");
                });
            } else {
              throw new Error(data.message);
            }
          })
          .catch((error) => {
            Swal.fire({
              position: "center",
              icon: "warning",
              title: "잘못된 비밀번호입니다. &#x1F97A",
              showConfirmButton: false,
              timer: 1500,
            });
            navigator("/");
          });
      },
      allowOutsideClick: false,
    });
  }, []);

  const handleCharge = () => {
    Swal.fire({
      title: "충전할 금액(원)을 입력해주세요.",
      input: "number",
      showCancelButton: true,
      confirmButtonText: "충전",
      showLoaderOnConfirm: true,
      preConfirm: (money) => {
        return chargeWallet(money)
          .then(({ data }) => {
            if (data.statusCode === 2010) {
              setCash(data.cash);
              getWalletHistory()
                .then(({ data }) => {
                  if (data.statusCode !== 2000) {
                    throw new Error(data.message);
                  }
                  setWalletHistoies([...data.walletHistorys]);
                })
                .catch((error) => {
                  Swal.showValidationMessage(`Request failed: ${error}`);
                  navigator("/");
                });
            } else {
              throw new Error(data.message);
            }
          })
          .catch((error) => {
            Swal.fire({
              position: "center",
              icon: "error",
              title: "충전이 실패하였습니다. &#x1F97A",
              showConfirmButton: false,
              timer: 1500,
            });
            navigator("/");
          });
      },
      allowOutsideClick: false,
    });
  };

  return (
    <div>
      <Logo />
      <div className="ml-5">
        <div className="mr-5">
          <p className="text-xl font-semibold mx-2">나의 지갑</p>
          <div className="flex flex-row justify-between border border-solid border-blue-200 rounded mt-3 py-6">
            <p className="font-semibold">{cash}</p>
            <p className="font-semibold text-right mr-5">ETH</p>
          </div>
          <div className="grid grid-cols-1 mt-2">
            <button
              type="button"
              className="bg-blue-200 py-1 text-white rounded"
              onClick={handleCharge}>
              충전하기
            </button>
          </div>
        </div>

        <div className="mt-5 mx-2">
          <p className="font-semibold">충전 내역</p>
          <div className="overflow-scroll max-h-[100vh]">
            {walletHistories.map((history) => (
              <div className="flex justify-between border-b border-solid my-2 py-2">
                <div className="flex flex-row justify-between">
                  <p className="mr-5 text-sm">{history.date && history.date}</p>
                  {history.roomId ? (
                    <p className="mr-5 text-sm">{history.roomName && history.roomName}</p>
                  ) : (
                    ""
                  )}
                </div>
                <div className="flex flex-col">
                  <p>
                    <p>{history.type === "exit" ? "-" : "+"}</p>
                    {history.money && history.money} ETH
                  </p>
                  <p className="text-xs">
                    {history.totalMoneyBeforeTransaction &&
                      history.money &&
                      history.totalMoneyBeforeTransaction + history.money}{" "}
                    ETH
                  </p>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Wallet;
