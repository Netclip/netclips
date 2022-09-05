import React from 'react';
import './searchbar.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

export const SearchBar = () => {
  return (
    <div className="searchcontainer">
      <input
        // onChange={(e) => props.function(e.target.value)}
        className="searchbar"
        type="text"
        placeholder="Enter text here..."
        style={{ width: '40rem' }}
      />
    </div>
  );
};
export default SearchBar;
