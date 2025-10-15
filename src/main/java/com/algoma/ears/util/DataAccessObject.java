package com.algoma.ears.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public abstract class DataAccessObject <T extends DataTransferObject>{
    protected final Connection connection;
    protected final String LAST_VAL = "SELECT last_value FROM ";

    public DataAccessObject(Connection connection){
        super();
        this.connection = connection;
    }
    public abstract T findById(long id);
    public abstract List<T> findAll();
    public abstract T update(T dto);
    public abstract T create(T dto);
    public abstract void delete(long id);

    public long getLastValue(String seq){
        long key = 0;
        String sql = LAST_VAL + seq;
        try (PreparedStatement s = connection.prepareStatement(sql)){
            ResultSet rs = s.executeQuery();
            while(rs.next()){
                key = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
}
