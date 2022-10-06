import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import { readRoom } from "../../api/room";
import { formatDate, roomMore } from "../../util/room";
import { Map, MapMarker } from "react-kakao-maps-sdk";

const RoomInfoTabs = (room: any) => {
  const navigator = useNavigate();
  const { meeting_id } = useParams<{ meeting_id: string }>();
  const [roomInfo, setRoomInfo] = useState<roomMore>({
    title: "",
    parentCategory: "",
    childCategory: "",
    minUser: 0,
    maxUser: 0,
    location: {
      locationId: "",
      name: "",
      latitude: "",
      hardness: "",
      roadAddress: "",
    },
    description: "",
    endDate: "",
    reservationDate: "",
    price: 0,
    minMemberScore: 0,
  });

  useEffect(() => {
    //axios
    if (meeting_id) {
      readRoom(meeting_id)
        .then(({ data }) => {
          setRoomInfo({ ...data.roomInfo });
          console.log(data.roomInfo);
        })
        .catch((e) => {
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

  const [position, setPosition] = useState({ lat: 0, lng: 0 });

  useEffect(() => {
    console.log("FLOAT lat : " + parseFloat(roomInfo.location.latitude));
    console.log("FLOAT hardness : " + parseFloat(roomInfo.location.hardness));

    setPosition({
      lat: parseFloat(roomInfo.location.latitude),
      lng: parseFloat(roomInfo.location.hardness),
    });
  }, [roomInfo]);

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

      <div className="text-sm mt-5">
        <p className="font-bold mb-5">모임 설명</p>
        {roomInfo.description.split("\n").map((line: string) => (
          <div className="mt-2 leading-6">
            <p>{line}</p>
          </div>
        ))}
      </div>
      <hr className="my-3" />

      <div className="text-sm">
        <p className="font-bold my-5">모임 위치</p>
        <div className="mt-2">
          <Map // 지도를 표시할 Container
            center={{
              // 지도의 중심좌표
              lat: position.lat,
              lng: position.lng,
            }}
            style={{
              // 지도의 크기
              width: "100%",
              height: "200px",
            }}
            level={3} // 지도의 확대 레벨
          ></Map>
        </div>
      </div>
    </div>
  );
};

export default RoomInfoTabs;
