<%@ page import="com.myProject.dao.BookMarkGroupDAO" %>
<%@ page import="com.myProject.dto.BookMarkGroupDTO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>북마크 그룹 수정</h1>
<%@ include file="header.jsp"%>
<%
  String id = request.getParameter("id");

  BookMarkGroupDAO bookMarkGroupDAO = new BookMarkGroupDAO();
  BookMarkGroupDTO bookMarkGroupDTO = bookMarkGroupDAO.selectBookMarkGroup(Integer.parseInt(id));
%>

<form method="post" action="bookmarkGroupUpdate_submit.jsp" >
  <table>
    <tr>
      <th>북마크 이름</th>
      <td><input type="text" name="name" value="<%=bookMarkGroupDTO.getName()%>"></td>
    </tr>
    <tr>
      <th>순서</th>
      <td><input type="text" name="order_no" value="<%=bookMarkGroupDTO.getOrder()%>"></td>
    </tr>
    <tr>
      <td colspan="2">
        <a href="bookmarkGroup.jsp">돌아가기</a>
        <input type="submit" name="update" value="수정">
        <input type="hidden" name="id" value="<%=bookMarkGroupDTO.getId()%>">
      </td>
    </tr>
  </table>
</form>

</body>
</html>