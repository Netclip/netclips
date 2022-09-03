import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import './Likes.scss';

export const Like = props => {
  var [likeCount, setLikeCount] = useState([]);
  var [dislikeCount, setDislikeCount] = useState([]);
  var [vidId, setVidId] = useState(window.location.href.slice(-1));
  var [likeStatus, setLikeStatus] = useState(false);

  const getBearerToken = () => {
    var authToken = localStorage.getItem('jhi-authenticationToken') || sessionStorage.getItem('jhi-authenticationToken');
    if (authToken) {
      authToken = JSON.parse(authToken);
      return `Bearer ${authToken}`;
    }
    return null;
  };

  const axiosConfig = {
    timeout: 5000,
    headers: {
      Authorization: getBearerToken(),
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
    },
  };
  const fetchData = async () => {
    try {
      await axios
        .get('api/videos', axiosConfig)
        // .then(response => setLikeCount(response.data[0]))
        // console.log(likeCount)
        .then(response => {
          // setLikeCount(response.data)
          console.log(likeCount);
          setVidId(vidId);
          // console.log("-> "+window.location.href.slice(-1))
          console.log(response.data[Number.parseInt(vidId) - 1].likes);
          setLikeCount(response.data[Number.parseInt(vidId) - 1].likes);
          setDislikeCount(response.data[Number.parseInt(vidId) - 1].dislikes);
          // setLikeCount(response)
          // setLikeCount(++likeCount);
          setLikeStatus(true);
        });

      if (likeStatus == true) {
        // setLikeCount(--likeCount);
        setLikeStatus(false);
      }
    } catch (err) {
      console.log(err);
    }

    useEffect(() => {
      setLikeCount(likeCount);
      setDislikeCount(dislikeCount);
    }, [props]);
  };

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
      onClick={fetchData}
      style={{
        backgroundColor: likeStatus ? 'red' : '',
        color: likeStatus ? 'black' : '',
      }}
    >
      {props.text}: <FontAwesomeIcon icon={props.icon} />
      {/* <div>{likeCount['likes']}</div> */}
      <div>
        {/* {likeCount.map((el, i) => {
        return (
          <p>
            {el['likes']}
          </p>
        )
      })} */}
        {props.likesCount}
      </div>
    </div>
  );
};
export default Like;
