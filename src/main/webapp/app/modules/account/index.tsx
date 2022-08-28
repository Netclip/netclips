import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Settings from './settings/settings';
import Password from './password/password';
import UploadGrid from 'app/shared/components/UploadGrid';

const AccountRoutes = () => (
  <div>
    <ErrorBoundaryRoutes>
      <Route path="settings" element={<Settings />} />
      <Route path="password" element={<Password />} />
      <Route path="upload" element={<UploadGrid />} />
    </ErrorBoundaryRoutes>
  </div>
);

export default AccountRoutes;
