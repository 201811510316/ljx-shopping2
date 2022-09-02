package com.ljx.springcloud.pojo;
/**
 *     展示商品列表
 *     商品goodsId,
 *     商品名称goodsName,
 *     商品描述detail,
 *     商品库存goodsStock,
 *     商品分类名称categoryName,
 *     商品状态state
 */
public class goodsFenLei extends goods{

    //商品分类名称
    private String categoryName;

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
