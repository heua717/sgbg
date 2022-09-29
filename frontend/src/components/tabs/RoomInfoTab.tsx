import { type } from "os";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { readRoom } from "../../api/room";
import { roomMore } from "../../util/room";

const RoomInfoTabs = () => {
  const { meeting_id } = useParams<{ meeting_id: string }>();
  const [roomInfo, setRoomInfo] = useState<any>({
    title: "",
    category: "",
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
        .then(() => {})
        .catch();
    }
  }, []);

  return (
    <div>
      <h3 className="text-sm font-semibold">{`[${roomInfo.category}] ${roomInfo.title}`}</h3>
      <hr className="mt-5 mb-2" />

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
        <p className="col-start-5 col-end-9">{roomInfo.endDate}</p>
      </div>
      <hr className="my-2" />

      <div className="text-sm grid grid-cols-10">
        <p className="col-span-4 font-bold">예약 날짜</p>
        <p className="col-start-5 col-end-9">{roomInfo.reservationDate}</p>
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
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem, provident ducimus ad alias
          atque odio ipsam harum possimus, quam ullam veritatis aliquid quo maiores aliquam non
          adipisci, voluptatum sed facilis. Lorem ipsum dolor sit amet consectetur adipisicing elit.
          Minus, dicta nesciunt et ipsam ea molestiae qui unde fugiat impedit totam. Laboriosam,
          odit amet impedit hic non illo repellendus minus debitis! Lorem ipsum dolor sit amet,
          consectetur adipisicing elit. Officiis, nostrum repudiandae. Accusamus, unde incidunt
          laborum ea sapiente quasi neque. Veniam explicabo id expedita ratione eaque, est autem
          consectetur! Nulla, fugit.
        </div>
      </div>
      <hr className="my-2" />
    </div>
  );
};

export default RoomInfoTabs;
