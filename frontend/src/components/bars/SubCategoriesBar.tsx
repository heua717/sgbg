import BtnSubCategory from "../buttons/BtnSubCategory";

const SubCategoriesBar = (props: any) => {
  // const list = [0, 0, 0, 0];
  return (
    <div className="w-full mb-1">
      {props.childCategories.map((childCategory: string) => (
        <BtnSubCategory childCategory={childCategory} />
      ))}
    </div>
  );
};

export default SubCategoriesBar;
