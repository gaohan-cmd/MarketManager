package service;

import bean.User;
import dao.MarketDao;
import menu.MarketMenu;

import java.util.Scanner;

public class MarketService {
    MarketMenu mm=new MarketMenu();
    MarketDao md=new MarketDao();
    public boolean  regATMService(Scanner sc){
        //获取注册时的输入信息
        //获取输入信息是没有账号的
        User u = mm.regMenu(sc);
        String code = getCode();
        u.setUserCode(code);
        //调用dao 把注册信息持久化
        boolean b = md.reMarket(u);
        if(b){
            System.out.println("您的卡号是："+code);
        }
        return b;
    }
    //登录的功能
    public User loginMarketService(Scanner sc){
        //得到登录时输入的值
        User u = mm.loginMenu(sc);
        //调用dao  查询信息
        User u1 = md.loginUser(u.getUserCode(),u.getUserPwd());
        //查询出的对象  存在  登录成功
        return u1;
    }
    //自动获取账号的方法
    public String getCode(){
        String code = "888";
        String maxId = ""+(md.getMaxId()+1);
        //判断补0的个数
        if(maxId.length()==1){
            code += "00"+maxId;
        }else if(maxId.length()==2){
            code += "0"+maxId;
        }else if(maxId.length()==3){
            code +=maxId;
        }
        return code;
    }


}
