package com.entity.vo;

import com.entity.YishengOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 医生预约挂号订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yisheng_order")
public class YishengOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 预约挂号编号
     */

    @TableField(value = "yisheng_order_uuid_number")
    private String yishengOrderUuidNumber;


    /**
     * 医生
     */

    @TableField(value = "yisheng_id")
    private Integer yishengId;


    /**
     * 患者
     */

    @TableField(value = "huanzhe_id")
    private Integer huanzheId;


    /**
     * 实付价格
     */

    @TableField(value = "yisheng_order_true_price")
    private Double yishengOrderTruePrice;


    /**
     * 挂号状态
     */

    @TableField(value = "yisheng_order_types")
    private Integer yishengOrderTypes;


    /**
     * 预约看病日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "kanbing_time")
    private Date kanbingTime;


    /**
     * 看病时间段
     */

    @TableField(value = "shijianduan_types")
    private Integer shijianduanTypes;


    /**
     * 支付类型
     */

    @TableField(value = "yisheng_order_payment_types")
    private Integer yishengOrderPaymentTypes;


    /**
     * 挂号时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：预约挂号编号
	 */
    public String getYishengOrderUuidNumber() {
        return yishengOrderUuidNumber;
    }


    /**
	 * 获取：预约挂号编号
	 */

    public void setYishengOrderUuidNumber(String yishengOrderUuidNumber) {
        this.yishengOrderUuidNumber = yishengOrderUuidNumber;
    }
    /**
	 * 设置：医生
	 */
    public Integer getYishengId() {
        return yishengId;
    }


    /**
	 * 获取：医生
	 */

    public void setYishengId(Integer yishengId) {
        this.yishengId = yishengId;
    }
    /**
	 * 设置：患者
	 */
    public Integer getHuanzheId() {
        return huanzheId;
    }


    /**
	 * 获取：患者
	 */

    public void setHuanzheId(Integer huanzheId) {
        this.huanzheId = huanzheId;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getYishengOrderTruePrice() {
        return yishengOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setYishengOrderTruePrice(Double yishengOrderTruePrice) {
        this.yishengOrderTruePrice = yishengOrderTruePrice;
    }
    /**
	 * 设置：挂号状态
	 */
    public Integer getYishengOrderTypes() {
        return yishengOrderTypes;
    }


    /**
	 * 获取：挂号状态
	 */

    public void setYishengOrderTypes(Integer yishengOrderTypes) {
        this.yishengOrderTypes = yishengOrderTypes;
    }
    /**
	 * 设置：预约看病日期
	 */
    public Date getKanbingTime() {
        return kanbingTime;
    }


    /**
	 * 获取：预约看病日期
	 */

    public void setKanbingTime(Date kanbingTime) {
        this.kanbingTime = kanbingTime;
    }
    /**
	 * 设置：看病时间段
	 */
    public Integer getShijianduanTypes() {
        return shijianduanTypes;
    }


    /**
	 * 获取：看病时间段
	 */

    public void setShijianduanTypes(Integer shijianduanTypes) {
        this.shijianduanTypes = shijianduanTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getYishengOrderPaymentTypes() {
        return yishengOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setYishengOrderPaymentTypes(Integer yishengOrderPaymentTypes) {
        this.yishengOrderPaymentTypes = yishengOrderPaymentTypes;
    }
    /**
	 * 设置：挂号时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：挂号时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
