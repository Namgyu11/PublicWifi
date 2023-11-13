package com.myProject.dao;

import com.myProject.db.DBConnect;
import com.myProject.dto.Search_HistoryDTO;

import java.sql.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Search_HistoryDAO {

    public static void searchHistory(String lat, String lnt) {
        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = dbConnect.getConnection();

            DateFormatSymbols dfs = new DateFormatSymbols(Locale.KOREAN);
            dfs.setWeekdays(new String[]{
                    "unused",
                    "일요일",
                    "월요일",
                    "화요일",
                    "수요일",
                    "목요일",
                    "금요일",
                    "토요일"
            });
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd '('E')' HH:mm:ss", dfs);
            String strDate = sdf.format(new Date());

            String sql = " insert into search_wifi "
                    + " (lat, lnt, search_dttm) "
                    + " values ( ?, ?, ? )";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, lat);
            preparedStatement.setString(2, lnt);
            preparedStatement.setString(3, strDate.toString());

            preparedStatement.executeUpdate();

            System.out.println("!! 데이터 삽입 완료 !!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnect.close(connection, rs, preparedStatement);
        }
    }

    public List<Search_HistoryDTO> searchHistoryList() {
        List<Search_HistoryDTO> list = new ArrayList<>();
        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = dbConnect.getConnection();

            String sql = " select * "
                    + " from search_wifi "
                    + " order by id desc ";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                Search_HistoryDTO historyDTO = new Search_HistoryDTO(
                        rs.getInt("id")
                        , rs.getString("lat")
                        , rs.getString("lnt")
                        , rs.getString("search_dttm")
                );
                list.add(historyDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnect.close(connection, rs, preparedStatement);
        }
        return list;
    }

    public void deleteHistoryList(String id){
        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = dbConnect.getConnection();

            String sql = " delete from search_wifi where id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dbConnect.close(connection,rs,preparedStatement);
        }
    }
}
