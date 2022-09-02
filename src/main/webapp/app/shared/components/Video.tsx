import React, { useState, useRef } from 'react';

const Video = () => {
  const [src, setSrc] = useState('');
  const inputFileRef = useRef(null);

  const onFileChange = e => {
    const file = e.target.files[0];
    setSrc(URL.createObjectURL(file));
    /*Selected files data can be collected here.*/
    console.log(e.target.files);
  };
  const onBtnClick = e => {
    e.preventDefault();
    /*Collecting node-element and performing click*/
    inputFileRef.current.click();
  };

  return (
    <div>
      <video src={src} controls width="480" height="360"></video>
      <form className="some-container">
        <input hidden type="file" ref={inputFileRef} onChange={onFileChange} />
        <button onClick={onBtnClick}>Select file</button>
      </form>
    </div>
  );
};

export default Video;
