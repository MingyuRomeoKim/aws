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

    const url = "/api/members/save"
    let xhr = new XMLHttpRequest()

    xhr.open('POST', url, true)
    xhr.setRequestHeader('Content-type', 'application/json; charset=UTF-8')
    xhr.send(post);

    xhr.onload = function () {
        if(xhr.status === 201 || xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            console.log(json);
            if(json.value == true) {
                alert("회원 가입 완료!");
                document.location.href="/users";
            }else {
                alert("회원 가입 실패! : "+json.message);
            }
        }else{
            alert("API 서버가 죽어있습니다. 서버 상태 : "+xhr.status);
            return false;
        }
    }
}

document.addEventListener('DOMContentLoaded', function() {
    // Tab active
    let nowTab = window.location.pathname.split("/")[1];
    document.querySelectorAll(".navbar-nav.mr-auto > li")
        .forEach(function(each){
            each.classList.remove('active')
        });
    switch (nowTab) {
        case "" :
        case "home" :
        case "main" :
        case "dashboard" : document.getElementById("nav-dashboard").classList.add("active"); break;
        default : document.getElementById("nav-"+nowTab).classList.add("active"); break;
    }

});