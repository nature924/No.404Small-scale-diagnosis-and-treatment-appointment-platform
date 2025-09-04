package com.entity.model;

import com.entity.YishengOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 医生预约挂号订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YishengOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 预约挂号编号
     */
    private String yishengOrderUuidNumber;


    /**
     * 医生
     */
    private Integer yishengId;


    /**
     * 患者
     */
    private Integer huanzheId;


    /**
     * 实付价格
     */
    private Double yishengOrderTruePrice;


    /**
     * 挂号状态
     */
    private Integer yishengOrderTypes;


    /**
     * 预约看病日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date kanbingTime;


    /**
     * 看病时间段
     */
    private Integer shijianduanTypes;


    /**
     * 支付类型
     */
    private Integer yishengOrderPaymentTypes;


    /**
     * 挂号时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：预约挂号编号
	 */
    public String getYishengOrderUuidNumber() {
        return yishengOrderUuidNumber;
    }


    /**
	 * 设置：预约挂号编号
	 */
    public void setYishengOrderUuidNumber(String yishengOrderUuidNumber) {
        this.yishengOrderUuidNumber = yishengOrderUuidNumber;
    }
    /**
	 * 获取：医生
	 */
    public Integer getYishengId() {
        return yishengId;
    }


    /**
	 * 设置：医生
	 */
    public void setYishengId(Integer yishengId) {
        this.yishengId = yishengId;
    }
    /**
	 * 获取：患者
	 */
    public Integer getHuanzheId() {
        return huanzheId;
    }


    /**
	 * 设置：患者
	 */
    public void setHuanzheId(Integer huanzheId) {
        this.huanzheId = huanzheId;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getYishengOrderTruePrice() {
        return yishengOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setYishengOrderTruePrice(Double yishengOrderTruePrice) {
        this.yishengOrderTruePrice = yishengOrderTruePrice;
    }
    /**
	 * 获取：挂号状态
	 */
    public Integer getYishengOrderTypes() {
        return yishengOrderTypes;
    }


    /**
	 * 设置：挂号状态
	 */
    public void setYishengOrderTypes(Integer yishengOrderTypes) {
        this.yishengOrderTypes = yishengOrderTypes;
    }
    /**
	 * 获取：预约看病日期
	 */
    public Date getKanbingTime() {
        return kanbingTime;
    }


    /**
	 * 设置：预约看病日期
	 */
    public void setKanbingTime(Date kanbingTime) {
        this.kanbingTime = kanbingTime;
    }
    /**
	 * 获取：看病时间段
	 */
    public Integer getShijianduanTypes() {
        return shijianduanTypes;
    }


    /**
	 * 设置：看病时间段
	 */
    public void setShijianduanTypes(Integer shijianduanTypes) {
        this.shijianduanTypes = shijianduanTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getYishengOrderPaymentTypes() {
        return yishengOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setYishengOrderPaymentTypes(Integer yishengOrderPaymentTypes) {
        this.yishengOrderPaymentTypes = yishengOrderPaymentTypes;
    }
    /**
	 * 获取：挂号时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：挂号时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
