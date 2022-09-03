import axios from 'axios';

const getBearerToken = () => {
  var authToken = localStorage.getItem('jhi-authenticationToken') || sessionStorage.getItem('jhi-authenticationToken');
  if (authToken) {
    authToken = JSON.parse(authToken);
    return `Bearer ${authToken}`;
  }
  return null;
};

export default axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    'Content-type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    Authorization: getBearerToken(),
  },
});
