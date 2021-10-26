package menu;

import bean.User;

import java.util.Scanner;

public class MarketMenu {
    //登录注册欢迎界面
    public int  regAndLoginMenu(Scanner sc){
        System.out.println("--------------------");
        System.out.println("|    1,注册         |");
        System.out.println("|    2,登录         |");
        System.out.println("|    3,退出         |");
        System.out.println("--------------------");
        System.out.println("请输入操作序号：");
        int index = sc.nextInt();
        return index;
    }
    //注册界面
    public User regMenu(Scanner sc){
        //账号需要自动生成  后面改进
        System.out.println("请输入密码：");
        String  pwd = sc.next();
        System.out.println("请输入名字：");
        String  name = sc.next();
        User u = new User();
        //此时改对象没有账号
        u.setUserName(name);
        u.setUserPwd(pwd);
        return u;
    }
    //登录的界面
    public User loginMenu(Scanner sc){
        System.out.println("请输入账号：");
        String  code = sc.next();
        System.out.println("请输入密码：");
        String  pwd = sc.next();
        User loginu = new User();
        loginu.setUserCode(code);
        loginu.setUserPwd(pwd);
        return loginu;
    }

    //登录成功界面
    public int  welcomeMarket(Scanner sc,User u){
        System.out.println("--------------------");
        System.out.println("---欢迎"+u.getUserName()+"使用超市管理系统系统------");
        System.out.println("|    1,增加商品         |");
        System.out.println("|    2,删除商品         |");
        System.out.println("|    3,修改商品         |");
        System.out.println("|    4,查看信息         |");
        System.out.println("|    5,退出         |");
        System.out.println("--------------------");
        System.out.println("请输入操作序号：");
        int index = sc.nextInt();
        return index;
    }
}
