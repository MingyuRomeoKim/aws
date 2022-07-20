function checkValidationFromSignup() {
    let email = document.querySelector("#email").value;
    let name = document.querySelector("#name").value;
    let password = document.querySelector("#password").value;
    let phone = document.querySelector("#phone").value;

    if(email == "") {
        alert("이메일을 입력해주세요.");
        return false;
    }
    if(name == "") {
        alert("이름을 입력해주세요.");
        return false;
    }
    if(password == "") {
        alert("비밀번호를 입력해주세요.");
        return false;
    }
    if(phone == "") {
        alert("휴대폰번호를 입력해주세요.");
        return false;
    }

    let postObj = {
        email: email,
        name: name,
        password: password,
        phone : phone
    }

    let post = JSON.stringify(postObj)

    const url = "/members/save"
    let xhr = new XMLHttpRequest()

    xhr.open('POST', url, true)
    xhr.setRequestHeader('Content-type', 'application/json; charset=UTF-8')
    xhr.send(post);

    xhr.onload = function () {
        if(xhr.status === 201 || xhr.status === 200) {
            alert("회원 가입 완료!");
            document.location.href="/users";
        }else{
            alert("회원 가입 실패. 무언가 잘못 되었어요. 관리자에게 문의하세요.");
            return false;
        }
    }
}