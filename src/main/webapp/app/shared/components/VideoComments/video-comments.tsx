import React, { useEffect } from 'react';
import Like from '../LikesDislikes/Likes';
import './video-comments.scss';

function VideoComments() {
  return (
    <div className="videoCommentsContainer">
      <textarea className="comments" placeholder="write commmets here" />

      <div className="buttons-container">
        <div className="comment-button-container">
          <div className="submit-comments" onClick={() => {}}>
            submit
          </div>
        </div>
        <div className="comment-likes-dislikes">
          <Like text={'Likes'} icon={'heart'} />
          <Like text={'Dislikes'} icon={'trash'} />
        </div>
      </div>
    </div>
  );
}

export default VideoComments;
