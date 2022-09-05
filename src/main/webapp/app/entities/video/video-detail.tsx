import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './video.reducer';
import Like from 'app/shared/components/LikesDislikes/Likes';
import './videopage.scss';
import GetVideos from 'app/shared/components/GetVideos';
import VideoComments from 'app/shared/components/VideoComments/video-comments';
import axios from 'axios';

export const VideoDetail = () => {
  var [likeCount, setLikeCount] = useState(0);
  var [dislikeCount, setDislikeCount] = useState(0);

  var [likeButtonClicked, setLikeButtonClicked] = useState(false);
  var [dislikeButtonClicked, setdislikeButtonClicked] = useState(false);
  var urlStr = window.location.href;
  var n = urlStr.lastIndexOf('/');
  var result = urlStr.substring(n + 1);
  var [vidId, setVidId] = useState(result);
  // var [vidId, setVidId] = useState(window.location.href.slice(-1));
  // var [likeStatus, setLikeStatus] = useState(false);

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

  const fetchLikes = async () => {
    try {
      await axios.get('api/videos', axiosConfig).then(response => {
        var likesResults = response.data[Number.parseInt(vidId) - 1].likes;
        console.log('this video id is -> ' + vidId);
        console.log('video #' + vidId + ' likes= ' + likesResults);
        setLikeCount(likesResults);

        setLikeButtonClicked(true);
      });
      if (likeButtonClicked == true) {
        setLikeCount(--likeCount);
        setLikeButtonClicked(false);
      }
    } catch (err) {
      console.log(err);
    }
  };

  const fetchDislikes = async () => {
    try {
      await axios.get('api/videos', axiosConfig).then(response => {
        var dislikesResults = response.data[Number.parseInt(vidId) - 1].dislikes;
        console.log('this video id is -> ' + vidId);
        console.log('video #' + vidId + ' dislikes= ' + dislikesResults);
        setDislikeCount(dislikesResults);

        setdislikeButtonClicked(true);
      });
      if (dislikeButtonClicked == true) {
        setDislikeCount(--dislikeCount);
        setdislikeButtonClicked(false);
      }
    } catch (err) {
      console.log(err);
    }

    useEffect(() => {
      setLikeCount(likeCount);
      // setLikeButtonClicked(true);
    }, []);
  };

  // const fetchData = async () => {
  //   try {
  //     await axios.get('api/videos', axiosConfig).then(response => {
  //       setVidId(vidId);
  //       var tempLikes = response.data[Number.parseInt(vidId) - 1].likes;
  //       var tempDislikes = response.data[Number.parseInt(vidId) - 1].dislikes;

  //       console.log('likes => ' + tempLikes);
  //       console.log('dislikes => ' + tempDislikes);
  //       console.log('video id: '+vidId)

  //       // likeCount = tempLikes;
  //       // dislikeCount = tempDislikes;

  //       setLikeCount(tempLikes);
  //       setDislikeCount(tempDislikes);

  //       console.log('likeCount: ' + likeCount);
  //       console.log('dislikeCount: ' + dislikeCount);

  //       setLikeStatus(true);
  //     });

  //     if (likeStatus == true) {
  //       setLikeCount(--likeCount);
  //       // setDislikeCount(--dislikeCount);
  //       setLikeStatus(false);
  //     }
  //   } catch (err) {
  //     console.log(err);
  //   }

  //   useEffect(() => {
  //     setLikeCount(likeCount);
  //     setDislikeCount(dislikeCount);
  //   }, []);
  //   return likeCount && dislikeCount;
  // };

  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
    fetchDislikes();
    fetchLikes();
  }, []);

  const videoEntity = useAppSelector(state => state.video.entity);
  return (
    <div className="videoPgContainer">
      <div id="uploaderContainer">{/* <sub id="uploaderText">Video Uploader: {videoEntity.uploader}</sub> */}</div>

      <h1>{videoEntity.title}</h1>
      <div>{<GetVideos id={id} />}</div>

      <div className="videoDetailsContainer">
        <p>Description: {videoEntity.description}</p>
        <div id="likeButtonsContainer">
          <Like function={fetchLikes} className="button" text={'Likes'} icon={'heart'} count={likeCount} conditional={likeButtonClicked} />

          <Like
            function={fetchDislikes}
            className="button"
            text={'Dislikes'}
            icon={'trash'}
            count={dislikeCount}
            conditional={dislikeButtonClicked}
          />
        </div>
      </div>
      <VideoComments />
    </div>
  );
};

export default VideoDetail;
