<%@ page import="com.myProject.dao.BookMarkDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.myProject.dto.BookMarkDTO" %>
<%@ page import="com.myProject.dto.BookMarkGroupDTO" %>
<%@ page import="com.myProject.dao.BookMarkGroupDAO" %>
<%@ page import="com.myProject.dao.WifiDAO" %>
<%@ page import="com.myProject.dto.WifiDTO" %><%--
  Created by IntelliJ IDEA.
  User: KDMIN
  Date: 2023-09-27
  Time: 오후 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>북마크 목록</h1>
<%@ include file="header.jsp"%>

<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>북마크 이름</th>
    <th>와이파이명</th>
    <th>등록일자</th>
    <th>비고</th>
  </tr>
  </thead>
  <tbody>
  <%
    BookMarkDAO bookMarkDAO = new BookMarkDAO();
    if (bookMarkDAO.count() == 0) {
  %>
  <tr>
    <td colspan="5">데이터가 존재하지 않습니다.</td>
  </tr>
  <% } else {
    List<BookMarkDTO> bookMarkDTOList = bookMarkDAO.showBookMarklist();
    for (BookMarkDTO bookMarkDTO : bookMarkDTOList) {
      BookMarkGroupDAO bookMarkGroupDAO = new BookMarkGroupDAO();
      BookMarkGroupDTO bookMarkGroupDTO = bookMarkGroupDAO.selectBookMarkGroup(bookMarkDTO.getGroupNo());

      WifiDAO wifiDAO = new WifiDAO();
      WifiDTO wifiDTO = wifiDAO.selectWifi(bookMarkDTO.getMgrNo());
  %>
  <tr>
    <td><%=bookMarkDTO.getGroupNo()%></td>
    <td><%=bookMarkGroupDTO.getName()%></td>
    <td>
      <a href="detail_wifi.jsp?mgrNo=<%=wifiDTO.getXSwifiMgrNo()%>">
        <%=wifiDTO.getXSwifiMainNm()%>
      </a>
    </td>
    <td><%=bookMarkDTO.getRegister_dttm()%></td>
    <td><a href="bookmarkDelete.jsp?id=<%=bookMarkDTO.getId()%>"> 삭제 </a></td>
  </tr>
  <% } } %>
  </tbody>
</table>

</body>
</html>