package com.algoma.ears.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.algoma.ears.Jobs;
import com.algoma.ears.util.DataAccessObject;

public class JobsDAO extends DataAccessObject<Jobs>{
    private static final String INSERT= "INSERT INTO jobs (title,description) VALUES(?,?)";
    private static final String GET_ONE = "SELECT job_id, title, description FROM jobs WHERE job_id =?";
    private static final String GET_BY_TITTLE = "SELECT * FROM jobs WHERE title = ?";
    private static final String GET_ALL ="SELECT job_id, title, description FROM jobs";
    private static final String UPDATE= "UPDATE jobs SET title = ?, description = ? WHERE job_id = ?";
    private static final String DELETE="DELETE FROM jobs WHERE job_id = ?";

    public JobsDAO(Connection connection){
        super(connection);
    }

    @Override
    public Jobs findById(long id){
        Jobs foundJob = new Jobs();
        try(PreparedStatement st = this.connection.prepareStatement(GET_ONE);){
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                foundJob.setId(rs.getLong("job_id"));
                foundJob.setTitle(rs.getString("title"));
                foundJob.setDescription(rs.getString("description"));
            }
        }catch(SQLException e){
            
            throw new RuntimeException(e);
        }
        return foundJob;
    }

    @Override
    public List<Jobs> findAll(){
        List<Jobs> foundJobs = new ArrayList<>();
        try(PreparedStatement st =this.connection.prepareStatement(GET_ALL) ){
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Jobs job = new Jobs();
                job.setId(rs.getLong("job_id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                foundJobs.add(job);
            }
        }catch(SQLException e){
            
            throw new RuntimeException(e);
        }
        return foundJobs;
    }
    public List<Jobs> findByTitle(String title){
        List<Jobs> foundJobs = new ArrayList<>();
        try(PreparedStatement st =this.connection.prepareStatement(GET_BY_TITTLE) ){
            st.setString(1,title);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Jobs job = new Jobs();
                job.setId(rs.getLong("job_id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                foundJobs.add(job);
            }
        }catch(SQLException e){
            
            throw new RuntimeException(e);
        }
        return foundJobs;
    }

    @Override
    public Jobs create(Jobs dto){
        try(PreparedStatement st = this.connection.prepareStatement(INSERT);){
            st.setString(1,dto.getTitle());
            st.setString(2,dto.getDescription());
            st.execute();
            long id= this.getLastValue("JOBS_SEQUENCE");
            return this.findById(id);
        }catch(SQLException e){
            
            throw new RuntimeException(e);
        }
    }
    @Override
    public Jobs update(Jobs dto){
        Jobs job = null;
        try{
            this.connection.setAutoCommit(false);
        }catch(SQLException e){
            
            throw new RuntimeException(e);
        }
        try(PreparedStatement st = this.connection.prepareStatement(UPDATE);){
            st.setString(1,dto.getTitle());
            st.setString(2,dto.getDescription());
            st.setLong(3,dto.getId());
            st.execute();
            this.connection.commit();
            job =  this.findById(dto.getId());
        }catch(SQLException e){
            try{
                this.connection.rollback();
            }catch(SQLException se){
                
                throw new RuntimeException(se);
            }
            
            throw new RuntimeException(e);
        }finally{
            try {
                this.connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return job;
    }

    @Override
    public void delete(long id){
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try(PreparedStatement st =this.connection.prepareStatement(DELETE)){
            st.setLong(1,id);
            st.execute();
            this.connection.commit();
        }catch(SQLException e){
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
            throw new RuntimeException(e);
        }finally{
            try {
                this.connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
