package util;
import java.sql.*;

//封装连接数据库的过程   第二版
// 1，加载驱动 一次就够了
//2,每一个线程把自己的连接放到自己的ThreadLocal中  随身携带
public class DBUtil{
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn(){
        //每一个需要连接的线程  都先判断自己随身携带的ThreadLocal中是否已经有Connection
        Connection conn = tl.get();
        if(conn == null){
            //tl中没有连接  创建新连接  放到ThreadLocal容器中  并返回
            try {
                String url="jdbc:mysql://localhost:3306/supermarket?serverTimezone=UTC";
                conn = DriverManager.getConnection(url,"root","root");
                tl.set(conn);//把新的连接放到ThreadLocal容器中
                return conn;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            return conn;
        }
        return conn;
    }
    //关闭的方法  一个顶俩
    public static void getColse(ResultSet rs, PreparedStatement psta,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(psta!=null){
            try {
                psta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //关闭Connection时  需要先判断是否已关闭或者为空
        //关闭Connection时 需要把ThreadLocal中的Connection置为null
        try {
            if (conn != null && !conn.isClosed()) {
                //先关闭
                conn.close();
                //置为null
                tl.set(null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
