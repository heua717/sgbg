import { Map, MapMarker } from "react-kakao-maps-sdk";
import { location } from "../../util/room";

type ReadRoomMapProps = {
  _location: location;
};

const ReadRoomMap = ({ _location }: ReadRoomMapProps) => {
  const _lat = parseFloat(_location.x);
  const _lng = parseFloat(_location.y);
  return (
    <Map // 지도를 표시할 Container
      center={{
        // 지도의 중심좌표
        lat: _lat,
        lng: _lng,
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
          lat: _lat,
          lng: _lng,
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
            {_location.name}
          </a>
          <div>{_location.road_address}</div>
        </div>
      </MapMarker>
    </Map>
  );
};

export default ReadRoomMap;
