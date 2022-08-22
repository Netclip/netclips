import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './comment.reducer';

export const CommentDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const commentEntity = useAppSelector(state => state.comment.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="commentDetailsHeading">Comment</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{commentEntity.id}</dd>
          <dt>
            <span id="timeStamp">Time Stamp</span>
          </dt>
          <dd>{commentEntity.timeStamp ? <TextFormat value={commentEntity.timeStamp} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="content">Content</span>
          </dt>
          <dd>{commentEntity.content}</dd>
          <dt>
            <span id="likes">Likes</span>
          </dt>
          <dd>{commentEntity.likes}</dd>
          <dt>
            <span id="dislikes">Dislikes</span>
          </dt>
          <dd>{commentEntity.dislikes}</dd>
          <dt>Video User</dt>
          <dd>{commentEntity.videoUser ? commentEntity.videoUser.id : ''}</dd>
          <dt>Video</dt>
          <dd>{commentEntity.video ? commentEntity.video.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/comment" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/comment/${commentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CommentDetail;
