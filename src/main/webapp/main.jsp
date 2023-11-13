<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="com.myProject.dao.WifiDAO" %>
<%@ page import="java.util.*" %>
<%@ page import="com.myProject.dto.WifiDTO" %>

<html>
<head>
  <title>와이파이 정보 구하기</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<%
  String lat = request.getParameter("lat") == null ? "0.0" : request.getParameter("lat");
  String lnt = request.getParameter("lnt") == null ? "0.0" : request.getParameter("lnt");
%>
<a href="http://localhost:8080/PublicWifiService_war/main.jsp">홈</a>
<a href="#">|</a>
<a href="http://localhost:8080/PublicWifiService_war/history.jsp">위치 히스토리 목록</a>
<a href="#">|</a>
<a href="http://localhost:8080/PublicWifiService_war/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<a href="#">|</a>
<a href="http://localhost:8080/PublicWifiService_war/bookmarkList.jsp">북마크 보기</a>
<a href="#">|</a>
<a href="http://localhost:8080/PublicWifiService_war/bookmarkGroup.jsp">북마크 그룹 관리</a>

<div class="input">
  <span>LAT:</span>
  <input type="text" id="lat" value="">

  <span>LNT:</span>
  <input type="text" id="lnt" value="">

  <button id="btn_cur_position"><span>내 위치 가져오기</span></button>
  <button id="btn_nearest_wifi"><span>근처 Wifi 정보 보기</span></button>
</div>


<div>
  <table>
    <thead>
    <tr>
      <th>거리(km)</th>
      <th>관리번호</th>
      <th>자치구</th>
      <th>와이파이명</th>

      <th>도로명 주소</th>
      <th>상세 주소</th>

      <th>설치 위치(층)</th>
      <th>설치 기관</th>
      <th>설치 유형</th>

      <th>서비스 구분</th>
      <th>망 종류</th>
      <th>설치 년도</th>
      <th>실내 외 구분</th>
      <th>WIFI 접속 환경</th>

      <th>x좌표</th>
      <th>y좌표</th>
      <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <%
      if (!("0.0").equals(lat) && !("0.0").equals(lnt)) {
        WifiDAO wifiDAO = new WifiDAO();
        List<WifiDTO> list = wifiDAO.getNearestWifiList(lat, lnt);

        if (list != null) {
          for (WifiDTO wifiDTO : list) {
    %>
    <tr>
      <td><%=wifiDTO.getDistance()%></td>
      <td><%=wifiDTO.getXSwifiMgrNo()%></td>
      <td><%=wifiDTO.getXSwifiWrdofc()%></td>
      <td><a href="detail_wifi.jsp?mgrNo=<%= wifiDTO.getXSwifiMgrNo() %>&distance=<%=wifiDTO.getDistance()%>&wifiname=<%=wifiDTO.getXSwifiMainNm()%>"><%= wifiDTO.getXSwifiMainNm() %></a></td>
      <td><%=wifiDTO.getXSwifiAdres1()%></td>
      <td><%=wifiDTO.getXSwifiAdres2()%></td>
      <td><%=wifiDTO.getXSwifiInstlFloor()%></td>
      <td><%=wifiDTO.getXSwifiInstlMby()%></td>
      <td><%=wifiDTO.getXSwifiInstlTy()%></td>
      <td><%=wifiDTO.getXSwifiSvcSe()%></td>
      <td><%=wifiDTO.getXSwifiCmcwr()%></td>
      <td><%=wifiDTO.getXSwifiCnstcYear()%></td>
      <td><%=wifiDTO.getXSwifiInoutDoor()%></td>
      <td><%=wifiDTO.getXSwifiRemars3()%></td>
      <td><%=wifiDTO.getLat()%></td>
      <td><%=wifiDTO.getLnt()%></td>
      <td><%=wifiDTO.getWorkDttm()%></td>
    </tr>
    <% } %>
    <% } %>
    <% } else { %>
    <td colspan='17'> 위치 정보를 입력하신 후에 조회해 주세요. </td>
    <% } %>
    </tbody>
  </table>
</div>

<script>
  let getCurPosition = document.getElementById("btn_cur_position");
  let getNearestWifi = document.getElementById("btn_nearest_wifi");

  let lat = null;
  let lnt = null;

  window.onload = () => {
    lat = document.getElementById("lat").value;
    lnt = document.getElementById("lnt").value;
  }

  getCurPosition.addEventListener("click", function () {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition(function (position){
        let latitude = position.coords.latitude;
        let longitude = position.coords.longitude;
        document.getElementById("lat").value = latitude;
        document.getElementById("lnt").value = longitude;
      })
    } else{
      alert("위치 정보를 확인할 수 없으니 직접 입력해주시기 바랍니다.")
    }
  });

  getNearestWifi.addEventListener("click", function (){
    let latitude = document.getElementById("lat").value;
    let longitude = document.getElementById("lnt").value;

    if (latitude !== "" || longitude !== "") {
      window.location.assign("http://localhost:8080/PublicWifiService_war/main.jsp?lat=" + latitude + "&lnt=" + longitude);
    } else {
      alert("위치 정보를 입력하신 후에 조회해주세요.")
    }
  })
</script>

</body>
</html>