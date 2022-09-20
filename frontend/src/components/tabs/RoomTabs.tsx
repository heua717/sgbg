import { useState } from "react";

const RoomTabs = () => {
    const data = [
        {
          id: 0,
          title: "모임 정보",
        },
        {
          id: 1,
          title: "참여자 목록",
        },
        {
          id: 2,
          title: "커뮤니티",
        }
    ];
    const [index, setIndex] = useState(0);

    return (<div className="mx-6">
        <section>
            <ul>
                {data.map(item => (
                    <li key={item.id}
                    onClick={()=> setIndex(item.id)}
                    > {item.title}</li>
                ))}
            </ul>
            {data.filter(item => index === item.id).map(item => (
                <div>{item.title}이다.</div>
            ))}
        </section>
        

    </div>);
};

export default RoomTabs;
