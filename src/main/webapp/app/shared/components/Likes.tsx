import React, { useState } from 'react';

export const Like = () => {
  const [likeCount, setLikeCount] = useState(0);
  const incrementLikes = () => {
    setLikeCount(likeCount + 1);
  };

  return <button onClick={incrementLikes}>Likes: {likeCount}</button>;
};
export default Like;
