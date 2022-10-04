import { api } from "./API";

type Eval = {
  kakaoId: string,
  eview: string,
}

export const postEvalHost = (roomId: number, value: boolean) => {
  const url = `/eval/host/${roomId}`;
  return api.post(url, { isSuccess: value });
};

export const postEvalMember = (roomId: number, values: Eval[]) => {
  const url = `/eval/host/${roomId}`;
  return api.post(url, { evaluations: values });
};
