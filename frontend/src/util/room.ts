export type location = {
  id: string;
  name: string;
  x: string;
  y: string;
  road_address: string;
}

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