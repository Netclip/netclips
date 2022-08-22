import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import VideoUser from './video-user';
import VideoUserDetail from './video-user-detail';
import VideoUserUpdate from './video-user-update';
import VideoUserDeleteDialog from './video-user-delete-dialog';

const VideoUserRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<VideoUser />} />
    <Route path="new" element={<VideoUserUpdate />} />
    <Route path=":id">
      <Route index element={<VideoUserDetail />} />
      <Route path="edit" element={<VideoUserUpdate />} />
      <Route path="delete" element={<VideoUserDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default VideoUserRoutes;
