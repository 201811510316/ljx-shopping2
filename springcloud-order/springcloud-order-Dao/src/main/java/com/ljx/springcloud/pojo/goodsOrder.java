package com.ljx.springcloud.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 订单
 */
@Table(name="goods_order")
public class goodsOrder {

    //订单id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    //订单编号
    private String orderNo;

    //用户id
    private Integer userId;

    //总价格
    private Integer totalPrice;

    //订单支付状态 0-未支付 1-已支付
    private Integer payStatus;

    //用户地址
    private String userAddress;

    //用户电话
    private String telephone;

    //生成订单时间
    private Date createTime;

    //订单状态 0-为发货 1-已发货
    private Integer orderState;

    //发货时间
    private Date updateTime;

    //用户支付订单时间
    private Date payTime;

    public goodsOrder() {
    }

    public goodsOrder(Integer orderId, String orderNo, Integer userId, Integer totalPrice, Integer payStatus, String userAddress, String telephone, Date createTime, Integer orderState, Date updateTime, Date payTime) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.payStatus = payStatus;
        this.userAddress = userAddress;
        this.telephone = telephone;
        this.createTime = createTime;
        this.orderState = orderState;
        this.updateTime = updateTime;
        this.payTime = payTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "goodsOrder{" +
                "orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", payStatus=" + payStatus +
                ", userAddress='" + userAddress + '\'' +
                ", telephone='" + telephone + '\'' +
                ", createTime=" + createTime +
                ", orderState=" + orderState +
                ", updateTime=" + updateTime +
                ", payTime=" + payTime +
                '}';
    }
}
