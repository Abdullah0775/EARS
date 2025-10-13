package com.algoma.ears.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.algoma.ears.Faculty;
import com.algoma.ears.util.DataAccessObject;


public class FacultyDAO extends DataAccessObject<Faculty> {
    private final static String INSERT = "INSERT INTO faculty(faculty_user,faculty_password,faculty_name, subject) VALUES(?,?,?,?)";
    private final static String DELETE = "DELTE FROM faculty WHERE faculty_id = ?";
    private static final String UPDATE = "UPDATE faculty SET faculty_user = ?, faculty_password=?, faculty_name = ?, subject = ? WHERE faculty_id = ?";
    private static final String GET_ONE= "SELECT faculty_id, faculty_user, faculty_password, faculty_name, subject FROM faculty WHERE faculty_id = ?";
    private static final String GET_BY_USER= "SELECT faculty_id, faculty_user, faculty_password, faculty_name, subject FROM faculty WHERE faculty_user = ?";
    private static final String GET_ALL= "SELECT faculty_id, faculty_user, faculty_password, faculty_name, subject FROM faculty";

    public FacultyDAO(Connection con){
        super(con);
    }

    @Override 
    public Faculty create(Faculty dto){
        try(PreparedStatement st= this.connection.prepareStatement(INSERT)){
            st.setString(1, dto.getUserId());
            st.setString(2, dto.getPassword());
            st.setString(3, dto.getName());
            st.setString(4, dto.getSubject());
            st.execute();
           
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        long id = this.getLastValue("FACULTY_SEQUENCE");
        return findById(id);
    }
    @Override
    public Faculty findById(long id){
        Faculty rf = new Faculty();
        try(PreparedStatement st = this.connection.prepareStatement(GET_ONE)){
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                rf.setId(rs.getLong("faculty_id"));
                rf.setUserId(rs.getString("faculty_user"));
                rf.setPassword(rs.getString("faculty_password"));
                rf.setName(rs.getString("faculty_name"));
                rf.setSubject(rs.getString("subject"));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return  rf;
    }
    public Faculty findByUser(String user){
    Faculty rf = new Faculty();
        try(PreparedStatement st = this.connection.prepareStatement(GET_BY_USER)){
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                rf.setId(rs.getLong("faculty_id"));
                rf.setUserId(rs.getString("faculty_user"));
                rf.setPassword(rs.getString("faculty_password"));
                rf.setName(rs.getString("faculty_name"));
                rf.setSubject(rs.getString("subject"));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return  rf;
} 
    @Override
    public List<Faculty> findAll(){
        List<Faculty> facultys = new ArrayList<>();
        try(PreparedStatement st = this.connection.prepareStatement(GET_ALL)){
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Faculty rf = new Faculty();
                rf.setId(rs.getLong("faculty_id"));
                rf.setUserId(rs.getString("faculty_user"));
                rf.setPassword(rs.getString("faculty_password"));
                rf.setName(rs.getString("faculty_name"));
                rf.setSubject(rs.getString("subject"));
                facultys.add(rf);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return  facultys;
}
    @Override
    public Faculty update(Faculty dto){
    try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try(PreparedStatement st = this.connection.prepareStatement(UPDATE)){
            st.setString(1,dto.getUserId());
            st.setString(2,dto.getPassword());
            st.setString(3,dto.getName());
            st.setString(4,dto.getSubject());
            st.setLong(5,dto.getId());
            st.execute();
            this.connection.commit();
        }catch(SQLException e){
            try {
                this.connection.rollback();
            } catch ( SQLException se) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        }finally{
            try {
                this.connection.setAutoCommit(true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return findById(dto.getId());
    }

    @Override
    public void delete(long id){
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try(PreparedStatement st = this.connection.prepareStatement(DELETE)){
            st.setLong(1,id);
            st.execute();
            this.connection.commit();
        }catch(SQLException e){
            try {
                this.connection.rollback();
            } catch ( SQLException se) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException(e);
        }finally{
             try {
                this.connection.setAutoCommit(true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
