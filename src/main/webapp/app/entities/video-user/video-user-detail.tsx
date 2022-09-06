import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './video-user.reducer';

export const VideoUserDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const videoUserEntity = useAppSelector(state => state.videoUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="videoUserDetailsHeading">Video User</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{videoUserEntity.id}</dd>
          <dt>Internal User</dt>
          <dd>{videoUserEntity.internalUser ? videoUserEntity.internalUser.id : ''}</dd>
          <dt>Liked Videos</dt>
          <dd>
            {videoUserEntity.likedVideos
              ? videoUserEntity.likedVideos.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {videoUserEntity.likedVideos && i === videoUserEntity.likedVideos.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Videos Disliked</dt>
          <dd>
            {videoUserEntity.videosDisliked
              ? videoUserEntity.videosDisliked.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {videoUserEntity.videosDisliked && i === videoUserEntity.videosDisliked.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Liked Comments</dt>
          <dd>
            {videoUserEntity.likedComments
              ? videoUserEntity.likedComments.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {videoUserEntity.likedComments && i === videoUserEntity.likedComments.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>Disliked Comments</dt>
          <dd>
            {videoUserEntity.dislikedComments
              ? videoUserEntity.dislikedComments.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {videoUserEntity.dislikedComments && i === videoUserEntity.dislikedComments.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/video-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/video-user/${videoUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default VideoUserDetail;
