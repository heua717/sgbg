import BtnMypageInfo from "../buttons/BtnMypageInfo";
const ProfileCard = (props: any): any => {
  const { user } = props;
  return (
    <div className="grid grid-cols-2">
      <BtnMypageInfo type={"Participant"} user={user} />
      <BtnMypageInfo type={"Host"} user={user} />
    </div>
  );
};

export default ProfileCard;
