import React, { Component, useState, useEffect, Fragment } from 'react';
import axios from 'axios';
import HomeGrid from './HomeGrid';
import VideoPlayer from './VideoPlayer';

const API_URL = '/api/s3';
const VIDEO_USER = 'api/videos/fetch-play-video/';
const id = 16;

function GetVideos() {
  const [video, setVideo] = useState([]);
  const axios = require('axios');

  const getAllVideos = async () => {
    try {
      const response = await axios.get(`${VIDEO_USER}${id}`);
      console.log(response.data.preSignedUrl);
      setVideo(response.data.preSignedUrl);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getAllVideos();
  }, []);

  // GetVideos();

  return (
    <>
      <VideoPlayer url={video} />
    </>
    // {/*     <Fragment>  */}
    // {/*       {video.map((video) => {  */}
    // {/*         console.log(video);  */}
    // {/*         return (  */}
    // {/*           <div key={video.id}>  */}
    // {/*             <h2>{video.id}</h2>  */}
    // {/*             <h3>{video.contentRef}</h3>  */}
    // {/*           </div>  */}
    // {/*          );  */}
    // {/*       })}  */}
    // {/*      </Fragment>  */}
  );
}

export default GetVideos;
