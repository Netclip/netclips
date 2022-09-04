import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IVideoUser } from 'app/shared/model/video-user.model';
import { getEntities as getVideoUsers } from 'app/entities/video-user/video-user.reducer';
import { IVideo } from 'app/shared/model/video.model';
import { getEntity, updateEntity, createEntity, reset } from './video.reducer';

export const VideoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const videoUsers = useAppSelector(state => state.videoUser.entities);
  const videoEntity = useAppSelector(state => state.video.entity);
  const loading = useAppSelector(state => state.video.loading);
  const updating = useAppSelector(state => state.video.updating);
  const updateSuccess = useAppSelector(state => state.video.updateSuccess);

  const handleClose = () => {
    navigate('/video');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getVideoUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...videoEntity,
      ...values,
      uploader: videoUsers.find(it => it.id.toString() === values.uploader.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...videoEntity,
          uploader: videoEntity?.uploader?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="netclipsApp.video.home.createOrEditLabel" data-cy="VideoCreateUpdateHeading">
            Create or edit a Video
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="video-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Content Ref" id="video-contentRef" name="contentRef" data-cy="contentRef" type="text" />
              <ValidatedField label="Title" id="video-title" name="title" data-cy="title" type="text" />
              <ValidatedField label="Description" id="video-description" name="description" data-cy="description" type="text" />
              <ValidatedField label="Likes" id="video-likes" name="likes" data-cy="likes" type="text" />
              <ValidatedField label="Dislikes" id="video-dislikes" name="dislikes" data-cy="dislikes" type="text" />
              <ValidatedField label="Upload Date" id="video-uploadDate" name="uploadDate" data-cy="uploadDate" type="date" />
              <ValidatedField label="View Count" id="video-viewCount" name="viewCount" data-cy="viewCount" type="text" />
              <ValidatedField label="Thumbnail Ref" id="video-thumbnailRef" name="thumbnailRef" data-cy="thumbnailRef" type="text" />
              <ValidatedField id="video-uploader" name="uploader" data-cy="uploader" label="Uploader" type="select">
                <option value="" key="0" />
                {videoUsers
                  ? videoUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/video" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default VideoUpdate;
