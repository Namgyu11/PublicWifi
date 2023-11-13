package com.myProject.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.myProject.db.DBConnect;
import com.myProject.dto.WifiDTO;
import static com.myProject.dao.Search_HistoryDAO.searchHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDAO {
    public WifiDAO(){

    }

    public static int insertPublicWifi(JsonArray jsonArray) {
        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        int cnt = 0;

        try {
            connection = dbConnect.getConnection();
            connection.setAutoCommit(false);    //Auto-Commit 해제

            String sql = " insert into public_wifi "
                    + " ( x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, "
                    + " x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr, "
                    + " x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lat, lnt, work_dttm) "
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < jsonArray.size(); i++) {

                JsonObject data = (JsonObject) jsonArray.get(i).getAsJsonObject(); // JSON 배열의 i번째 요소를 가져와 JsonObject로 변환


                preparedStatement.setString(1, data.get("X_SWIFI_MGR_NO").getAsString());
                preparedStatement.setString(2, data.get("X_SWIFI_WRDOFC").getAsString());
                preparedStatement.setString(3, data.get("X_SWIFI_MAIN_NM").getAsString());
                preparedStatement.setString(4, data.get("X_SWIFI_ADRES1").getAsString());
                preparedStatement.setString(5, data.get("X_SWIFI_ADRES2").getAsString());
                preparedStatement.setString(6, data.get("X_SWIFI_INSTL_FLOOR").getAsString());
                preparedStatement.setString(7, data.get("X_SWIFI_INSTL_TY").getAsString());
                preparedStatement.setString(8, data.get("X_SWIFI_INSTL_MBY").getAsString());
                preparedStatement.setString(9, data.get("X_SWIFI_SVC_SE").getAsString());
                preparedStatement.setString(10, data.get("X_SWIFI_CMCWR").getAsString());
                preparedStatement.setString(11, data.get("X_SWIFI_CNSTC_YEAR").getAsString());
                preparedStatement.setString(12, data.get("X_SWIFI_INOUT_DOOR").getAsString());
                preparedStatement.setString(13, data.get("X_SWIFI_REMARS3").getAsString());
                preparedStatement.setString(14, data.get("LAT").getAsString());
                preparedStatement.setString(15, data.get("LNT").getAsString());
                preparedStatement.setString(16, data.get("WORK_DTTM").getAsString());

                preparedStatement.addBatch();
                preparedStatement.clearParameters();

            }

            int[] result = preparedStatement.executeBatch();
            cnt += result.length;    //배치한 완료 개수
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbConnect.close(connection,rs,preparedStatement);
        }

        return cnt;
    }
    public List<WifiDTO> selectWifiList(String mgrNo, double distance) {

        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        List<WifiDTO> list = new ArrayList<>();

        try {
            connection = dbConnect.getConnection();
            String sql = " select * from public_wifi where x_swifi_mgr_no = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mgrNo);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                WifiDTO wifiDTO = WifiDTO.builder()
                        .distance(distance)
                        .xSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"))
                        .xSwifiWrdofc(rs.getString("X_SWIFI_WRDOFC"))
                        .xSwifiMainNm(rs.getString("X_SWIFI_MAIN_NM"))
                        .xSwifiAdres1(rs.getString("X_SWIFI_ADRES1"))
                        .xSwifiAdres2(rs.getString("X_SWIFI_ADRES2"))
                        .xSwifiInstlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"))
                        .xSwifiInstlTy(rs.getString("X_SWIFI_INSTL_TY"))
                        .xSwifiInstlMby(rs.getString("X_SWIFI_INSTL_MBY"))
                        .xSwifiSvcSe(rs.getString("X_SWIFI_SVC_SE"))
                        .xSwifiCmcwr(rs.getString("X_SWIFI_CMCWR"))
                        .xSwifiCnstcYear(rs.getString("X_SWIFI_CNSTC_YEAR"))
                        .xSwifiInoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"))
                        .xSwifiRemars3(rs.getString("X_SWIFI_REMARS3"))
                        .lat(rs.getString("LAT"))
                        .lnt(rs.getString("LNT"))
                        .workDttm(String.valueOf(rs.getTimestamp("work_dttm").toLocalDateTime()))
                        .build();
                list.add(wifiDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.close(connection,rs,preparedStatement);
        }

        return list;
    }
    public List<WifiDTO> getNearestWifiList(String lat, String lnt){
        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        List<WifiDTO> list = new ArrayList<>();
        try {
            connection = dbConnect.getConnection();

            // 위도 경도 구하기
            String sql = " SELECT *, " +
                    " round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT) " +
                    " -radians(?))+sin(radians(?))*sin(radians(LAT))), 4) " +
                    " AS distance " +
                    " FROM public_wifi " +
                    " ORDER BY distance " +
                    " LIMIT 20;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, Double.parseDouble(lat));
            preparedStatement.setDouble(2, Double.parseDouble(lnt));
            preparedStatement.setDouble(3, Double.parseDouble(lat));

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                WifiDTO wifiDTO = WifiDTO.builder()
                        .distance(rs.getDouble("distance"))
                        .xSwifiMgrNo(rs.getString("x_swifi_mgr_no"))
                        .xSwifiWrdofc(rs.getString("x_swifi_wrdofc"))
                        .xSwifiMainNm(rs.getString("x_swifi_main_nm"))
                        .xSwifiAdres1(rs.getString("x_swifi_adres1"))
                        .xSwifiAdres2(rs.getString("x_swifi_adres2"))
                        .xSwifiInstlFloor(rs.getString("x_swifi_instl_floor"))
                        .xSwifiInstlTy(rs.getString("x_swifi_instl_ty"))
                        .xSwifiInstlMby(rs.getString("x_swifi_instl_mby"))
                        .xSwifiSvcSe(rs.getString("x_swifi_svc_se"))
                        .xSwifiCmcwr(rs.getString("x_swifi_cmcwr"))
                        .xSwifiCnstcYear(rs.getString("x_swifi_cnstc_year"))
                        .xSwifiInoutDoor(rs.getString("x_swifi_inout_door"))
                        .xSwifiRemars3(rs.getString("x_swifi_remars3"))
                        .lat(rs.getString("lat"))
                        .lnt(rs.getString("lnt"))
                        .workDttm(String.valueOf(rs.getTimestamp("work_dttm").toLocalDateTime()))
                        .build();

                list.add(wifiDTO);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbConnect.close(connection,rs,preparedStatement);
        }
        searchHistory(lat, lnt); //해당 값을 조회한 경우 history 데이터에 추가
        return list;
    }
    public WifiDTO selectWifi(String mgrNo){
        WifiDTO wifiDTO = new WifiDTO();

        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
           connection = dbConnect.getConnection();
           String sql =  " select * from public_wifi where x_swifi_mgr_no = ? ";

           preparedStatement = connection.prepareStatement(sql);

           preparedStatement.setString(1,mgrNo);
           rs = preparedStatement.executeQuery();

           while (rs.next()){
               wifiDTO.setXSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"));
               wifiDTO.setXSwifiWrdofc(rs.getString("X_SWIFI_WRDOFC"));
               wifiDTO.setXSwifiMainNm(rs.getString("X_SWIFI_MAIN_NM"));
               wifiDTO.setXSwifiAdres1(rs.getString("X_SWIFI_ADRES1"));
               wifiDTO.setXSwifiAdres2(rs.getString("X_SWIFI_ADRES2"));
               wifiDTO.setXSwifiInstlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
               wifiDTO.setXSwifiInstlTy(rs.getString("X_SWIFI_INSTL_TY"));
               wifiDTO.setXSwifiInstlMby(rs.getString("X_SWIFI_INSTL_MBY"));
               wifiDTO.setXSwifiSvcSe(rs.getString("X_SWIFI_SVC_SE"));
               wifiDTO.setXSwifiCmcwr(rs.getString("X_SWIFI_CMCWR"));
               wifiDTO.setXSwifiCnstcYear(rs.getString("X_SWIFI_CNSTC_YEAR"));
               wifiDTO.setXSwifiInoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
               wifiDTO.setXSwifiRemars3(rs.getString("X_SWIFI_REMARS3"));
               wifiDTO.setLat(rs.getString("LAT"));
               wifiDTO.setLnt(rs.getString("LNT"));
               wifiDTO.setWorkDttm(String.valueOf(rs.getTimestamp("work_dttm").toLocalDateTime()));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbConnect.close(connection,rs,preparedStatement);
        }
        return wifiDTO;
    }
}
