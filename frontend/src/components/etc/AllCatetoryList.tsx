import React from 'react';
import { useRecoilState } from 'recoil';
import { roomMore } from '../../store/room';
import { inputRoomInfo } from '../../store/room';
import { useNavigate } from 'react-router-dom';

const AllCatetoryList = () => {
  const [room, setRoom] = useRecoilState<roomMore>(inputRoomInfo)
  const navigate = useNavigate();

  const OnClick = (e: React.MouseEvent<HTMLElement>) => {
    const categoryTag = e.target as HTMLElement;
    const id = categoryTag.innerHTML
    console.log(id);
    
    setRoom({
      ...room,
      category: id
    });
    
    navigate(-1)
  };
  return (
    <div>
      {/* 
      아래 p 태그 요소를 클릭할 때마다 recoil에 저장됨(저장되는 것은 하나이기 때문에 새로 클릭하면 값이 갱신됨)
      클릭한 것은 배경이 바뀌던가, 밑줄을 긋던가 하는 방법으로 표시가 됨
    */}
    <div>
      <h4 className="bg-gray-300 py-1 pl-2" id="이색놀거리">이색 놀거리</h4>
      <p className="pl-5 my-1" onClick={OnClick}>방탈출</p>
      <p className="pl-5 my-1" onClick={OnClick}>보드게임</p>
    </div>

    <div>
      <h4 className="bg-gray-300 py-1 pl-2">야외활동</h4>
      <p className="pl-5 my-1" onClick={OnClick}>스포츠</p>
      <p className="pl-5 my-1" onClick={OnClick}>레저활동</p>
      <p className="pl-5 my-1" onClick={OnClick}>놀이공원</p>
      <p className="pl-5 my-1" onClick={OnClick}>공연</p>
    </div>

    <div>
      <h4 className="bg-gray-300 py-1 pl-2">원데이 클래스</h4>
      <p className="pl-5 my-1" onClick={OnClick}>공예</p>
      <p className="pl-5 my-1" onClick={OnClick}>댄스/뮤직</p>
      <p className="pl-5 my-1" onClick={OnClick}>드로잉/사진/글</p>
      <p className="pl-5 my-1" onClick={OnClick}>피트니스/스포츠</p>
      <p className="pl-5 my-1" onClick={OnClick}>뷰티/마인드</p>
    </div>

    <div>
      <h4 className="bg-gray-300 py-1 pl-2">OTT 공유</h4>
      <p className="pl-5 my-1" onClick={OnClick}>음악</p>
      <p className="pl-5 my-1" onClick={OnClick}>영상</p>
    </div>
    
    <div>
      <h4 className="bg-gray-300 py-1 pl-2">봉사활동</h4>
      <p className="pl-5 my-1" onClick={OnClick}>기부</p>
      <p className="pl-5 my-1" onClick={OnClick}>자원봉사</p>
    </div>

    <div>
      <h4 className="bg-gray-300 py-1 pl-2">기타</h4>
      <p className="pl-5 my-1" onClick={OnClick}>기타</p>
    </div>
    </div>
  );
};

export default AllCatetoryList;