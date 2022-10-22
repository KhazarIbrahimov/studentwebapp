package com.freedom.studentwebapp.repository;


import com.freedom.studentwebapp.entity.University;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniversityRepository extends Repository<University>{

    @Override
    public List<University> getList() {
        //try() icerisinde yalniz Closeable ve ya AutoCloseable olan object-ler istifade oluna biler.
        try(Connection connection = connect()) {
            //Interface
            Statement statement = connection.createStatement();
            //Interface
            ResultSet rs = statement.executeQuery("select * from university");

            List<University> list = new ArrayList<>();
            while (rs.next()){
                list.add(new University(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address")
                ));
            }
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public University getById(Integer id)  {
        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("select * from teacher where id=?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            List<University> list = new ArrayList<>();
            if (rs.next()){
                return new University(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address")
                );
            }else {
                return null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public University insert(University university) {
        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into university(name) values(?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, university.getName());

            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()){
                university.setId(rs.getInt(1));
            }
            return university;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(University university) {
        try(Connection connection = connect()) {
            String sql = "update university set ";
            int index = 1;
            if (university.getName()!=null){
                sql+= "name=?";
                index++;
            }

            sql += " where id=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(index, university.getId());
            index--;

            if (university.getName()!=null){
                statement.setString(index, university.getName());
            }

            return statement.execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id){
        try(Connection connection = connect()) {
            PreparedStatement statement = connection.prepareStatement("delete from university where id=?");
            statement.setInt(1, id);

            return statement.execute();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
