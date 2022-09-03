import React from 'react';
import ReactPlayer from 'react-player';
import './videoPlayer.scss';

const VideoPlayer = ({ url }) => {
  return (
    <div>
      <ReactPlayer controls={true} url={url} width="100%" height="470px" />
      {/* <iframe
        width="100%"
        height="480px"
        src={url}
        title="YouTube video player"
        frameBorder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowFullScreen
      /> */}
    </div>
  );
};

export default VideoPlayer;
