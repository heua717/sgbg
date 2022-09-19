export const getParticipantNickname = (score: number) => {
  const participant = [
    "",
    "눈물나는 참여자",
    "섭섭한 참여자",
    "괜찮은 참여자",
    "성실한 참여자",
    "열정적인 참여자",
  ];
  if (score < 20) {
    return [participant[1]];
  } else if (score < 40) {
    return [participant[2]];
  } else if (score < 60) {
    return [participant[3]];
  } else if (score < 80) {
    return [participant[4]];
  } else {
    return [participant[5]];
  }
};

export const getParticipantBadge = (score: number) => {
  if (score < 20) {
    return "1";
  } else if (score < 40) {
    return "2";
  } else if (score < 60) {
    return "3";
  } else if (score < 80) {
    return "4";
  } else {
    return "5";
  }
};
