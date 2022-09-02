import React from 'react';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Icons } from 'react-toastify';
import 'app/shared/components/test components/Header.scss';
import MenuSharpIcon from '@mui/icons-material/MenuSharp';
import SearchIcon from '@mui/icons-material/Search';
import VideoCallSharpIcon from '@mui/icons-material/VideoCallSharp';
import AppsSharpIcon from '@mui/icons-material/AppsSharp';
import NotificationsSharpIcon from '@mui/icons-material/NotificationsSharp';
import { Avatar } from '@mui/material';

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    <img src="content/images/netclips.PNG" alt="Logo" />
  </div>
);

// const [inputSearch, setInputSearch] = useState('');

export const Brand = () => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    <div>
      <span className="brand-title">NetClips</span>
      <span className="navbar-version">{VERSION}</span>
    </div>
    {/* <div className='header__input'>
            <input
                onChange={e => setInputSearch(e.target.value)}
                value={inputSearch}
                placeholder="Search"
                type="text"
            />

            <Link to={`/search/${inputSearch}`}>
                <SearchIcon className='header__inputButton' />
            </Link>

        </div> */}
  </NavbarBrand>
);

export const Home = () => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>Home</span>
    </NavLink>
  </NavItem>
);
