package com.ljx.springcloud.pojo;

//统计支付商品的数量
public class goodsSum{
    private Integer goodsId;
    private String goodsName;
    private Integer count;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public goodsSum() {
    }

    public goodsSum(Integer goodsId, String goodsName, Integer count) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.count = count;
    }
    //当一个集合内有两个或者多个一样商品，就商品数量相加
    public static goodsSum merge(goodsSum goodsSum1,goodsSum goodsSum2){
        if(!goodsSum1.equals(goodsSum2)){
            throw new IllegalArgumentException();
        }
        return new goodsSum(goodsSum1.goodsId,goodsSum1.goodsName,goodsSum1.count+goodsSum2.count);
    }
    //重写equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        goodsSum goodsSum = (goodsSum) o;
        if(goodsId ==null){
            if(goodsSum.goodsId !=null){
                return false;
            }
        }else if(!goodsId.equals(goodsSum.goodsId)){
            return false;
        }
        return true;
    }
    //重写hashCode
    @Override
    public int hashCode() {
        final int prime=30;
        int result=1;
        result= prime*result+((goodsId == null)?0:goodsId.hashCode());
        return result;
    }
}
