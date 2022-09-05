import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import './Likes.scss';

// const Count = ({ count }) => {
//   const [num, setNum] = useState(count);

//   useEffect(() => {
//     setNum(count);
//   }, [count]);
//   return <p>{num}</p>
// }

export const Like = props => {
  // function incrementLikes() {
  //   likeCount = props.likesCount;
  //     setLikeCount(++likeCount);
  //     setLikeStatus(true);

  //   if (likeStatus == true) {
  //       setLikeCount(--likeCount);
  //       setLikeStatus(false);
  //     }

  //   useEffect(() => {
  //       setLikeCount(likeCount);
  //     });
  //   }
  return (
    <div
      className="button"
      onClick={props.function}
      style={{
        backgroundColor: props.conditional ? '#a4dbc4' : '',
        color: props.conditional ? 'black' : '',
      }}
    >
      {props.text}: <FontAwesomeIcon icon={props.icon} />
      <div>{props.count}</div>
    </div>
  );
};
export default Like;
