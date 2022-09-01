import React, { Component } from 'react';
import { Row, Col, Container } from 'reactstrap';
import VideoPlayer from './VideoPlayer';
import Video from './Video';
import './video.scss';

function HomeGrid() {
  return (
    <Container className="grid">
      <Row className="row">
        {/*         <Col> */}
        {/*           <Video /> */}
        {/*         </Col> */}
        <Col>
          <VideoPlayer url={'https://www.youtube.com/embed/gQlMMD8auMs'} />
        </Col>
        <Col>
          <VideoPlayer url={'https://www.youtube.com/embed/F0B7HDiY-10'} />
        </Col>
        <Col>
          <VideoPlayer url={'https://zcw-cohort8n3dot1.s3.amazonaws.com/netclips/1661985974602-street.mp4'} />
        </Col>
        <Col>
          <VideoPlayer url={'https://zcw-cohort8n3dot1.s3.amazonaws.com/netclips/1661985974602-street.mp4'} />
        </Col>
      </Row>
    </Container>
  );
}

export default HomeGrid;
