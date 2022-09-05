import React, { Component } from 'react';
import { Row, Col, Container } from 'reactstrap';
import Uploader from './Uploader';
import FileUpload from './FileUpload';

export const UploadGrid = () => {
  return (
    <div className="container" style={{ width: '600px' }}>
      <div className="my-3">
        <h3>NetClips</h3>
        <h4>File Upload</h4>
      </div>
      <Uploader />
    </div>
  );
};

export default UploadGrid;
