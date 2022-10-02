import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCommentMedical } from '@fortawesome/free-solid-svg-icons'
import { Link, useNavigate, useParams } from "react-router-dom";
import { getParticipantBadge } from "../../util/profile";
import { createComment, readComment, deleteComment, updateComment } from "../../api/community";
import React, { useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { auth } from "../../store/auth";
import Swal from "sweetalert2";


const CommunityTab = (props: any) => {

  const navigate = useNavigate()
  let {meeting_id} = useParams()

  const [commentList, setCommentList] = useState([{
    content: '',
    commentId: 0,
    createdAt: '',
    username: '',
    userScore: 0,
    }])

  const [ comment, setComment ] = useState('')
  const [userAuth] = useRecoilState(auth);


  const onChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const {value} = e.target
    setComment(value)
  };


  const onClickSubmit = (e: React.MouseEvent) => {
    // 버튼을 누르면 usestate content에 작성한 내용이 저장됨
    // const value = e.target
    // console.log(comment); // ok
    
    // 버튼을 누르면 axios 요청 with content and room_id
    // console.log(meeting_id, comment); ok
    createComment({
      content: comment,
      roomId: Number(meeting_id)
    }).then((res)=>{
      console.log(res);
      // 다 되면 현재 페에지 리다이렉트
      readCommentList();
    })    
  }

  const readCommentList = () => {
    readComment(Number(meeting_id))
    .then(({data})=> {
      console.log(data);
      // 저장해주고
      setCommentList(commentList.concat(data))
      // navigate(0)
    })
    console.log(commentList.length);
    
  }
  // 컴포넌트 create될 때 axios
  useEffect(()=>{
    // 만약 로그인이 안되어 있거나, 이 모임의 참여자가 아니면 리다이렉트 시키기
    console.log(userAuth);
    /* if (userAuth.isLogined === false){
      Swal.fire({
        title: '모임에 참여한 사용자만 접근 가능합니다.',
        icon: 'error',
        timer: 5000,
      }).then(()=>{
        navigate(0);
      })
    } else { */
      readCommentList();
    // }
    
    // 전체 커뮤니티 글 읽어오기
  }, [])

  // 삭제하기
  const onClickDelete = (commentId: number):any => {
    deleteComment(commentId)
    .then(({data})=> {
      console.log(data);
    })
  }

  // const onClickUpdate = (commentId: number): any => {

  // }

  return (
    <div>
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
        {commentList.length > 1 && (
          <div className="h-[75vh]">
            {commentList.map(comment=>
              <div className="my-5 border rounded p-2">
                <div className="justify-between">
                  <Link to={`/profile/${comment.username}`}>
                    <div className="flex flex-row justify-start border-b border-gray-300 pb-1">
                      <div className="w-[25px] h-[25px] mr-2">
                        <img
                          className="w-full h-full"
                          src={
                            process.env.PUBLIC_URL + `/img/userBadge` + getParticipantBadge(comment.userScore) + ".png"
                          }
                          alt="사용자 뱃지"
                        />
                      </div>
                      <span className="font-semibold leading-tightl">{comment.username}</span>
                    </div>
                  </Link>
                  {userAuth.userId === comment.username && (
                    <div>
                      {/* <button onClick={onClickUpdate(comment.commentId)}>수정하기</button> */}
                      <button onClick={onClickDelete(comment.commentId)}>삭제하기</button>
                    </div>
                  )}
                </div>

                <div className="rounded p-2">
                  <p>{comment.content}</p>
                </div>
                <div className="font-semibold text-xs text-right mt-2 mr-2">
                  <p>{comment.createdAt}</p>
                </div>
              </div>
            )}
          </div>
        )}
        </div>
      {commentList.length === 1 && (
        <p className="mt-5 text-lg text-center font-semibold">아직 작성된 글이 없습니다!</p>
      )}
    </div>
  );
};

export default CommunityTab;