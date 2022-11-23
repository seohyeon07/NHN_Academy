<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수업 등록</title>
</head>
<body>
<h2>수업 등록</h2>
+ 담당 교수 : <span>${course.getTeacher().getName()}</span></br>
+ 과목 명 : <span>${course.getSubject().getName()}</span> </br>
<a href="/courses/update/${course.getId()}">수정</a>
<a href="/courses/delete/${course.getId()}">삭제</a>
</body>
</html>
