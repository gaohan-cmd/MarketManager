package test;

import bean.User;
import menu.MarketMenu;
import service.MarketService;
import service.MarketUserService;

import java.util.Scanner;

public class MarketTest {
    public static void main(String[] args) {
        MarketMenu mm=new MarketMenu();
        MarketService ms=new MarketService();
        MarketUserService mus=new MarketUserService();
        Scanner sc=new Scanner(System.in);
        while(true){
            int index =  mm.regAndLoginMenu(sc);
            if(index==1){
                //注册
                boolean b = ms.regATMService(sc);
                if(b){
                    System.out.println("注册成功");
                }else{
                    System.out.println("注册失败");
                }
            }else if(index==2){
                //登录  登录成功时，我们得到了一个对象
                User u = ms.loginMarketService(sc);
                if(u!=null){
                    //整个登录成功后的操作 都在这service方法中
                    mus.UserService(sc,u);
                }else{
                    System.out.println("登录失败");
                }
            }else{
                //退出
                break;
            }
        }
    }
}
