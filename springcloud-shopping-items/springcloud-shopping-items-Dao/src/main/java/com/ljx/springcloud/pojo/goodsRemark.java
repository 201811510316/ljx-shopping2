package com.ljx.springcloud.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="goods_remark")
public class goodsRemark {

    //商品备注id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //商品id（外键）
    private Integer goodsId;

    //商品备注标题
    private String remarkKey;

    //商品备注内容
    private String remarkVal;

    public goodsRemark() {
    }

    public goodsRemark(Integer id, Integer goodsId, String remarkKey, String remarkVal) {
        this.id = id;
        this.goodsId = goodsId;
        this.remarkKey = remarkKey;
        this.remarkVal = remarkVal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getRemarkKey() {
        return remarkKey;
    }

    public void setRemarkKey(String remarkKey) {
        this.remarkKey = remarkKey;
    }

    public String getRemarkVal() {
        return remarkVal;
    }

    public void setRemarkVal(String remarkVal) {
        this.remarkVal = remarkVal;
    }

    @Override
    public String toString() {
        return "goodsRemark{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", remarkKey='" + remarkKey + '\'' +
                ", remarkVal='" + remarkVal + '\'' +
                '}';
    }
}
