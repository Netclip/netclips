import React, { useState, useEffect, Fragment } from 'react';
import UploadService from './FileUploadService';

const UploadFiles = () => {
  const [selectedFiles, setSelectedFiles] = useState(undefined);
  const [currentFile, setCurrentFile] = useState(undefined);
  const [progress, setProgress] = useState(0);
  const [message, setMessage] = useState('');
  const [fileInfos, setFileInfos] = useState([]);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  const selectFile = event => {
    setSelectedFiles(event.target.files);
  };

  const upload = () => {
    let currentFile = selectedFiles[0];
    setProgress(0);
    setCurrentFile(currentFile);
    UploadService.upload(
      currentFile,
      event => {
        setProgress(Math.round((100 * event.loaded) / event.total));
      },
      title,
      description
    )
      .then(response => {
        setMessage(response.data);
        console.log(response.data);
        alert('File Uploaded');
        return UploadService.getFiles();
      })
      .then(files => {
        setFileInfos(files.data);
      })
      .catch(() => {
        setProgress(0);
        setMessage('Could not upload the file!');
        setCurrentFile(undefined);
      });
    setSelectedFiles(undefined);
  };

  useEffect(() => {
    UploadService.getFiles().then(response => {
      setFileInfos(response.data);
    });
  }, []);

  return (
    <div>
      {currentFile && (
        <div className="progress">
          <div
            className="progress-bar progress-bar-info progress-bar-striped"
            role="progressbar"
            aria-valuenow={progress}
            aria-valuemin={0}
            aria-valuemax={100}
            style={{ width: progress + '%' }}
          >
            {progress}%
          </div>
        </div>
      )}
      <label className="btn btn-default">
        <input type="text" placeholder="Title" onChange={e => setTitle(e.target.value)} />
      </label>
      <label className="btn btn-default">
        <input type="text" placeholder="Description" onChange={e => setDescription(e.target.value)} />
      </label>
      <label className="btn btn-default">
        <input type="file" onChange={selectFile} />
      </label>
      <button className="btn btn-warning" disabled={!selectedFiles} onClick={upload}>
        Upload
      </button>
      {/* <div className="alert alert-light" role="alert">
        {message}
      </div> */}
      {/* <Fragment>
      <div className="card">
        <div className="card-header">List of Files</div>
        <ul className="list-group list-group-flush">
          {fileInfos &&
            fileInfos.map((file, index) => (
              <li className="list-group-item" key={index}>
                <a href={file.url}>{file.name}</a>
              </li>
            ))}
        </ul>
      </div>
      </Fragment> */}
    </div>
  );
};
export default UploadFiles;
