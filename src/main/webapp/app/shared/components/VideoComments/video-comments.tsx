import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Like from '../LikesDislikes/Likes';
import './video-comments.scss';

const API_URL = '/api/comments';

function VideoComments() {
  const [comment, setComments] = useState([]);
  const [content, setContent] = useState('');
  const [video_id, setVideo_id] = useState<Number>();
  const axios = require('axios');

  const getComments = async () => {
    try {
      const response = await axios.get(`${API_URL}`);
      console.log(response.data);
      setComments(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  // function handleSubmit(event) {
  //   event.preventDefault();
  //   const url = `${API_URL}/post?video_id=1`;
  //   const formData = new FormData();
  //   formData.append('content', content);
  //   const config = {
  //     headers: {
  //       'content-type': 'multipart/form-data',
  //     },
  //   };
  //   axios.post(url, formData, config).then(response => {
  //     console.log(response.data), alert('Posted Comment');
  //   });
  // }

  function handleSubmit(event) {
    event.preventDefault();
    axios
      .post(API_URL + '/post', JSON.stringify(content), {
        headers: { 'Content-Type': 'application/json; charset=UTF-8' },
        params: { video_id: 4, content: content }, //Add userID as a param
      })
      .then(response => console.log('repsonse', response.status));
  }

  useEffect(() => {
    getComments();
  }, []);

  return (
    <div>
      <div className="videoCommentsContainer">
        {/* <textarea className="comments" placeholder="write comments here"> */}
        <form onSubmit={handleSubmit}>
          <input type="text" placeholder="Comment" onChange={e => setContent(e.target.value)} />
          <br />
          <button type="submit"> Post </button>
        </form>
        {/* </textarea> */}

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
              <div className="recommendedVideos__videos">
                <div className="post-card" key={comment.id}>
                  <h2 className="post-title">{comment.timeStamp}</h2>
                  <p className="post-body">{comment.content}</p>
                  {/* <div className="button">
                    <div className="delete-btn">Delete</div>
                  </div> */}
                </div>
                {/* <p>
                  {comment.id}
                  {comment.content}
                  {comment.timeStamp}
                  {comment.likes}
                  {comment.dislikes}
                </p> */}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default VideoComments;
