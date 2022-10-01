import BtnSubCategory from "../buttons/BtnSubCategory";

const SubCategoriesBar = () => {
  const list = [0, 0, 0, 0];
  return (
    <div className="w-full mb-1">
      {list.map(() => (
        <BtnSubCategory />
      ))}
    </div>
  );
};

export default SubCategoriesBar;
