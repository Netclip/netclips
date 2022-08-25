import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useState } from 'react';
import './Likes.scss';

export const Like = props => {
  const [likeCount, setLikeCount] = useState(0);
  const [likeStatus, setLikeStatus] = useState(false);

  const incrementLikes = () => {
    setLikeCount(likeCount + 1);
    setLikeStatus(true);

    if (likeStatus == true) {
      setLikeCount(likeCount - 1);
      setLikeStatus(false);
    }
  };

  return (
    // <button
    <div
      className="button"
      onClick={incrementLikes}
      style={{
        backgroundColor: likeStatus ? 'orange' : '',
        color: likeStatus ? 'black' : '',
      }}
    >
      {props.text}: <FontAwesomeIcon icon={props.icon} /> {likeCount}
    </div>
  );
};
export default Like;
