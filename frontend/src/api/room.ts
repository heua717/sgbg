import { corsApi } from "./API";
import { roomMore} from "../store/room";


const createRoom = (_params: roomMore) => {
  const url = 'room/create';
  return corsApi.post(url, {params: _params});
}

export default createRoom