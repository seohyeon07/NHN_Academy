<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>수업 목록</title>
</head>
<body>
<h1>수업 목록</h1>
<table border="1">
    <th>NO.</th>
    <th>담당 교수</th>
    <th>과목 명</th>
    <th>등록날짜</th>
    <c:forEach var ="course" items="${requestScope.courseList}">
        <tr>
            <td>${course.getId()}</td>
            <td>${course.getTeacher().getName()}</td>
            <td><a href="/courses/course/${course.getId()}">${course.getSubject().getName()}</a></td>
            <td>${course.getCreatedAt()}</td>
        </tr>
    </c:forEach>
</table>

<button><a href="/courses/register">강의 등록하기</a></button>

</body>
</html>
