import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCommentMedical } from '@fortawesome/free-solid-svg-icons'
import { Link, useParams } from "react-router-dom";
import { getParticipantBadge } from "../../util/profile";
import { createComment } from "../../api/community";
import React, { useState } from "react";

const CommunityTab = () => {
  const comments = [
    {
      userId: "host",
      participantScore: 88,
      comment: "안녕하세요~~ 블루 하와이안 만들기 모임장입니다!! 참여자 분들은 내일 2시까지 삼성역 5번 출구에서 만나요~",
      createdDate: "1시간 전"
    },
    {
      userId: "user1",
      participantScore: 43,
      comment: "제 온도가 가장 낮으니 가장 먼저 도착하도록 하겠습니다. 열어분 걱정 마세요",
      createdDate: "1시간 전"
    },
    {
      userId: "user1",
      participantScore: 43,
      comment: "제 온도가 가장 낮으니 가장 먼저 도착하도록 하겠습니다. 열어분 걱정 마세요",
      createdDate: "1시간 전"
    },
    {
      userId: "user1",
      participantScore: 43,
      comment: "제 온도가 가장 낮으니 가장 먼저 도착하도록 하겠습니다. 열어분 걱정 마세요",
      createdDate: "1시간 전"
    },
  ];

  const [ comment, setComment ] = useState('')
  const onChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const {value} = e.target
    setComment(value)
  };

  let {meeting_id} = useParams()

  const onClickSubmit = (e: React.MouseEvent) => {
    // 버튼을 누르면 usestate content에 작성한 내용이 저장됨
    // const value = e.target
    // console.log(comment); // ok
    
    // 버튼을 누르면 axios 요청 with content and room_id
    // console.log(meeting_id, comment); ok
    createComment({
      content: comment,
      room_id: Number(meeting_id)
    }).then((res)=>{
      console.log(res);
      // 다 되면 현재 페에지 리다이렉트
    })    
  }

  // 컴포넌트 create될 때 axios

  return (
    <div>
      <div className="grid grid-cols-12">
        <textarea 
            name="community" 
            id="community" 
            onChange={onChange}
            style={{resize: "none", border: "5px solid #5280C0"}}
            className="rounded-l col-start-1 col-end-12"
            placeholder="글을 작성해주세요"
            cols={40} 
            rows={3}></textarea> 
        <button type="submit" onClick={onClickSubmit}
          className="bg-blue-200 rounded-r text-white text-lg border-none">
          <FontAwesomeIcon icon={faCommentMedical}/>
        </button>
      </div>
      {/* 작성된 게시글 */}
      <div className="h-[75vh]">
        {comments.map(comment=>
          <div className="my-5 border rounded p-2">
            <Link to={`/profile/${comment.userId}`}>
            <div className="flex flex-row justify-start border-b border-gray-300 pb-1">
              <div className="w-[25px] h-[25px] mr-2">
                <img
                  className="w-full h-full"
                  src={
                    process.env.PUBLIC_URL + `/img/userBadge` + getParticipantBadge(comment.participantScore) + ".png"
                  }
                  alt="사용자 뱃지"
                />
              </div>
              <span className="font-semibold leading-tightl">{comment.userId}</span>
            </div>
            </Link>

            <div className="rounded p-2">
              <p>{comment.comment}</p>
            </div>
            <div className="font-semibold text-xs text-right mt-2 mr-2">
              <p>{comment.createdDate}</p>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default CommunityTab;