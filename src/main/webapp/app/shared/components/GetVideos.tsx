import React, { Component, useState, useEffect, Fragment } from 'react';
import axios from 'axios';
import HomeGrid from './HomeGrid';

const API_URL = '/api/s3';
const VIDEO_USER = 'api/video-user/1/videos';

function GetVideos() {
  const [video, setVideo] = useState([]);
  const axios = require('axios');

  const getAllVideos = async () => {
    try {
      const response = await axios.get(`${VIDEO_USER}`);
      console.log(response.data);
      setVideo(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getAllVideos();
  }, []);

  // GetVideos();

  return (
    // <HomeGrid video={video} />
    <Fragment>
      {video.map((video, index) => {
        console.log(video);
        return (
          <div key={video.id}>
            <h2>{video.id}</h2>
            <h3>{video.contentRef}</h3>
          </div>
        );
      })}
    </Fragment>
  );
}

export default GetVideos;
