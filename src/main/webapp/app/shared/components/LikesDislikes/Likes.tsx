import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useState } from 'react';
import './Likes.scss';

export const Like = props => {
  var [likeCount, setLikeCount] = useState(props.likesCount);
  var [likeStatus, setLikeStatus] = useState(false);

  function incrementLikes() {
    likeCount = props.likesCount;
    setLikeCount(++likeCount);
    setLikeStatus(true);

    if (likeStatus == true) {
      setLikeCount(--likeCount);
      setLikeStatus(false);
    }

    useEffect(() => {
      setLikeCount(likeCount);
    });
  }

  return (
    <div
      className="button"
      onClick={incrementLikes}
      style={{
        backgroundColor: likeStatus ? 'orange' : '',
        color: likeStatus ? 'black' : '',
      }}
    >
      {props.text}: <FontAwesomeIcon icon={props.icon} />
      <div>{likeCount}</div>
    </div>
  );
};
export default Like;
