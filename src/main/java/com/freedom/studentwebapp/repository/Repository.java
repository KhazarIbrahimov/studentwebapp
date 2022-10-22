package com.freedom.studentwebapp.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public abstract class Repository<T> {

    protected Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/freedom", "root", "");
    }


    public abstract List<T> getList();

    public abstract T getById(Integer id);

    public abstract T insert(T person);

    public abstract boolean update(T person);

    public abstract boolean delete(Integer id);

}
