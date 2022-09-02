import './home.scss';
import HomeGrid from 'app/shared/components/HomeGrid';
import React from 'react';
import { Link } from 'react-router-dom';

import { Row, Col, Alert } from 'reactstrap';

import { useAppSelector } from 'app/config/store';
import SearchBar from './SearchBar/searchbar';
import Like from 'app/shared/components/LikesDislikes/Likes';
import { APP_DATE_FORMAT } from 'app/config/constants';

import Video from 'app/shared/components/Video';
import Sidebar from 'app/shared/components/test components/Sidebar';
import RecommendedVideos from 'app/shared/components/test components/RecommendedVideos';
import Header from 'app/shared/components/test components/Header';
import GetVideos from 'app/shared/components/GetVideos';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);

  return (
    <>
      <div>
        <Header />
        <SearchBar />
        <div className="app__page">
          <RecommendedVideos />
        </div>

        <h1>NetClips</h1>
      </div>
    </>
  );
};

export default Home;
