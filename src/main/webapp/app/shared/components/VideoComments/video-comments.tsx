import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Like from '../LikesDislikes/Likes';
import './video-comments.scss';
import Moment from 'react-moment';
import { TextField } from '@mui/material';

const API_URL = '/api/comments';

function VideoComments(props) {
  const id = props.id;

  const [comment, setComments] = useState([]);
  const [content, setContent] = useState('');
  const axios = require('axios');

  const getComments = async () => {
    try {
      const response = await axios.get(`${API_URL}/video/?video_id=${id}`);
      console.log(response.data.content);
      setComments(response.data.content);
    } catch (err) {
      console.log(err);
    }
  };

  function handleSubmit(event) {
    // event.preventDefault();
    axios
      .post(API_URL + '/post', JSON.stringify(content), {
        headers: { 'Content-Type': 'application/json; charset=UTF-8' },
        params: { video_id: props.id, content: content }, //Add userID as a param
      })
      .then(response => console.log('repsonse', response.status));
  }

  useEffect(() => {
    getComments();
  }, []);

  return (
    <div className="videoCommentsContainer">
      <div className="videoCommentsContainer">
        {/* <textarea className="comments" placeholder="write comments here"> */}
        <form onSubmit={handleSubmit}>
          <TextField className="comments" type="text" placeholder="Comment" onChange={e => setContent(e.target.value)} />
          <br />
          <button type="submit"> Post </button>
        </form>
        {/* </textarea> */}

        {/* <div className="buttons-container">
          <div className="comment-button-container">
            <div className="submit-comments" onClick={() => {}}>
              submit
            </div>
          </div>
          <div className="comment-likes-dislikes">
            <Like text={'Likes'} icon={'heart'} />
            <Like text={'Dislikes'} icon={'trash'} />
          </div>
        </div> */}
      </div>
      <div className="comment_Text">
        {comment.map((comment, index) => {
          // console.log(video);
          return (
            <div key={index}>
              <div className="recommendedVideos__videos">
                <div key={comment.id}>
                  <h4>{comment.content}</h4>
                  <div>
                    {comment.uploader} --
                    <Moment format="YYYY/MM/DD HH:mm">{comment.timeStamp}</Moment>
                  </div>
                  <p>
                    {comment.likes}
                    {comment.dislikes}
                  </p>
                  {/* <div className="button">
                    <div className="delete-btn">Delete</div>
                  </div> */}
                </div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default VideoComments;
