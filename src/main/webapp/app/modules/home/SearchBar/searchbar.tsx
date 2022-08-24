import React from 'react';
import './searchbar.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

export const SearchBar = () => {
  return (
    <div className="searchcontainer">
      <input className="searchbar" type="text" placeholder="Enter text here..." style={{ width: '40rem' }} />
    </div>
  );
};
export default SearchBar;
