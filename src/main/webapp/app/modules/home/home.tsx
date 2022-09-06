import './home.scss';
import React from 'react';
import { useAppSelector } from 'app/config/store';
import Sidebar from 'app/shared/components/test components/Sidebar';
import FrontPage from 'app/shared/components/FrontPage';
import { Breakpoint, BreakpointProvider } from 'react-socks';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);

  return (
    <>
      <BreakpointProvider>
        <div className="hipster">
          <div className="app__page">
            <Sidebar />
            <FrontPage />
          </div>
        </div>
      </BreakpointProvider>
    </>
  );
};

export default Home;
