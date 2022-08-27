import React, { Component } from 'react';
import { Row, Col, Container } from 'reactstrap';
import VideoPlayer from './VideoPlayer';

function HomeGrid() {
  return (
    <Container>
      <Row>
        <Col>
          <VideoPlayer url={'https://www.youtube.com/embed/gQlMMD8auMs'} />
        </Col>
        <Col>
          <VideoPlayer url={'https://www.youtube.com/embed/F0B7HDiY-10'} />
          {/* <iframe width="560" height="315" src="https://www.youtube.com/embed/gQlMMD8auMs" title="YouTube video player" frameBorder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowFullScreen></iframe> */}
        </Col>
      </Row>
      <Row>
        <Col>
          <VideoPlayer url={'https://www.youtube.com/embed/v7bnOxV4jAc'} />
        </Col>
        <Col>
          <VideoPlayer url={'https://www.youtube.com/embed/G8GaQdW2wHc'} />
          {/* <iframe width="560" height="315" src="https://www.youtube.com/embed/gQlMMD8auMs" title="YouTube video player" frameBorder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowFullScreen></iframe> */}
        </Col>
      </Row>
    </Container>
  );
}

export default HomeGrid;
