import Logo from "../components/etc/Logo";

const chargeList = [
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
  {
    date: '2022.09.26',
    charged: '0.1'
  },
]

const Wallet = (): JSX.Element => <div>
  <Logo />
  <div className="ml-5">
    <div className="mr-5">
      <p className="text-xl font-semibold mx-2">나의 지갑</p>
      <div className="border border-solid border-blue-200 rounded mt-3 py-6">
        <p className="font-semibold text-right mr-5">ETH</p>
      </div>
      <div className="grid grid-cols-1 mt-2">
        <button type="button" 
          className="bg-blue-200 py-1 text-white rounded"
        >충전하기</button>
      </div>
    </div>

    <div className="mt-5 mx-2">
      <p className="font-semibold">충전 내역</p>
      <div className="overflow-scroll max-h-[100vh]">
        {chargeList.map(chargeItem => 
          <div 
          className="flex justify-between border-b border-solid my-2 py-2">
            <p>{chargeItem.charged} ETH</p>
            <p className="mr-5 text-sm">{chargeItem.date}</p>
          </div>
        )}
      </div>
    </div>
  </div>
</div>;


export default Wallet;