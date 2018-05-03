package BelajarJDBC.dao;

import BelajarJDBC.model.Nasabah;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoNasabah {
    
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_Name = "belajarjpa";
    public static final String DB_URL = "jdbc:mysql://localhost/"+DB_Name;
    
    public static final String USER = "root";
    public static final String PASS = "";
    public static final String tbl_nasabah = "nasabah";
    Connection conn = null;
    Statement stmt = null;
    
    public DaoNasabah(){
        create_database();
        create_table_nasabah();
    }
    
    private void openConnection(){
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(ClassNotFoundException ex){
            Logger.getLogger(DaoNasabah.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException ex){
            Logger.getLogger(DaoNasabah.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void closeConnection(){
        try{
            if(conn!=null)conn.close();
        }catch(SQLException ex){
             Logger.getLogger(DaoNasabah.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void save(Nasabah nasabah){
        openConnection();
        try{
            if(conn==null){
                System.out.println("conn is null");
                return;
            }
            stmt = conn.createStatement();
            String sql = "INSERT INTO " + tbl_nasabah
                    + " (user, pass, saldo)"
                    + "Values ("
                    + "'" + nasabah.getUser() + "'"
                    + ", '" + nasabah.getPass() + "', "
                    + nasabah.getSaldo()
                    +")";
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
             Logger.getLogger(DaoNasabah.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }
    
    public void update(long id, Nasabah nasabah){
        openConnection();
        try{
            if(conn==null){
                System.out.println("conn is null");
                return;
            }
            stmt = conn.createStatement();
            String sql = " UPDATE " + tbl_nasabah
                    + " SET user = '" +nasabah.getUser()+"'"
                    + ", pass = '" +nasabah.getPass()+"'"
                    + ", saldo = " +nasabah.getSaldo()
                    + " WHERE id ="+id;
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
             Logger.getLogger(DaoNasabah.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }
    
    public void delete(long id){
        openConnection();
        try{
            if(conn==null){
                System.out.println("conn is null");
                return;
            }
            stmt = conn.createStatement();
            String sql = "DELETE FROM " + tbl_nasabah +
                    " WHERE id ="+id;
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
             Logger.getLogger(DaoNasabah.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }
    
    public Nasabah findByUser(Nasabah nasabah){
        Nasabah new_nasabah = null;
        openConnection();
        try{
            if(conn==null){
                System.out.println("conn is null");
                return null;
            }
            stmt = conn.createStatement();
            String sql = "SELECT * FROM " + tbl_nasabah + " WHERE user ='" + nasabah.getUser()+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.first()){
                
                long nid = rs.getLong("id");
                String user = rs.getString("user");
                String pass = rs.getString("pass");
                long saldo = rs.getLong("saldo");
                if(pass.equals(nasabah.getPass())){
                    new_nasabah = new Nasabah(nid, user, pass, saldo);
                }
                
                rs.close();
            }
        }catch(SQLException ex){
             Logger.getLogger(DaoNasabah.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return new_nasabah;
    }
    
    public Nasabah findById(long id){
        Nasabah nasabah = null;
        openConnection();
        try{
            if(conn==null){
                System.out.println("conn is null");
                return null;
            }
            stmt = conn.createStatement();
            String sql = "SELECT * FROM " + tbl_nasabah + " WHERE id =" + id;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.first()){
                
                long nid = rs.getLong("id");
                String user = rs.getString("user");
                String pass = rs.getString("pass");
                long saldo = rs.getLong("saldo");
                nasabah = new Nasabah(nid, user, pass, saldo);
                
                rs.close();
            }
        }catch(SQLException ex){
             Logger.getLogger(DaoNasabah.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return nasabah;
    }
    
    private void create_database(){
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost/", USER, PASS);
            
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS "+DB_Name;
            stmt.executeUpdate(sql);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch(SQLException se2){
            }
            try{
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    
    private void create_table_nasabah(){
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            stmt = conn.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS "+tbl_nasabah+" "
                    + "(id INTEGER NOT NULL AUTO_INCREMENT, "
                    + " user VARCHAR(255), "
                    + " pass VARCHAR(255), "
                    + " saldo BIGINT, "
                    + " PRIMARY KEY ( id ))";
            
            stmt.executeUpdate(sql);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch(SQLException se2){
            }
            try{
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
