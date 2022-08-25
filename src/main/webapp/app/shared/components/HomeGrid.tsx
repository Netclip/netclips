import React, { Component } from 'react';
import { Row, Col, Container } from 'reactstrap';

function HomeGrid() {
  return (
    <Container>
      <Row>
        <Col>
          <iframe src="https://www.youtube.com/embed/gQlMMD8auMs" />
        </Col>
        <Col>
          <iframe src="https://www.youtube.com/embed/gQlMMD8auMs" />
        </Col>
        <Col>
          <iframe src="https://www.youtube.com/embed/gQlMMD8auMs" />
        </Col>
      </Row>
      <Row>
        <Col>
          <iframe src="https://www.youtube.com/embed/gQlMMD8auMs" />
        </Col>
        <Col>
          <iframe src="https://www.youtube.com/embed/gQlMMD8auMs" />
        </Col>
        <Col>
          <iframe src="https://www.youtube.com/embed/gQlMMD8auMs" />
        </Col>
      </Row>
      <Row>
        <Col>
          <iframe src="https://www.youtube.com/embed/gQlMMD8auMs" />
        </Col>
        <Col>
          <iframe src="https://www.youtube.com/embed/gQlMMD8auMs" />
        </Col>
        <Col>
          <iframe src="https://www.youtube.com/embed/gQlMMD8auMs" />
        </Col>
      </Row>
    </Container>
  );
}

export default HomeGrid;
