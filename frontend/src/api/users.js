import axios from 'axios'

function userTest(data,token){
    const url = '/api/users/test'
    return axios.post(url,data,{ headers: { 'authtoken': token } });
}

export { userTest };