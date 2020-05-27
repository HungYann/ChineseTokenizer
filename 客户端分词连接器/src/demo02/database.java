package demo02;

import java.sql.*;
  
public class database{  
     String url = "jdbc:mysql://localhost:3306/book";  
    String name = "com.mysql.jdbc.Driver";  
    String user = "root";  
   String password = "lhy942821";  
   
    
    Connection conn = null;  
    PreparedStatement pstmt =null;
    Statement stmt;
    String sql;
  
    public database() {  
        try {  
        	//第一步加载驱动
            Class.forName(name);//指定连接类型         
            conn = DriverManager.getConnection(url, user, password);//获取连接    
            conn.createStatement();
           
           
            String username = null;
            String password = null;
            String mail = null;
            
            
            String select = "select * from user";
            ResultSet rs = stmt.executeQuery(select);
            
            while(rs.next())
            {
            	System.out.println(rs.getString("usernmae")+rs.getString("password"));
            }
            
       
            String deleteSQL = "delete from post where id=1";
            stmt.executeUpdate(deleteSQL);
            
            
        
            
            sql= "insert into user(user,name,password,mail) values(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.setString(3,mail);
            pstmt.executeUpdate(sql);
            
         
            
          
            sql = "update user set username = ? where id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.executeUpdate(sql);
            
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  

    }  

