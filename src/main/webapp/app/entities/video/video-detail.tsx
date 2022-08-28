import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './video.reducer';
import Like from 'app/shared/components/LikesDislikes/Likes';
import './videopage.scss';
import Video from 'app/shared/components/Video';

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
      <Video />
      {/* <div id="videoPlaceholder"></div> */}

      <div className="videoDetailsContainer">
        <p>Description: {videoEntity.description}</p>
        <div id="likeButtonsContainer">
          <Like className="button" text={'Likes'} icon={'heart'} likesCount={videoEntity.likes} />
          <Like className="button" text={'Dislikes'} icon={'trash'} likesCount={videoEntity.dislikes} />
        </div>
      </div>
    </div>
    // <Row>
    //   <Col md="8">
    //     <h2 data-cy="videoDetailsHeading">Video</h2>
    //     <dl className="jh-entity-details">
    //       <dt>
    //         <span id="id">ID</span>
    //       </dt>
    //       <dd>{videoEntity.id}</dd>
    //       <dt>
    //         <span id="contentRef">Content Ref</span>
    //       </dt>
    //       <dd>{videoEntity.contentRef}</dd>
    //       <dt>
    //         <span id="title">Title</span>
    //       </dt>
    //       <dd>{videoEntity.title}</dd>
    //       <dt>
    //         <span id="description">Description</span>
    //       </dt>
    //       <dd>{videoEntity.description}</dd>
    //       <dt>
    //         <span id="likes">Likes</span>
    //       </dt>
    //       <dd>{videoEntity.likes}</dd>
    //       <dt>
    //         <span id="dislikes">Dislikes</span>
    //       </dt>
    //       <dd>{videoEntity.dislikes}</dd>
    //       <dt>
    //         <span id="uploadDate">Upload Date</span>
    //       </dt>
    //       <dd>
    //         {videoEntity.uploadDate ? <TextFormat value={videoEntity.uploadDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
    //       </dd>
    //       <dt>Uploader</dt>
    //       <dd>{videoEntity.uploader ? videoEntity.uploader.id : ''}</dd>
    //     </dl>
    //     <Button tag={Link} to="/video" replace color="info" data-cy="entityDetailsBackButton">
    //       <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
    //     </Button>
    //     &nbsp;
    //     <Button tag={Link} to={`/video/${videoEntity.id}/edit`} replace color="primary">
    //       <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
    //     </Button>
    //   </Col>
    // </Row>
  );
};

export default VideoDetail;
