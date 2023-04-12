<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forum</title>
    <!-- Thêm CSS của Bootstrap vào trang -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5 ">
            <h1 style="margin-top: 100px;margin-bottom: 100px" class="row justify-content-center">Login</h1>
            <form id="login-form">
                <div class="form-group">
                    <label for="username">Tên tài khoản</label>
                    <input name="username" type="text" class="form-control" id="username"
                           aria-describedby="emailHelp" placeholder="admin">
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="text" name="password" class="form-control" id="password"
                           placeholder="admin">
                </div>
                <button onclick="login()" type="submit" class="btn btn-primary">Đăng nhập</button>
            </form>
        </div>
    </div>
</div>

<script>
    function login() {
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        const xhr = new XMLHttpRequest();
        xhr.open("POST", "/login");
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.onload = function() {
            if (xhr.status === 200) {
                if (xhr.responseText === "success") {
                    window.location.href = '/forum'
                } else {
                    alert("Tài khoản hoặc mật khẩu không đúng!");
                }
            }
        };
        xhr.send(JSON.stringify({
            "username": username,
            "password": password
        }));
    }

    const loginForm = document.getElementById("login-form");
    loginForm.addEventListener("submit", function(event) {
        event.preventDefault();
        login();
    });
</script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

</body>
</html>
