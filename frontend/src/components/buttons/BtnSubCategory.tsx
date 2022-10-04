const BtnSubCategory = (props: any) => {
  return (
    <button className="border border-gray-300 rounded-full font-light text-sm py-1 px-2 ml-2">
      {props.childCategory}
    </button>
  );
};

export default BtnSubCategory;
