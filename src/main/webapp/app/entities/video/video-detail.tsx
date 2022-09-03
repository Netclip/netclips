import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './video.reducer';
import likeCount from 'app/shared/components/LikesDislikes/Likes';
import Like from 'app/shared/components/LikesDislikes/Likes';
import './videopage.scss';
import GetVideos from 'app/shared/components/GetVideos';
import VideoComments from 'app/shared/components/VideoComments/video-comments';

export const VideoDetail = () => {
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
          <Like className="button" text={'Likes'} icon={'heart'} likesCount={likeCount} />
          <Like className="button" text={'Dislikes'} icon={'trash'} likesCount={null} />
        </div>
      </div>
      <VideoComments />
    </div>
  );
};

export default VideoDetail;
