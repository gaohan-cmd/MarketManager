package service;

import bean.Goods;
import bean.Type;
import bean.User;
import dao.MarketDao;
import menu.MarketMenu;

import java.util.Scanner;

public class MarketUserService {
    MarketMenu mm=new MarketMenu();
    MarketDao md=new MarketDao();
    //存款 把输入的存款金额  存入数据库
    public Goods addservice(Scanner sc, User u, Goods g, Type t){
        //user是操作对象
        User user = u;
        Goods goods=g;
        Type type=t;

        System.out.println("请输入商品名称：");
        String goodsName=sc.next();
        System.out.println("请输入商品价格：");
        Double goodsPrice=sc.nextDouble();
        System.out.println("请输入商品数目：");
        int goodsNum=sc.nextInt();
        System.out.println("请输入类型编号：");
        int typeId=sc.nextInt();
        g.setGoodsName(goodsName);
        g.setGoodsPrice(goodsPrice);
        g.setGoodsNum(goodsNum);
        t.setTypeId(typeId);

        //现在这个对象中的月就是存款后的金额  我们只需要持久化即可  存储在数据库中
        boolean b = md.add(user,type,goods);
        if(b){
            System.out.println("存款成功");
            return goods;
        }else{
            System.out.println("存款失败！！！");
            //如果失败 返回操作前的对象
            return g;
        }

    }
    //删除
    public Goods delservice(Scanner sc,Goods g){
        //操作对象
        Goods goods=g;
        System.out.println("请输入要删除的商品的id：");
        while(true){
            int goodsId = sc.nextInt();
            g.setGoodsId(goodsId);
            //取款我们要判断  金额>0
            if(goodsId<0){
                System.out.println("ID必须是正数,请重新输入！！！");
            }else{
                break;
            }
        }
        boolean b = md.del(g);
        if(b){
            System.out.println("删除成功");
            return goods;
        }else{
            System.out.println("删除失败");
            return g;
        }
    }

    //修改
    public Goods updservice(Scanner sc,Goods g){
        //操作对象
        Goods goods=g;
        System.out.println("请输入要修改的商品的id：");
        while(true){
            int goodsId = sc.nextInt();
            g.setGoodsId(goodsId);
            //取款我们要判断  金额>0
            if(goodsId<0){
                System.out.println("ID必须是正数,请重新输入！！！");
            }else{
                break;
            }
        }
        System.out.println("请输入要修改成的商品的名称：");
        String name=sc.next();
        g.setGoodsName(name);
        boolean b = md.upd(g);
        if(b){
            System.out.println("修改成功");
            return goods;
        }else{
            System.out.println("修改失败");
            return g;
        }
    }
    //查看
    public Goods findGoodsById(int goodsId){
        Goods g = md.findById(goodsId);
        System.out.println("------当前商品信息-------");
        System.out.println("| 商品名称："+g.getGoodsName()+"  |");
        System.out.println("| 商品价格："+g.getGoodsPrice()+" |");
        System.out.println("| 商品数目："+g.getGoodsNum()+"   |");
        System.out.println("| 入库时间："+g.getGoodsTime()+"  |");
        System.out.println("--------------------------");
        return g;
    }

    //业务处理
    public void UserService(Scanner sc, User u){
        //为了防止登录对象再操作后发生改变  我们自己定义一个操作对象用于使用
        Goods g=new Goods();
        Type t=new Type();
        //开始增删改查
        while(true){
            int index = mm.welcomeMarket(sc,u);
            if(index==1){
                //增加后，返回商品
                g = addservice(sc,u,g,t);
            }else if(index==2){
                //删除
                g = delservice(sc,g);
            }else if(index==3){
                //修改信息
                g=updservice(sc,g);
            }else if(index==4){
                //查询
                System.out.println("请输入您要查询商品的ID:");
                int goodsId=sc.nextInt();
                g.setGoodsId(goodsId);
                g = findGoodsById(g.getGoodsId());
            }else{
                //退出
                return;
            }
        }
    }
}
