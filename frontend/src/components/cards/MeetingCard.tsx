import { faMapMarkerAlt } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useEffect, useState } from "react";
import { formatDate } from "../../util/room";
// import { useEffect } from "react";
// import { useRecoilState } from "recoil";
// import { roomMore } from "../../util/room";
// import { inputRoomInfo } from "../../store/room";
// import { useState } from "react";


const MeetingCard = (props:any): JSX.Element => {
  const [meetingText, setMeetingText] = useState('모집 중')

  const getStateBadgeColor = () => {
    return 'bg-yellow-100';
  }
  const getMemberBadgeColor = () => {
    return 'bg-gray-100';
  }

  const getIsImminent = () =>{
    const monthNow = new Date().getMonth() + 1
    const dateNow = new Date().getDate()

    const endDate = props.room.endDate.split('T')[0]
    const endDateSplited = endDate.split('-')
    const endDateMonth = Number(endDateSplited[1])
    const endDateDate = Number(endDateSplited[2])
    
    // console.log(endDateMonth, endDateDate );
    
    
    // 달이 같고, 일의 차이가 1 이하이면 마감 임박
    if (monthNow === endDateMonth && (endDateDate - dateNow) <= 1) {
      // console.log(monthNow, endDateMonth, endDateDate, dateNow);
      
      setMeetingText('마감 임박')
    } else {
      setMeetingText('모집 중')
    }
    // 아니면 모집 중    
  }

  useEffect(()=>{
    // console.log('hi');
    
    getIsImminent();
  }, [props.room.endDate])

  return (
    // w-per100 h-per100 
    <div className="flex flex-row border rounded-lg p-2 mb-2">
      {/* 모임 이미지, 뱃지 */}
      <div className="w-per45 mr-5 relative">
        <div className={`absolute top-0 left-0 rounded ${getStateBadgeColor()} text-sm font-semibold px-2 py-1`}>{meetingText}</div>
        <img
          className="w-full h-full rounded"
          src={process.env.PUBLIC_URL + `/img/${props.room.parentCategory}_${props.room.childCategory.replace('/', '')}.jpg`}
          alt="소분류 카테고리 사진"></img>

        <div className={`absolute bottom-0 right-0 rounded ${getMemberBadgeColor()} text-sm font-semibold px-2`}>
          {props.room.minUser? props.room.minUser : ''} / {props.room.maxUser? props.room.maxUser : ''}
        </div>
      </div>
      {/* 모임 설명 */}
      <div className="w-per55 flex flex-col justify-between"> {/* 설명 부분 전체를 묶는 div */}

        {/* 카테고리, 모임명, 위치 */}
        {/* 설명 1,2번째 줄을 묶는 div */}
        <div>
          <div className="flex justify-between">
            <span 
              className="text-xs text-blue-200 font-bold border border-blue-200 border-1 py-0.5 px-1.5 rounded-xl">
              {props.room.childCategory? props.room.childCategory : ''}
            </span>
            <span className="text-xs pt-1">
              <FontAwesomeIcon icon={faMapMarkerAlt} className="text-xs mr-1" />        
                {props.room.location.roadAddress? props.room.location.roadAddress : ''}
            </span>
          </div>
          <div className="text-sm font-semibold py-2">
            <span>
              {props.room.title? props.room.title : ''}
            </span>
          </div>
        </div>
        {/* 모임일자, 가격 */}
        <div className="flex justify-between">
          <div >
            <p className="text-sm">{props.room.reservationDate? formatDate(props.room.reservationDate).split('  ')[0] : '모이는 날'}</p>
            <p className="text-sm">{props.room.reservationDate? formatDate(props.room.reservationDate).split('  ')[1] : ''}</p>
          </div>

          <span className="text-sm font-semibold">{props.room.price? props.room.price : '0'}원</span>
        </div>
      </div>
    </div>
  );
};

export default MeetingCard;
