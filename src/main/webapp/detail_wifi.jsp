<%@ page import="java.util.List" %>
<%@ page import="com.myProject.dto.WifiDTO" %>
<%@ page import="com.myProject.dao.WifiDAO" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.myProject.dao.BookMarkDAO" %>
<%@ page import="com.myProject.dto.BookMarkDTO" %>
<%@ page import="com.myProject.dto.BookMarkGroupDTO" %>
<%@ page import="com.myProject.dao.BookMarkGroupDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
</head>
<h1>와이파이 정보 구하기</h1>
<%@include file="header.jsp"%>
<body>
<%
  WifiDAO wifiDAO = new WifiDAO();
  String distance = request.getParameter("distance");
  String mgrNo = request.getParameter("mgrNo");
  String name = request.getParameter("wifiname");
  List<WifiDTO> wifiDTOList = wifiDAO.selectWifiList(mgrNo, Double.parseDouble(distance));

  BookMarkGroupDAO bookMarkGroupDAO = new BookMarkGroupDAO();
  List<BookMarkGroupDTO> bookMarkGroupDTOS = bookMarkGroupDAO.showBookMarkGroup();
  request.setAttribute("bookmarkList", bookMarkGroupDTOS);
%>

<form method="post" action="bookmarkAddList.jsp" id="bookmarkList">
  <select name="gId">
    <option value="none" selected>북마크 그룹 이름 선택</option>
    <% for (BookMarkGroupDTO bookMarkGroupDTO : bookMarkGroupDTOS) {%>
    <option value="<%=bookMarkGroupDTO.getId()%>">
      <%=bookMarkGroupDTO.getName()%>
    </option>
    <% } %>
  </select>
  <input type="submit" value="북마크 추가하기">
  <input type="hidden" name="mgrNo" value="<%=mgrNo%>">
</form>

<table>
  <% for (WifiDTO wifiDTO : wifiDTOList) {%>
  <tr>
    <th>거리(km)</th>
    <td><%=wifiDTO.getDistance()%></td>
  </tr>
  <tr>
    <th>관리번호</th>
    <td><%=wifiDTO.getXSwifiMgrNo()%></td>
  </tr>

  <tr>
    <th>자치구</th>
    <td><%=wifiDTO.getXSwifiWrdofc()%></td>
  </tr>
  <tr>
    <th>와이파이명</th>
    <td><%=wifiDTO.getXSwifiMainNm()%></td>
  </tr>

  <tr>
    <th>도로명 주소</th>
    <td><%=wifiDTO.getXSwifiAdres1()%></td>
  </tr>
  <tr>
    <th>상세 주소</th>
    <td><%=wifiDTO.getXSwifiAdres2()%></td>
  </tr>
  <tr>
    <th>설치 위치(층)</th>
    <td><%=wifiDTO.getXSwifiInstlFloor()%></td>
  </tr>
  <tr>
    <th>설치 기관</th>
    <td><%=wifiDTO.getXSwifiInstlMby()%></td>
  </tr>
  <tr>
    <th>설치 유형</th>
    <td><%=wifiDTO.getXSwifiInstlTy()%></td>
  </tr>
  <tr>
    <th>서비스 구분</th>
    <td><%=wifiDTO.getXSwifiSvcSe()%></td>
  </tr>
  <tr>
    <th>망 종류</th>
    <td><%=wifiDTO.getXSwifiCmcwr()%></td>
  </tr>
  <tr>
    <th>설치 년도</th>
    <td><%=wifiDTO.getXSwifiCnstcYear()%></td>
  </tr>
  <tr>
    <th>실내 외 구분</th>
    <td><%=wifiDTO.getXSwifiInoutDoor()%></td>
  </tr>
  <tr>
    <th>WIFI 접속 환경</th>
    <td><%=wifiDTO.getXSwifiRemars3()%></td>
  </tr>
  <tr>
    <th>x좌표</th>
    <td><%=wifiDTO.getLat()%></td>
  </tr>
  <tr>
    <th>y좌표</th>
    <td><%=wifiDTO.getLnt()%></td>
  </tr>
  <tr>
    <th>작업일자</th>
    <td><%=wifiDTO.getWorkDttm()%></td>
  </tr>
  <% } %>
</table>
</body>
</html>