<%@ page import="com.myProject.dao.BookMarkGroupDAO" %>
<%@ page import="com.myProject.dto.BookMarkGroupDTO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>북마크 그룹 수정</h1>
<%@ include file="header.jsp"%>
<%
  request.setCharacterEncoding("UTF-8");

  String id = request.getParameter("id");
  String name = request.getParameter("name");
  String order = request.getParameter("order_no");

  BookMarkGroupDAO bookMarkGroupDAO = new BookMarkGroupDAO();
  int affected = bookMarkGroupDAO.updateBookMarkGroup(Integer.parseInt(id), name, Integer.parseInt(order));
%>
</body>
<script>
  <%
      String text = affected > 0 ? "북마크 그룹 데이터를 수정하였습니다." : "북마크 그룹 데이터를 수정하지 못했습니다.";
  %>
  alert("<%= text %>");
  location.href = "bookmarkGroup.jsp";
</script>
</html>