import http from './http-common';

const upload = (file, onUploadProgress, title, description) => {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('title', title);
  formData.append('description', description);
  return http.post('api/videos/upload', formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    onUploadProgress,
  });
};
const getFiles = () => {
  return http.get('/videos/');
};
export default {
  upload,
  getFiles,
};
