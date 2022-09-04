import React from 'react';
import './recommendedVideos.scss';
import VideoCard from './VideoCard';

function RecommendedVideos() {
  return (
    <div className="recommendedVideos">
      <h2>Recommended</h2>
      {/* <div className="recommendedVideos__videos">
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
        <VideoCard
          title="BLACKPINK - ‘Pink Venom’ M/V"
          views="11M Views"
          timestamp="3 days ago"
          channelImage="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
          channel="BLACKPINK"
          image="https://i.ytimg.com/vi/gQlMMD8auMs/hqdefault.jpg?sqp=-oaymwEcCOADEI4CSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZlhO2x8cGFPGbZHh4gS2AUI90DQ"
        />
      </div> */}
    </div>
  );
}

export default RecommendedVideos;
