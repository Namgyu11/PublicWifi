# PublicWifi
서울시 공공 와이파이를 제공하는 웹서비스
# Tech Stack
- Language : Java
- Build System : Maven
- DBMS : MariaDB
- Application Server : Tomcat 8.5
- JDK : JDK 1.8
- Web : HTML5, CSS, JSP
- Library : Lombok, Okhttp3, Gson
# 프로젝트 구현 순서 및 기능 
- 서울시 공공데이터 Open API를 활용해 서울시 공공 와이파이 서비스 위치 정보 데이터를 가져옵니다.
- 내 위치 정보를 입력하면 가까운 위치에 있는 와이파이 정보 20개를 보여줍니다.
- 내가 입력한 위치정보에 대해서 조회하는 시점에 DB에 히스토리를 저장하고 보여줍니다.
- 선택한 와이파이의 상세정보를 보여줍니다.
- 사용자가 북마크 그룹을 추가, 수정, 삭제 기능을 활용할 수 있습니다.
- 사용자가 선택한 북마크에 원하는 와이파이 정보를 북마크에 추가 및 삭제 기능을 활용할 수 있습니다.
