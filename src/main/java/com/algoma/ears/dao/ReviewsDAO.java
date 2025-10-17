package com.algoma.ears.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.algoma.ears.Reviews;
import com.algoma.ears.util.DataAccessObject;

public class ReviewsDAO extends DataAccessObject<Reviews> {

    private static final String INSERT ="INSET INTO reviews(rev_id, fac_id, app_id, content) VALUES(?,?,?,?)";
    private static final String GET_ONE ="SELECT * FROM  reviews WHERE rev_id = ?";
    private static final String GET_ALL="SELECT * FROM  reviews";
    private static final String UPDATE="UPDATE reviews SET fac_id = ?, app_id = ? , content = ? WHERE rev_id=?";
    private static final String DELETE ="DELETE FROM reviews WHERE rev_id =?";
    private static final String GET_BY_APPID="SELECT * FROM reviews WHERE app_id = ?"; 
    private static final String GET_BY_FACID="SELECT * FROM reviews WHERE fac_id = ?";

    public ReviewsDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Reviews findById(long id) {
        Reviews rv = new Reviews();
        try (PreparedStatement st = this.connection.prepareStatement(GET_ONE)){
            st.setLong(1,id);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                rv.setId(rs.getLong("rev_id"));
                rv.setFacultyId(rs.getLong("fac_id"));
                rv.setApplicationId(rs.getLong("app_id"));
                rv.setReviewContent(rs.getString("content"));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rv;
    }

    @Override
    public List<Reviews> findAll() {
        List<Reviews> rvs = new ArrayList<>();
        try (PreparedStatement st = this.connection.prepareStatement(GET_ALL)){
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                Reviews rv = new Reviews();
                rv.setId(rs.getLong("rev_id"));
                rv.setFacultyId(rs.getLong("fac_id"));
                rv.setApplicationId(rs.getLong("app_id"));
                rv.setReviewContent(rs.getString("content"));
                rvs.add(rv);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rvs;
    }

    
    public List<Reviews> findByAppId(long appId) {
        List<Reviews> rvs = new ArrayList<>();
        try (PreparedStatement st = this.connection.prepareStatement(GET_BY_APPID)){
            st.setLong(1, appId);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                Reviews rv = new Reviews();
                rv.setId(rs.getLong("rev_id"));
                rv.setFacultyId(rs.getLong("fac_id"));
                rv.setApplicationId(rs.getLong("app_id"));
                rv.setReviewContent(rs.getString("content"));
                rvs.add(rv);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rvs;
    }
    
    public List<Reviews> findByFacId(long faculty_id) {
        List<Reviews> rvs = new ArrayList<>();
        try (PreparedStatement st = this.connection.prepareStatement(GET_BY_FACID)){
            st.setLong(1,faculty_id);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                Reviews rv = new Reviews();
                rv.setId(rs.getLong("rev_id"));
                rv.setFacultyId(rs.getLong("fac_id"));
                rv.setApplicationId(rs.getLong("app_id"));
                rv.setReviewContent(rs.getString("content"));
                rvs.add(rv);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rvs;
    }


    @Override
    public Reviews update(Reviews dto) {
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement st = this.connection.prepareStatement(UPDATE)) {
            st.setLong(1,dto.getFacultyId());
            st.setLong(2,dto.getApplicationId());
            st.setString(3,dto.getReviewContent());
            st.setLong(4, dto.getId());
            st.execute();
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException se) {
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
        return this.findById(dto.getId());
    }

    @Override
    public Reviews create(Reviews dto) {
        try (PreparedStatement st = this.connection.prepareStatement(INSERT)) {
            st.setLong(1, dto.getId());
            st.setLong(2, dto.getFacultyId());
            st.setLong(3,dto.getApplicationId());
            st.setString(4, dto.getReviewContent());
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this.findById(dto.getId());
    }

    @Override
    public void delete(long id) {
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement st = this.connection.prepareStatement(DELETE)) {
            st.setLong(1, id);
            st.execute();
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException es) {
                throw new RuntimeException(es);
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
