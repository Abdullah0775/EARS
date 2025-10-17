package com.algoma.ears.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.algoma.ears.Application;
import com.algoma.ears.util.DataAccessObject;

public class AppDAO extends DataAccessObject<Application>{

    private static final String INSERT="INSERT INTO applications(job_id, resume) VALUES (?,?)";
    private static final String UPDATE="UPDATE applications SET job_id = ?,resume = ?, status = ? WHERE app_id = ?";
    private static final String DELETE="DELETE FROM applications WHERE app_id = ?";
    private static final String GET_ONE="SELECT * FROM applications WHERE app_id=?";
    private static final String GET_ALL="SELECT * FROM applications";
    private static final String GET_BY_JOBID= "SELECT * FROM applications WHERE job_id=?";
    public AppDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Application findById(long id) {
        Application na = new Application();
        try(PreparedStatement st = this.connection.prepareStatement(GET_ONE)){
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                na.setId(rs.getLong("app_Id"));
                na.setJobId(rs.getLong("job_Id"));
                na.setResume(rs.getString("resume"));
                na.setStatus(rs.getString("status"));
            }
        }catch(SQLException | IOException e){
            throw new RuntimeException(e);
        }

        return na;
    }

    @Override
    public List<Application> findAll() {
        List<Application> apps = new ArrayList<>();
        try(PreparedStatement st = this.connection.prepareStatement(GET_ALL)){
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Application na = new Application();
                na.setId(rs.getLong("app_Id"));
                na.setJobId(rs.getLong("job_Id"));
                na.setResume(rs.getString("resume"));
                na.setStatus(rs.getString("status"));
                apps.add(na);
            }
        }catch(SQLException | IOException e){
            throw new RuntimeException(e);
        }
        return apps;
    } 
    // Find applications by Job ID, no need to join right?
    public List<Application> findByJobId(long jobId) {
        List<Application> apps = new ArrayList<>();
        try(PreparedStatement st = this.connection.prepareStatement(GET_BY_JOBID)){
            st.setLong(1, jobId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Application na = new Application();
                na.setId(rs.getLong("app_Id"));
                na.setJobId(rs.getLong("job_Id"));
                na.setResume(rs.getString("resume"));
                na.setStatus(rs.getString("status"));
                apps.add(na);
            }
        }catch(SQLException | IOException e){
            throw new RuntimeException(e);
        }
        return apps;
    }

    @Override
    public Application update(Application dto) {
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try(PreparedStatement st = this.connection.prepareStatement(UPDATE)){
            st.setLong(1,dto.getJobId());
            st.setString(2,dto.getResume());
            st.setString(3,dto.getStatus());
            st.setLong(4,dto.getId());
            st.execute();
            this.connection.commit();
        }catch(SQLException e){
            try {
                this.connection.rollback();
            } catch ( SQLException se) {
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }finally{
            try {
                this.connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return this.findById(dto.getId());
    }

    @Override
    public Application create(Application dto) {
        try(PreparedStatement st = this.connection.prepareStatement(INSERT)){
            
            st.setLong(1, dto.getJobId());
            st.setString(2,dto.getResume());
            st.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        long id = this.getLastValue("app_seq");
        return this.findById(id);
    }

    @Override
    public void delete(long id) {
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
                throw new RuntimeException(se);
            }
            throw new RuntimeException(e);
        }finally{
            try {
                this.connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
}
