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
      <Container className="grid">
        <Row className="row">
          <Col>
            <VideoPlayer url={'https://www.youtube.com/embed/gQlMMD8auMs'} />
          </Col>
          <Col>
            <VideoPlayer url={'https://www.youtube.com/embed/gQlMMD8auMs'} />
          </Col>
          <Col>
            <VideoPlayer url={'https://www.youtube.com/embed/F0B7HDiY-10'} />
          </Col>
          <Col>
            <VideoPlayer url={'https://www.youtube.com/embed/v7bnOxV4jAc'} />
          </Col>
          <Col>
            <VideoPlayer url={'https://www.youtube.com/embed/G8GaQdW2wHc'} />
          </Col>
        </Row>
      </Container>
    );
  };
}

export default HomeGrid;
