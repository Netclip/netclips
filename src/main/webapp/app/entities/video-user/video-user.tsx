import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IVideoUser } from 'app/shared/model/video-user.model';
import { getEntities } from './video-user.reducer';

export const VideoUser = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const videoUserList = useAppSelector(state => state.videoUser.entities);
  const loading = useAppSelector(state => state.videoUser.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="video-user-heading" data-cy="VideoUserHeading">
        Video Users
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/video-user/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Video User
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {videoUserList && videoUserList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Internal User</th>
                <th>Liked Videos</th>
                <th>Videos Disliked</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {videoUserList.map((videoUser, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/video-user/${videoUser.id}`} color="link" size="sm">
                      {videoUser.id}
                    </Button>
                  </td>
                  <td>{videoUser.internalUser ? videoUser.internalUser.id : ''}</td>
                  <td>
                    {videoUser.likedVideos
                      ? videoUser.likedVideos.map((val, j) => (
                          <span key={j}>
                            <Link to={`/video/${val.id}`}>{val.id}</Link>
                            {j === videoUser.likedVideos.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {videoUser.videosDislikeds
                      ? videoUser.videosDislikeds.map((val, j) => (
                          <span key={j}>
                            <Link to={`/video/${val.id}`}>{val.id}</Link>
                            {j === videoUser.videosDislikeds.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/video-user/${videoUser.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`/video-user/${videoUser.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`/video-user/${videoUser.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Video Users found</div>
        )}
      </div>
    </div>
  );
};

export default VideoUser;
