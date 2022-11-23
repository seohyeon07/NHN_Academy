<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수업 등록</title>
</head>
<body>
<h2>수업 등록</h2>
<form method="post">
    <input type="hidden" value="${course.getId()}">
    + 담당 교수 :
    <select name="teacherId">
        <c:forEach var="teacher" items="${requestScope.teacherList}">
            <option value="${teacher.getId()}">${teacher.getName()}</option>
        </c:forEach>
    </select>
     + 과목 명 :
    <select name="subjectId">
    <c:forEach var="subject"  items="${requestScope.subjectList}">
        <option value="${subject.getId()}">${subject.getName()}</option>
    </c:forEach>
    </select>

    <input type="submit" value="등록하기">
</form>
</body>
</html>
