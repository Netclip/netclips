import React, { Component, useState, useEffect, Fragment } from 'react';
import { Row, Col, Container } from 'reactstrap';
import VideoPlayer from './VideoPlayer';
import Video from './Video';
import './video.scss';
import videoReducer from 'app/entities/video/video.reducer';

function HomeGrid(props) {
  const displayVideo = props => {
    const { video } = props;

    return (
      <>
        <Container className="grid">
          <Row className="row">
            <Col>
              <VideoPlayer url={video} />
            </Col>
            <Col>
              <VideoPlayer url={video} />
            </Col>
          </Row>
        </Container>
      </>
    );
  };
}

export default HomeGrid;
