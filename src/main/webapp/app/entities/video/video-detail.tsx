import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col, Card } from 'reactstrap';
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
import { times } from 'lodash';

export const VideoDetail = () => {
  var [likeCount, setLikeCount] = useState(0);
  var [dislikeCount, setDislikeCount] = useState(0);

  var [likeButtonClicked, setLikeButtonClicked] = useState(false);
  var [dislikeButtonClicked, setdislikeButtonClicked] = useState(false);
  var urlStr = window.location.href;
  var n = urlStr.lastIndexOf('/');
  var result = urlStr.substring(n + 1);
  var [vidId, setVidId] = useState(result);

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
      await axios.get(`/api/videos/fetch-play-video/${result}`, axiosConfig).then(response => {
        var likesResults = response.data.likes;
        console.log('likes COUNT -> ' + likesResults);
        setLikeCount(likesResults);
      });
    } catch (err) {
      console.log(err);
    }
  };

  const fetchDislikes = async () => {
    try {
      await axios.get(`/api/videos/fetch-play-video/${result}`, axiosConfig).then(response => {
        var dislikesResults = response.data.dislikes;
        console.log('dislikes COUNT -> ' + dislikesResults);
        setDislikeCount(dislikesResults);
      });
    } catch (err) {
      console.log(err);
    }
  };

  const handleLike = () => {
    fetchLikes();
    if (likeButtonClicked) {
      setLikeButtonClicked(false);
    } else if (!dislikeButtonClicked) {
      setLikeButtonClicked(true);
    } else {
      setdislikeButtonClicked(false);
      setLikeButtonClicked(true);
    }

    try {
      axios.put(`api/video/like/?id=${result}`);
    } catch (err) {
      console.log(err);
    }
  };

  const handleDislike = () => {
    fetchDislikes();
    if (dislikeButtonClicked) {
      setdislikeButtonClicked(false);
    } else if (!likeButtonClicked) {
      setdislikeButtonClicked(true);
    } else {
      setLikeButtonClicked(false);
      setdislikeButtonClicked(true);
      console.log('is the dislike button clicked? ' + dislikeButtonClicked);
    }

    try {
      axios.put(`api/video/dislike/?id=${result}`);
    } catch (err) {
      console.log(err);
    }
  };

  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
    fetchDislikes();
    fetchLikes();
    console.log('is the dislike button clicked? ' + dislikeButtonClicked);
    console.log('is the like button clicked? ' + likeButtonClicked);
  }, []);

  useEffect(() => {
    console.log('\nnoww is the DISLIKE button clicked? ' + dislikeButtonClicked);
  }, [dislikeButtonClicked]);
  useEffect(() => {
    console.log('\nnoww is the LIKE button clicked? ' + likeButtonClicked);
  }, [likeButtonClicked]);

  const videoEntity = useAppSelector(state => state.video.entity);
  return (
    <Row className="rowContainer">
      <div className="videoPgContainer">
        <div id="uploaderContainer">{/* <sub id="uploaderText">Video Uploader: {videoEntity.uploader}</sub> */}</div>
        <Row className="videoDetailsContainer">
          <Col sm="6">
            <Card>
              <h1>{videoEntity.title}</h1>
              <div>{<GetVideos id={id} />}</div>
            </Card>
          </Col>
          <Col sm="4">
            <Card>
              <p>Description: {videoEntity.description}</p>
              <div id="likeButtonsContainer">
                <Like
                  function={handleLike}
                  className="button"
                  text={'Likes'}
                  icon={'heart'}
                  count={likeCount}
                  conditional={likeButtonClicked}
                />

                <Like
                  function={handleDislike}
                  className="button"
                  text={'Dislikes'}
                  icon={'trash'}
                  count={dislikeCount}
                  conditional={dislikeButtonClicked}
                />
              </div>
            </Card>
            <Card>
              <VideoComments id={id} />
            </Card>
          </Col>
          <Col sm="4"></Col>
        </Row>

        <Row></Row>

        <Row className="videoDetailsContainer"></Row>
      </div>
    </Row>
  );
};

export default VideoDetail;
