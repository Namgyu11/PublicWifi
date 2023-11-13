<%@ page import="com.myProject.dao.BookMarkGroupDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.myProject.dto.BookMarkGroupDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>북마크 그룹</h1>
<%@ include file="header.jsp"%>
<button onclick="location.href = 'bookmarkGroupAdd.jsp'">북마크 그룹 이름 추가</button>
<br>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>북마크 이름</th>
    <th>순서</th>
    <th>등록일자</th>
    <th>수정일자</th>
    <th>비고</th>
  </tr>
  </thead>
  <tbody>
  <%
    BookMarkGroupDAO bookMarkGroupDAO = new BookMarkGroupDAO();
    if (bookMarkGroupDAO.groupCount() == 0) {
  %>
  <tr>
    <td colspan="6"> 데이터가 존재하지 않습니다.</td>
  </tr>
  <% } else {
    List<BookMarkGroupDTO> bookMarkGroupDTOList = bookMarkGroupDAO.showBookMarkGroup();
    for (BookMarkGroupDTO bookMarkGroupDTO : bookMarkGroupDTOList) {
      String update = bookMarkGroupDTO.getUpdate_dttm() == null ? "" : bookMarkGroupDTO.getUpdate_dttm();
  %>
  <tr>
    <td><%=bookMarkGroupDTO.getId()%></td>
    <td><%=bookMarkGroupDTO.getName()%></td>
    <td><%=bookMarkGroupDTO.getOrder()%></td>
    <td><%=bookMarkGroupDTO.getRegister_dttm()%></td>
    <td><%=update%></td>
    <td>
      <a href="bookmarkGroupUpdate.jsp?id=<%=bookMarkGroupDTO.getId()%>">수정</a>
      <a href="bookmarkGroupDelete.jsp?id=<%=bookMarkGroupDTO.getId()%>">삭제</a>
    </td>
  </tr>
  <% } } %>
  </tbody>
</table>
</body>
</html>