import React, { useState, useRef } from 'react';
import axios from 'axios';

const Video = () => {
  const [src, setSrc] = useState('');
  const inputFileRef = useRef(null);
  const [file, setFile] = useState();
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

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

  function handleChange(event) {
    setFile(event.target.files[0]);
  }

  function handleSubmit(event) {
    event.preventDefault();
    const url = 'http://localhost:8080/api/videos/upload';
    const formData = new FormData();
    formData.append('file', file);
    formData.append('title', title);
    formData.append('description', description);
    const config = {
      headers: {
        'content-type': 'multipart/form-data',
      },
    };
    axios.post(url, formData, config).then(response => {
      console.log(response.data), alert('File Uploaded');
    });
  }

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input type="text" placeholder="Title" onChange={e => setTitle(e.target.value)} />
        <br />
        <input type="text" placeholder="Description" onChange={e => setDescription(e.target.value)} />
        <br />
        <br />
        <input type="file" ref={inputFileRef} onChange={handleChange} />
        <button type="submit"> Upload </button>
      </form>
    </div>
  );
};

export default Video;
