import React from 'react';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Icons } from 'react-toastify';
import 'app/shared/components/test components/Header.scss';
import SearchBar from 'app/modules/home/SearchBar/searchbar';

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    <img src="content/images/NetClips-12.png" alt="Logo" />
  </div>
);

// const [inputSearch, setInputSearch] = useState('');

export const Brand = () => (
  <>
    <NavbarBrand tag={Link} to="/" className="brand-logo">
      <BrandIcon />
    </NavbarBrand>

    {/* <SearchBar /> */}
  </>
);

export const Home = () => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>Home</span>
    </NavLink>
  </NavItem>
);
