function checkValidationFromSignup() {
    let email = document.querySelector("#email").value;
    let name = document.querySelector("#name").value;
    let password = document.querySelector("#password").value;
    let phone = document.querySelector("#phone").value;

    if (email == "") {
        alert("이메일을 입력해주세요.");
        return false;
    }
    if (name == "") {
        alert("이름을 입력해주세요.");
        return false;
    }
    if (password == "") {
        alert("비밀번호를 입력해주세요.");
        return false;
    }
    if (phone == "") {
        alert("휴대폰번호를 입력해주세요.");
        return false;
    }

    let postObj = {
        email: email,
        name: name,
        password: password,
        phone: phone
    }

    let post = JSON.stringify(postObj)

    const url = "/api/members/save"
    let xhr = new XMLHttpRequest()

    xhr.open('POST', url, true)
    xhr.setRequestHeader('Content-type', 'application/json; charset=UTF-8')
    xhr.send(post);

    xhr.onload = function () {
        if (xhr.status === 201 || xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            console.log(json);
            if (json.value == true) {
                alert("회원 가입 완료!");
                document.location.href = "/login";
            } else {
                alert("회원 가입 실패! : " + json.message);
            }
        } else {
            alert("API 서버가 죽어있습니다. 서버 상태 : " + xhr.status);
            return false;
        }
    }
}

function sendData(type = '', method = '') {
    switch (type) {
        case 'boards' :
            let member_id = document.querySelector("#member_id").value;
            let member_name = document.querySelector("#member_name").value;
            let title = document.querySelector("#title").value;
            let isSecret = false;
            if(document.querySelector("#isSecret").value == '1') isSecret = true;

            let category = document.querySelector("#category").value;
            let postObj = {
                member_id: member_id,
                member_name : member_name,
                content: window.content.getData(),
                title: title,
                isSecret: isSecret,
                category: category
            }
            let post = JSON.stringify(postObj)

            const url = "/api/boards/save"
            let xhr = new XMLHttpRequest()

            xhr.open('POST', url, true)
            xhr.setRequestHeader('Content-type', 'application/json; charset=UTF-8')
            xhr.send(post);

            xhr.onload = function () {
                if (xhr.status === 201 || xhr.status === 200) {
                    var json = JSON.parse(xhr.responseText);
                    console.log(json);
                    if (json.value == true) {
                        alert("게시글 작성 완료!");
                        document.location.href = "/boards";
                    } else {
                        alert("게시글 작성 실패! : " + json.message);
                    }
                } else {
                    alert("API 서버가 죽어있습니다. 서버 상태 : " + xhr.status);
                    return false;
                }
            }
            break;
        default :
            break;
    }
}

document.addEventListener('DOMContentLoaded', function () {
    // Tab active
    let nowTab = window.location.pathname.split("/")[1];
    document.querySelectorAll(".navbar-nav.mr-auto > li")
        .forEach(function (each) {
            each.classList.remove('active')
        });
    switch (nowTab) {
        case "" :
        case "home" :
        case "main" :
        case "dashboard" :
            document.getElementById("nav-dashboard").classList.add("active");
            break;
        default :
            document.getElementById("nav-" + nowTab).classList.add("active");
            break;
    }

});