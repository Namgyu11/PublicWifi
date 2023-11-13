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
<%
  request.setCharacterEncoding("UTF-8");

  String name = request.getParameter("name");
  String orderNo = request.getParameter("order_no");

  BookMarkGroupDTO bookMarkGroupDTO = new BookMarkGroupDTO();
  bookMarkGroupDTO.setName(name);
  bookMarkGroupDTO.setOrder(Integer.parseInt(orderNo));
  bookMarkGroupDTO.setRegister_dttm(String.valueOf(new java.sql.Timestamp(new java.util.Date().getTime())));

  BookMarkGroupDAO bookMarkGroupDAO = new BookMarkGroupDAO();
  int affected = bookMarkGroupDAO.saveBookMarkGroup(bookMarkGroupDTO);
%>
</body>
<script>
  <%
      String text = affected > 0 ? "북마크 그룹 데이터를 추가하였습니다." : "북마크 그룹 데이터를 추가하지 못했습니다.";
  %>
  alert("<%= text %>");
  location.href = "bookmarkGroup.jsp";
</script>
</html>