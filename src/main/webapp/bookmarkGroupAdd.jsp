<%@ page import="com.myProject.dao.BookMarkDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.myProject.dto.BookMarkGroupDTO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.myProject.dao.BookMarkGroupDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>북마크 그룹 추가</h1>
<%@ include file="header.jsp"%>

<form action="bookmarkGroupAdd_submit.jsp">
  <table>
    <tr>
      <th>북마크 이름</th>
      <td><input type="text" name="name"></td>
    </tr>
    <tr>
      <th>순서</th>
      <td><input type="text" name="order_no"></td>
    </tr>
    <tr>
      <td style="text-align: center;" colspan="2">
        <input type="submit" value="추가">
      </td>
    </tr>
  </table>
</form>

</body>
</html>