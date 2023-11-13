<%--
  Created by IntelliJ IDEA.
  User: hagaj
  Date: 2023-11-13
  Time: 오후 6:44
  To change this template use File | Settings | File Templates.
--%>
<<%@ page language="java" contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8" %>
<%@ page import="com.myProject.api.APIService" %>

<html>
<head>
  <title>와이파이 정보 구하기</title>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<%--와이파이 개수 --%>
<%
  APIService apiService = new APIService();
  int count = apiService.getPublicWifiJson();
%>

<%--와이파이 개수 디스플레이 --%>
<div>
  <% if (count > 0) {%>
  <div style="text-align: center;">
    <h1 style="margin: 20px 0"><%=count%>건의 데이터를 성공적으로 저장했습니다.</h1>
    <a href="http://localhost:8080/PublicWifiService_war/main.jsp">홈으로 돌아가기</a>
  </div>
  <% } else { %>
  <h1 style="text-align: center">데이터 저장 실패</h1>
  <% } %>
</div>

</body>
</html>