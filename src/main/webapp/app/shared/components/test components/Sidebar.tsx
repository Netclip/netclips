import {
  ExpandMoreSharp,
  HistorySharp,
  HomeSharp,
  OndemandVideoSharp,
  SubscriptionsSharp,
  ThumbUpAltSharp,
  VideoLibrarySharp,
  WatchLaterSharp,
  Whatshot,
} from '@mui/icons-material';
import React from 'react';
import './Sidebar.scss';
import SidebarRow from './SidebarRow';
import { Breakpoint, BreakpointProvider } from 'react-socks';
import { Link, useLocation, useNavigate } from 'react-router-dom';

function Sidebar() {
  return (
    <Breakpoint medium up>
      <div className="sidebar">
        <Link to={`/`}>
          <SidebarRow Icon={HomeSharp} Title="Home" />
        </Link>
        <hr />
        <Link to={`/video`}>
          <SidebarRow Icon={OndemandVideoSharp} Title="Your Videos" />
        </Link>
        <SidebarRow Icon={ThumbUpAltSharp} Title="Liked Videos" />
        <SidebarRow Icon={HistorySharp} Title="History" />
        <hr />
      </div>
    </Breakpoint>
  );
}

export default Sidebar;
