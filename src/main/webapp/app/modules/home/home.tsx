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

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);

  return (
    <>
      <div>
        <SearchBar />
        <h1>NetClips</h1>
        <Video />
        <HomeGrid />
      </div>
    </>
  );
};

export default Home;
