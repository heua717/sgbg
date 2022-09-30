export type location = {
  locationId: string;
  name: string;
  latitude: string;
  hardness: string;
  roadAddress: string;
};

export interface room {
  title: string;
  parentCategory: string;
  childCategory: string;
  minUser: number;
  maxUser: number;
  location: location;
  description: string;
}

export interface roomMore extends room {
  roomId?: number;
  endDate: string;
  reservationDate: string;
  price: number;
  minMemberScore: number;
}

export const formatDate = (date: string): string => {
  let tmp = date.split("T");
  let newDate = tmp[0].split("-");
  let newTime = tmp[1].split(":");
  return `[${newDate[0]}.${newDate[1]}.${newDate[2]}]  ${newTime[0]}시 ${newTime[1]}분`;
};
