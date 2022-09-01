import React, { Component } from 'react';
import { Row, Col, Container } from 'reactstrap';
import HomeGrid from './HomeGrid';
import Uploader from './Uploader';

export const UploadGrid = () => {
  return (
    <div>
      <HomeGrid />
      <Uploader />
    </div>
  );
};

export default UploadGrid;
