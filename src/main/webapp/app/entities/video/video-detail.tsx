import React, { useEffect, useLayoutEffect, useState } from 'react';
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
        // setDislikeCount(response.data.dislikes);
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
        // setLikeCount(response.data.likes);
      });
    } catch (err) {
      console.log(err);
    }
  };

  const handleLike = async () => {
    if (likeButtonClicked) {
      setLikeButtonClicked(false);

      try {
        const res = await axios.put(`api/video/like/?id=${result}`);
        setLikeCount(res.data.like);
        setDislikeCount(res.data.dislike);
      } catch (err) {
        console.log(err);
      }
      fetchLikes();
      fetchDislikes();
    } else if (!dislikeButtonClicked) {
      setLikeButtonClicked(true);

      try {
        const res = await axios.put(`api/video/like/?id=${result}`);
        setLikeCount(res.data.like);
        setDislikeCount(res.data.dislike);
      } catch (err) {
        console.log(err);
      }
      fetchLikes();
      fetchDislikes();
    } else {
      setdislikeButtonClicked(false);
      setLikeButtonClicked(true);

      try {
        const res = await axios.put(`api/video/like/?id=${result}`);
        setLikeCount(res.data.like);
        setDislikeCount(res.data.dislike);
      } catch (err) {
        console.log(err);
      }
      fetchLikes();
      fetchDislikes();
    }
  };

  const handleDislike = async () => {
    if (dislikeButtonClicked) {
      setdislikeButtonClicked(false);

      try {
        const res = await axios.put(`api/video/dislike/?id=${result}`);
        setDislikeCount(res.data.dislike);
        setLikeCount(res.data.like);
      } catch (err) {
        console.log(err);
      }
      fetchDislikes();
      fetchLikes();
    } else if (!likeButtonClicked) {
      setdislikeButtonClicked(true);

      try {
        const res = await axios.put(`api/video/dislike/?id=${result}`);
        setDislikeCount(res.data.dislike);
        setLikeCount(res.data.like);
      } catch (err) {
        console.log(err);
      }
      fetchDislikes();
      fetchLikes();
    } else {
      setLikeButtonClicked(false);
      setdislikeButtonClicked(true);
      try {
        const res = await axios.put(`api/video/dislike/?id=${result}`);
        setDislikeCount(res.data.dislike);
        setLikeCount(res.data.like);
      } catch (err) {
        console.log(err);
      }
      fetchLikes();
      fetchDislikes();
    }
  };

  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
    fetchDislikes();
    fetchLikes();
    // console.log('is the dislike button clicked? ' + dislikeButtonClicked);
    // console.log('is the like button clicked? ' + likeButtonClicked);
  }, []);

  // useEffect(() => {
  //   console.log('\nnoww is the DISLIKE button clicked? ' + dislikeButtonClicked);
  // }, [dislikeButtonClicked]);
  // useEffect(() => {
  //   console.log('\nnoww is the LIKE button clicked? ' + likeButtonClicked);
  // }, [likeButtonClicked]);

  const videoEntity = useAppSelector(state => state.video.entity);
  return (
    <Row className="rowContainer">
      <div className="videoPgContainer">
        <div id="uploaderContainer">{/* <sub id="uploaderText">Video Uploader: {videoEntity.uploader}</sub> */}</div>
        <Row className="videoDetailsContainer">
          <Col className="video_Col" sm="6">
            <h1>{videoEntity.title}</h1>
            <div>{<GetVideos id={id} />}</div>
          </Col>
          <Col sm="4">
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
            <VideoComments id={id} />
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
