import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Video from './video';
import VideoUser from './video-user';
import Comment from './comment';
import UserVideoPage from 'app/shared/components/UserVideoPage';
import Sidebar from 'app/shared/components/test components/Sidebar';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="video/*" element={<Video />} />
        <Route
          path="userVideo/*"
          element={
            <div className="app__page">
              <Sidebar /> <UserVideoPage />
            </div>
          }
        />
        <Route path="video-user/*" element={<VideoUser />} />
        <Route path="comment/*" element={<Comment />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
