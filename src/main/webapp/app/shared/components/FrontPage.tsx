import React, { Component, useState, useEffect, Fragment } from 'react';
import axios from 'axios';
import VideoCard from './test components/VideoCard';

import '../components/test components/recommendedVideos.scss';

const API_URL = '/api/';

function FrontPage() {
  const [video, setVideo] = useState([]);
  const axios = require('axios');

  const getAllVideos = async () => {
    try {
      const response = await axios.get(`${API_URL}videos`);
      console.log(response.data);
      setVideo(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getAllVideos();
  }, []);

  return (
    <Fragment>
      <div>
        <h2 className="h2">Videos</h2>
        <div className="recommendedVideos">
          {video.map((video, index) => {
            // console.log(video);
            return (
              <div key={index}>
                <div className="recommendedVideos__videos">
                  <VideoCard
                    title={video.title}
                    views={`${video.likes} likes`}
                    timestamp={video.uploadDate}
                    channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
                    channel="BLACKPINK"
                    image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
                    link={'video/' + video.id}
                  />
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </Fragment>
  );
}

export default FrontPage;
