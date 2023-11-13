package com.myProject.dao;


import com.myProject.db.DBConnect;
import com.myProject.dto.BookMarkGroupDTO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookMarkGroupDAO {


    public int groupCount() {   //그룹에 현재 몇 개가 있는지 파악!!
        int count = 0;

        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = dbConnect.getConnection();
            String sql = " select count(*) from bookmark_group ";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.close(connection, rs, preparedStatement);
        }

        return count;
    }

    public int saveBookMarkGroup(BookMarkGroupDTO bookMarkGroupDTO){
        int affected = 0;

        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = dbConnect.getConnection();
            String sql = " insert into bookmark_group(name, order_no, register_dttm) "
                    + " values (?, ?, ?)" ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookMarkGroupDTO.getName());
            preparedStatement.setInt(2, bookMarkGroupDTO.getOrder());
            preparedStatement.setString(3, bookMarkGroupDTO.getRegister_dttm());

            affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("북마크 그룹 데이터 삽입 완료");
            } else {
                System.out.println("북마크 그룹 데이터 삽입 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.close(connection, rs, preparedStatement);
        }

        return affected;
    }

    public BookMarkGroupDTO selectBookMarkGroup(int id) {

        BookMarkGroupDTO bookMarkGroupDTO = new BookMarkGroupDTO();

        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = dbConnect.getConnection();

            String sql = " select * from bookmark_group where id = ? " ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bookMarkGroupDTO.setId(rs.getInt("id"));
                bookMarkGroupDTO.setName(rs.getString("name"));
                bookMarkGroupDTO.setOrder(rs.getInt("order_no"));
                bookMarkGroupDTO.setRegister_dttm(rs.getString("register_dttm"));
                bookMarkGroupDTO.setUpdate_dttm(rs.getString("update_dttm"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.close(connection, rs, preparedStatement);
        }

        return bookMarkGroupDTO;
    }
    public List<BookMarkGroupDTO> showBookMarkGroup() {
        List<BookMarkGroupDTO> list = new ArrayList<>();

        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = dbConnect.getConnection();

            String sql = " select * from bookmark_group order by id ";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BookMarkGroupDTO bookMarkGroupDTO = BookMarkGroupDTO.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .order(rs.getInt("order_no"))
                        .register_dttm(rs.getString("register_dttm"))
                        .update_dttm(rs.getString("update_dttm"))
                        .build();

                list.add(bookMarkGroupDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.close(connection, rs, preparedStatement);
        }

        return list;
    }

    public int updateBookMarkGroup(int id, String name, int order) {
        int result = 0;

        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;


        try {
            connection = dbConnect.getConnection();
            String sql = " update bookmark_group set name = ?, order_no = ?, update_dttm = ? where id = ? ";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, order);
            preparedStatement.setString(3, String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
            preparedStatement.setInt(4, id);

            result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println("!! 북마크 그룹 데이터 업데이트 성공 !!");
            } else {
                System.out.println("!! 북마크 그룹 데이터 업데이트 실패! !!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.close(connection, rs, preparedStatement);
        }

        return result;
    }

    public int deleteBookMarkGroup(int id) {
        int affected = 0;

        DBConnect dbConnect = new DBConnect();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = dbConnect.getConnection();

            preparedStatement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            preparedStatement.executeUpdate();

            String sql = " delete from bookmark_group where id = ? ";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("북마크 그룹 데이터 삭제 완료");
            } else {
                System.out.println("북마크 그룹 데이터 삭제 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.close(connection, rs, preparedStatement);
        }

        return affected;
    }

}