import axios from 'axios'

function registerLogin(userData){
    const url = '/api/login'
    return axios.post(url,userData,{ headers: { 'Content-Type': 'application/json' } });
}

function loginChk(token){
    const url = '/api/users/test'
    
    axios
    .post(url,"dd",{ headers: { 'authtoken': token } })
    .then((response) => {
        let tokenChk = response.headers.authorization
        // 토큰이 없다면 Home 화면으로 이동
        if( tokenChk === 'notLogin'){
            alert('로그인이 필요합니다.');
            return false;

            //window.location.href='http://localhost:8080/login';
        }
        else if( tokenChk === 'tokenIsNull' ){
            alert('인증이 만료되었습니다.');
            return false;
            //window.location.href='http://localhost:8080/login';
        }else{
            console.log('인증 성공')
            return true;
        }
    })
    .catch(err =>{
        console.log(err)
        return false;
    });
}

export { registerLogin, loginChk };