package bean;

import java.util.Date;
import java.util.Objects;

public class Goods {
    private int goodsId;
    private String goodsName;
    private Double goodsPrice;
    private Date goodsTime;
    private int goodsNum;
    private int userId;
    private int typeId;

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Date getGoodsTime() {
        return goodsTime;
    }

    public void setGoodsTime(Date goodsTime) {
        this.goodsTime = goodsTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return goodsId == goods.goodsId && goodsNum == goods.goodsNum && userId == goods.userId && typeId == goods.typeId && Objects.equals(goodsName, goods.goodsName) && Objects.equals(goodsPrice, goods.goodsPrice) && Objects.equals(goodsTime, goods.goodsTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsName, goodsPrice, goodsTime, goodsNum, userId, typeId);
    }
}
