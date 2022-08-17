package com.ljx.springcloud.pojo;

public class goodsFenLei extends goods{
    //展示商品列表
    // 商品
//    private Integer id;
    //商品名称
//    private String goodsName;
    //商品描述
//    private String detail;
    //商品库存
//    private Integer goodsStock;
    //商品分类名称
    private String categoryName;
    //商品状态
//    private Integer state;

    public goodsFenLei() {
    }

    public goodsFenLei(Integer goodsId, String goodsName, Integer goodsPrice, Integer goodsSales, Integer goodsStock, String detail, Integer categoryId, Integer defaultSize, Integer state, String tupian, Integer goodsHot, Integer goodsNew, String categoryName) {
        super(goodsId, goodsName, goodsPrice, goodsSales, goodsStock, detail, categoryId, defaultSize, state, tupian, goodsHot, goodsNew);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
