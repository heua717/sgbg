import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import { readRoom } from "../../api/room";
import { formatDate, roomMore } from "../../util/room";
import { Map, MapMarker } from "react-kakao-maps-sdk";

const RoomInfoTabs = () => {
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
  const [lat, setLat] = useState<number>(-1);
  const [lng, setLng] = useState<number>(-1);
  const [, updateState] = useState<number[]>();

  useEffect(() => {
    //axios
    if (meeting_id) {
      readRoom(meeting_id)
        .then(({ data }) => {
          setRoomInfo({ ...data });
        })
        .catch((e) => {
          Swal.fire({
            position: "center",
            icon: "error",
            title: "방 정보를 불러올 수 없습니다.",
            showConfirmButton: false,
            timer: 1500,
          }).then(() => {
            //navigator(-1);
          });
        });
    }
  }, []);

  useEffect(() => {
    setLat(parseFloat(roomInfo.location.latitude));
    setLng(parseFloat(roomInfo.location.hardness));
    updateState([1]);
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

      <div className="text-sm">
        <p className="font-bold">모임 설명</p>
        <p className="mt-2">{roomInfo.description}</p>
      </div>
      <hr className="my-3" />

      <div className="text-sm">
        <p className="font-bold">모임 위치</p>
        <div className="mt-2">
          <div>{typeof lat}</div>
          {lat !== -1 ? (
            <Map // 지도를 표시할 Container
              center={{
                // 지도의 중심좌표
                lat: lat,
                lng: lng,
              }}
              style={{
                // 지도의 크기
                width: "100%",
                height: "200px",
              }}
              level={3} // 지도의 확대 레벨
            >
              <MapMarker // 인포윈도우를 생성하고 지도에 표시합니다
                position={{
                  // 인포윈도우가 표시될 위치입니다
                  lat: lat,
                  lng: lng,
                }}
              >
                {/* MapMarker의 자식을 넣어줌으로 해당 자식이 InfoWindow로 만들어지게 합니다 */}
                {/* 인포윈도우에 표출될 내용으로 HTML 문자열이나 React Component가 가능합니다 */}
                <div className="p-5">
                  <a
                    href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667"
                    style={{ color: "blue" }}
                    target="_blank"
                    rel="noreferrer"
                  >
                    {roomInfo.location.name}
                  </a>
                  <div>{roomInfo.location.roadAddress}</div>
                </div>
              </MapMarker>
            </Map>
          ) : (
            ""
          )}
        </div>
      </div>
    </div>
  );
};

export default RoomInfoTabs;
