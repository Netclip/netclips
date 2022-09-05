import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Like from '../LikesDislikes/Likes';
import './video-comments.scss';

const API_URL = '/api/';

function VideoComments() {
  const [comment, setComments] = useState([]);
  const axios = require('axios');

  const getComments = async () => {
    try {
      const response = await axios.get(`${API_URL}videos`);
      console.log(response.data);
      setComments(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getComments();
  }, []);

  return (
    <div>
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
      <div>
        {comment.map((comment, index) => {
          // console.log(video);
          return (
            <div key={index}>
              <div className="recommendedVideos__videos">{comment.comments}</div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default VideoComments;
