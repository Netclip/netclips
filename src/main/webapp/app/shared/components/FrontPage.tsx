import React, { Component, useState, useEffect, Fragment } from 'react';
import axios from 'axios';
import './frontPage.scss';
import VideoCard from './test components/VideoCard';

const API_URL = '/api/';
const VIDEO_USER = 'api/videos/fetch-play-video/';
const id = 20;

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
      <section>
        <h2 className="h2">Videos</h2>
        <div className="recommendedVideos">
          {video.map((video, index) => {
            console.log(video);
            return (
              <div key={video.index}>
                <div className="recommendedVideos__videos">
                  <VideoCard
                    title={video.title}
                    views="11M Views"
                    timestamp="3 days ago"
                    channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
                    channel="BLACKPINK"
                    image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
                  />
                </div>
              </div>
            );
          })}
        </div>
      </section>
    </Fragment>
  );
}

export default FrontPage;
