import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import { readRoom } from "../../api/room";
import { formatDate, roomMore } from "../../util/room";
import ReadRoomMap from "../etc/ReadRoomMap";

const RoomInfoTabs = () => {
  const navigator = useNavigate();
  const { meeting_id } = useParams<{ meeting_id: string }>();
  const [roomInfo, setRoomInfo] = useState<any>({
    title: "",
    parentCategory: "",
    childCategory: "",
    minUser: 0,
    maxUser: 0,
    location: {
      id: "",
      name: "",
      x: "",
      y: "",
      road_address: "",
    },
    description: "",
    endDate: "",
    reservationDate: "",
    price: 0,
    minAttituteScore: 0,
  });

  useEffect(() => {
    //axios
    if (meeting_id) {
      readRoom(meeting_id)
        .then(({ data }) => {
          setRoomInfo({ ...data });
        })
        .catch((e) => {
          console.error(e);

          Swal.fire({
            position: "center",
            icon: "error",
            title: "방 정보를 불러올 수 없습니다.",
            showConfirmButton: false,
            timer: 1500,
          }).then(() => {
            navigator(-1);
          });
        });
    }
  }, []);

  return (
    <div>
      <h3 className="text-sm font-semibold">{`[${roomInfo.parentCategory}] ${roomInfo.title}`}</h3>
      <hr className="mt-2 mb-2" />

      <div className="text-sm grid grid-cols-10">
        <p className="col-span-4 font-bold">모집 인원</p>
        {/* grid grid-cols-3 */}
        <div className="col-start-5 col-end-8 grid grid-cols-3">
          <span>{roomInfo.minUser} 명</span>
          <span className="text-center">~</span>
          <span>{roomInfo.maxUser} 명</span>
        </div>
      </div>
      <hr className="my-2" />

      <div className="text-sm grid grid-cols-10">
        <p className="col-span-4 font-bold">모집 마감일</p>
        <p className="col-start-5 col-end-10">
          {roomInfo.endDate && formatDate(roomInfo.endDate)}
        </p>
      </div>
      <hr className="my-2" />

      <div className="text-sm grid grid-cols-10">
        <p className="col-span-4 font-bold">모이는 날</p>
        <p className="col-start-5 col-end-10">
          {roomInfo.reservationDate && formatDate(roomInfo.reservationDate)}
        </p>
      </div>
      <hr className="my-2" />

      <div className="text-sm grid grid-cols-10">
        <p className="col-span-4 font-bold">금액</p>
        <p className="font-bold col-start-5 col-end-9">{roomInfo.price}원</p>
      </div>
      <hr className="my-2" />

      <div className="text-sm">
        <p className="font-bold">모임 설명</p>
        <p className="mt-2">{roomInfo.description}</p>
      </div>
      <hr className="my-3" />

      <div className="text-sm">
        <p className="font-bold">모임 위치</p>
        <div className="mt-2">
          <ReadRoomMap _location={roomInfo.location} />
        </div>
      </div>
    </div>
  );
};

export default RoomInfoTabs;
