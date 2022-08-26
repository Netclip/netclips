import React from 'react';
import ReactPlayer from 'react-player';
import './videoPlayer.scss';

const VideoPlayer = ({ url }) => {
  return (
    <div>
      {/* <ReactPlayer
                className="react-player"
                controls={true}
                url={url}
                width="100%"
                height="100%"
            /> */}
      <iframe
        width="560"
        height="315"
        src={url}
        title="YouTube video player"
        frameBorder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowFullScreen
      />
    </div>
  );
};

export default VideoPlayer;
