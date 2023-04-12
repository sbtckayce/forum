<%@ page import="nlu.edu.vn.forum.models.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="user" scope="session" value="${sessionScope.user}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <!-- Thêm CSS của Bootstrap vào trang -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>

<div class="container">



        <div style="display: flex;align-items: center;justify-content: space-between;width: 100%;background: #f1f1f1;padding: 10px">
            <h4>Đăng nhập thành công chào mừng <span style="color: red">${user.username}</span> đến Trang chủ diễn đàn
            </h4>
            <a href="/logout" class=" ">Đăng xuất</a>
        </div>


        <div style="margin-top: 40px" class="col-md-10">
            <a href="/add-topic" type="button" class="mb-3 btn btn-primary">Tạo bài mới</a>

            <c:forEach items="${topics}" var="topics">
                <div class="card" style="margin-bottom: 20px">
                    <div class="card-header">
                            ${topics.title}
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">Bài mới nhất bởi ${topics.getCreator().getUsername()},<fmt:formatDate
                                value="${topics.createdTime.getTime()}"
                                pattern="HH:mm:ss dd-MM-yyyy"/></h5>
                        <a href="/topic-detail?id=${topics.id}" class="btn btn-primary">Xem chi tiết</a>
                    </div>
                </div>
            </c:forEach>

        </div>

</div>

<!-- Thêm các script của Bootstrap vào trang -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

</body>
</html>
