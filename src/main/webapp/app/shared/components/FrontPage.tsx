import React, { Component, useState, useEffect, Fragment } from 'react';
import axios from 'axios';
import VideoCard from './test components/VideoCard';
import '../../modules/home/SearchBar/searchbar.scss';

import '../components/test components/recommendedVideos.scss';

const API_URL = '/api/';

function FrontPage() {
  const [video, setVideo] = useState([]);
  const [searchInput, setSearchInput] = useState('');
  const [filteredVidsResults, setFilteredVidsResults] = useState([]);

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

  const searchVideos = searchValue => {
    setSearchInput(searchValue);
    // console.log(searchValue);
    if (searchInput !== '') {
      const filteredVids = video.filter(video => {
        return Object.values(video).join('').toLowerCase().includes(searchInput.toLowerCase());
      });
      setFilteredVidsResults(filteredVids);
    } else {
      setFilteredVidsResults(video);
    }
    // console.log(filteredVids);
  };

  return (
    <Fragment>
      <div>
        {/* <SearchBar
        // onChange={(e) => searchVideos(e.target.value)}
        // function={(e: { target: { value: any; }; }) => searchVideos(e.target.value)}
      /> */}

        <div className="searchcontainer">
          <input
            onChange={e => searchVideos(e.target.value)}
            className="searchbar"
            type="text"
            placeholder="Enter text here..."
            style={{ width: '40rem' }}
          />
        </div>

        <h2 className="h2">Videos</h2>
        <div className="recommendedVideos">
          {searchInput.length > 1
            ? filteredVidsResults.map((video, index) => {
                // console.log(video);
                return (
                  <div key={index}>
                    <div className="recommendedVideos__videos">
                      <VideoCard
                        title={video.title}
                        views={`${video.viewCount} Views`}
                        timestamp={video.uploadDate}
                        channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
                        channel={video.description}
                        image={video.thumbnailRef}
                        link={'video/' + video.id}
                      />
                    </div>
                  </div>
                );
              })
            : video.map((video, index) => {
                return (
                  <div key={index}>
                    <div className="recommendedVideos__videos">
                      <VideoCard
                        title={video.title}
                        views={`${video.viewCount} Views`}
                        timestamp={video.uploadDate}
                        channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
                        channel={video.description}
                        image={video.thumbnailRef}
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
