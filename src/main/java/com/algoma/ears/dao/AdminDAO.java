package com.algoma.ears.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.algoma.ears.Admin;
import com.algoma.ears.util.DataAccessObject;
public class AdminDAO extends DataAccessObject<Admin> {
    private final static String INSERT ="INSERT INTO admins(admin_user_id, admin_password, admin_name) VALUES (?, ?, ?)";
    private final static String DELETE= "DELETE FROM admins WHERE admin_id = ?";
    private final static String UPDATE ="UPDATE admins SET admin_user_id= ?, admin_password = ?, admin_name = ? WHERE admin_id = ?";
    private final static String GET_ONE ="SELECT admin_id, admin_user_id, admin_password, admin_name FROM admins WHERE admin_id = ?";
    private final static String GET_ALL ="SELECT admin_id, admin_user_id, admin_password, admin_name FROM admins";
    private final static String GET_BY_USER ="SELECT admin_id, admin_user_id, admin_password, admin_name FROM admins WHERE admin_user_id = ?"; 

    public AdminDAO(Connection con){
        super(con);
    }

    @Override
    public Admin findById(long id){
        Admin rtAdmin = new Admin();
        try(PreparedStatement st = this.connection.prepareStatement(GET_ONE)){
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                rtAdmin.setId(rs.getLong("admin_id"));
                rtAdmin.setUserId(rs.getString("admin_user_id"));
                rtAdmin.setPassword(rs.getString("admin_password"));
                rtAdmin.setName(rs.getString("admin_name"));
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return rtAdmin;
    }

    public Admin findByUser(String userId){
        Admin rtAdmin = new Admin();
        try(PreparedStatement st = this.connection.prepareStatement(GET_BY_USER)){
            st.setString(1,userId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                rtAdmin.setId(rs.getLong("admin_id"));
                rtAdmin.setUserId(rs.getString("admin_user_id"));
                rtAdmin.setPassword(rs.getString("admin_password"));
                rtAdmin.setName(rs.getString("admin_name"));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return rtAdmin;
    }

    @Override
    public List<Admin> findAll(){
        List<Admin> admins = new ArrayList<>();
        try(PreparedStatement st = this.connection.prepareStatement(GET_ALL)){
            Admin rtAdmin= new Admin();
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                rtAdmin.setId(rs.getLong("admin_id"));
                rtAdmin.setUserId(rs.getString("admin_user_id"));
                rtAdmin.setPassword(rs.getString("admin_password"));
                rtAdmin.setName(rs.getString("admin_name"));
            }
            admins.add(rtAdmin);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return admins;
    }
    @Override
    public Admin create(Admin dto){
        try(PreparedStatement st = this.connection.prepareStatement(INSERT)){
            st.setString(1,dto.getName());
            st.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        long id = this.getLastValue("ADMIN_SEQUENCE");
        Admin admin = findById(id);
        return admin;
    }

    @Override
    public Admin update(Admin dto){
        Admin admin = null;
        try{
            this.connection.setAutoCommit(false);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        try(PreparedStatement st= this.connection.prepareStatement(UPDATE)){
            st.execute();
            this.connection.commit();
            admin =this.findById(dto.getId());
        }catch(SQLException e){
            try{
                this.connection.rollback();
            }catch(SQLException sqle){
                throw new RuntimeException(sqle);
            }
            throw new RuntimeException(e);
        }finally{
            try {
                this.connection.setAutoCommit(true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return admin;
    }

    @Override
    public void delete(long id){
        try(PreparedStatement st = this.connection.prepareStatement(DELETE)){
            st.setLong(1,id);
            st.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

}
