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
      await axios.get('api/videos', axiosConfig).then(response => {
        setVidId(vidId);
        var tempLikes = response.data[Number.parseInt(vidId) - 1].likes;
        var tempDislikes = response.data[Number.parseInt(vidId) - 1].dislikes;
        likeCount = tempLikes;
        dislikeCount = tempDislikes;

        console.log('likes => ' + tempLikes);
        console.log('dislikes => ' + tempDislikes);

        setLikeCount(tempLikes);
        setDislikeCount(tempDislikes);

        console.log('likeCount: ' + likeCount);
        console.log('dislikeCount: ' + dislikeCount);

        setLikeStatus(true);
      });

      if (likeStatus == true) {
        setLikeCount(--likeCount);
        // setDislikeCount(--dislikeCount);
        setLikeStatus(false);
      }
    } catch (err) {
      console.log(err);
    }

    useEffect(() => {
      setLikeCount(likeCount);
      setDislikeCount(dislikeCount);
    }, []);
    return likeCount && dislikeCount;
  };

  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const videoEntity = useAppSelector(state => state.video.entity);
  return (
    <div className="videoPgContainer">
      <div id="uploaderContainer">
        <sub id="uploaderText">Video Uploader: {videoEntity.uploader}</sub>
      </div>

      <h1>{videoEntity.title}</h1>
      <div>
        <GetVideos />
      </div>

      <div className="videoDetailsContainer">
        <p>Description: {videoEntity.description}</p>
        <div id="likeButtonsContainer">
          <Like
            function={fetchData}
            className="button"
            text={'Likes'}
            icon={'heart'}
            count={likeCount}
            conditional={likeStatus}
            //  style={{
            //     backgroundColor: likeStatus ? 'red' : '',
            //     color: likeStatus ? 'black' : '',
            //   }}
          />
          <Like className="button" text={'Dislikes'} icon={'trash'} count={dislikeCount} />
        </div>
      </div>
      <VideoComments />
    </div>
  );
};

export default VideoDetail;
