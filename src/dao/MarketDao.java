package dao;

import bean.Goods;
import bean.Type;
import bean.User;
import util.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MarketDao {
    Connection conn= null;
    PreparedStatement psta = null;
    ResultSet rs = null;
    //注册
    public boolean reMarket(User u){
        try {
            conn = DBUtil.getConn();
            String sql = "insert into user(user_code,user_pwd,user_name) values(?,?,?)";
            psta = conn.prepareStatement(sql);
            psta.setString(1, u.getUserCode());
            psta.setString(2,u.getUserPwd());
            psta.setString(3,u.getUserName());
            //执行
            int num = psta.executeUpdate();
            if(num>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.getColse(rs,psta,conn);
        }
        return false;

    }
    //登录 根据账号密码验证
    public User loginUser(String userCode,String userPwd){
        try {
            conn = DBUtil.getConn();
            String sql ="select *  from user where user_code=? and user_pwd=?";
            psta = conn.prepareStatement(sql);
            //拼装sql
            psta.setString(1,userCode);
            psta.setString(2,userPwd);
            rs = psta.executeQuery();
            //登录  我们要求账号唯一，查出结果应该是一条数据
            if(rs.next()){
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUserName((rs.getString("user_name")));
                u.setUserCode(rs.getString("user_code"));
                u.setUserPwd(rs.getString("user_pwd"));
                return u;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.getColse(rs,psta,conn);
        }
        return null;
    }

    //增加商品
    public boolean add(User u, Type t, Goods g){
        conn=DBUtil.getConn();
        int flag;
        String sql ="insert into goods(goods_name,goods_price,goods_num,goods_time,user_id,type_id) values(?,?,?,?,?,?)";
        try {
            psta = conn.prepareStatement(sql);
            //拼装sql
            psta.setString(1,g.getGoodsName());
            psta.setDouble(2,g.getGoodsPrice());
            psta.setInt(3,g.getGoodsNum());
            java.util.Date d=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
            String time=sdf.format(d);
            psta.setString(4,time);
            psta.setInt(5,u.getUserId());
            psta.setInt(6,t.getTypeId());
            flag = psta.executeUpdate();
            if(flag>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return false;

    }

    //删除商品
    public boolean del(Goods g){
        conn=DBUtil.getConn();
        int flag;
        String sql ="delete from goods where goods_id=?";
        try {
            psta = conn.prepareStatement(sql);
            //拼装sql
            psta.setInt(1,g.getGoodsId());
            flag = psta.executeUpdate();
            if(flag>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    //修改商品
    public boolean upd(Goods g){
        conn=DBUtil.getConn();
        int flag;
        String sql ="update goods set goods_name=? where goods_id=?";
        try {
            psta = conn.prepareStatement(sql);
            //拼装sql
            psta.setString(1,g.getGoodsName());
            psta.setInt(2,g.getGoodsId());
            flag = psta.executeUpdate();
            if(flag>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    //查询商品
    public Goods findById(int goodsId){
        try{
            conn = DBUtil.getConn();
            String sql = "select *  from goods where goods_id=?";
            psta = conn.prepareStatement(sql);
            psta.setInt(1,goodsId);
            rs = psta.executeQuery();
            //单挑查询  结果是唯一的
            if(rs.next()){
                Goods g = new Goods();
                g.setGoodsId(rs.getInt("goods_id"));
                g.setGoodsName(rs.getString("goods_name"));
                g.setGoodsPrice(rs.getDouble("goods_price"));
                g.setGoodsNum(rs.getInt("goods_num"));
                g.setGoodsTime(rs.getDate("goods_time"));
                g.setUserId(rs.getInt("user_id"));
                g.setTypeId(rs.getInt("type_id"));
                return g;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.getColse(rs,psta,conn);
        }
        return null;
    }

    //  自动生成卡号
    public int getMaxId(){
        try{
            conn = DBUtil.getConn();
            String sql = "select max(user_id) maxId from  user ";
            psta = conn.prepareStatement(sql);
            rs = psta.executeQuery();
            if(rs.next()){
                //int maxId = rs.getInt(1);
                int maxId = rs.getInt("maxId");
                return maxId;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.getColse(rs,psta,conn);
        }
        return 0;
    }

}
