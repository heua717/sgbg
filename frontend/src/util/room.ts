export type location = {
  id: string;
  name: string;
  x: string;
  y: string;
  road_address: string;
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
  endDate: string;
  reservationDate: string;
  price: number;
  minAttituteScore: number;
}

export const formatDate = (date: string): string => {
  let tmp = date.split("T");
  let newDate = tmp[0].split("-");
  let newTime = tmp[1].split(":");
  return `[${newDate[0]}.${newDate[1]}.${newDate[2]}]  ${newTime[0]}시 ${newTime[1]}분`;
};
