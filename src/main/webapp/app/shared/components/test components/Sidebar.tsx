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

function Sidebar() {
  return (
    <Breakpoint medium up>
      <div className="sidebar">
        <SidebarRow Icon={HomeSharp} Title="Home" />
        <SidebarRow Icon={Whatshot} Title="Trending" />
        <SidebarRow Icon={SubscriptionsSharp} Title="Subscription" />
        <hr />
        <SidebarRow Icon={VideoLibrarySharp} Title="Library" />
        <SidebarRow Icon={HistorySharp} Title="History" />
        <SidebarRow Icon={OndemandVideoSharp} Title="Your Videos" />
        <SidebarRow Icon={WatchLaterSharp} Title="Watch Later" />
        <SidebarRow Icon={ThumbUpAltSharp} Title="Liked Videos" />
        <SidebarRow Icon={ExpandMoreSharp} Title="Show more" />
        <hr />
      </div>
    </Breakpoint>
  );
}

export default Sidebar;
