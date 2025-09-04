package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 病例
 *
 * @author 
 * @email
 */
@TableName("bingli")
public class BingliEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public BingliEntity() {

	}

	public BingliEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 患者
     */
    @TableField(value = "huanzhe_id")

    private Integer huanzheId;


    /**
     * 医生
     */
    @TableField(value = "yisheng_id")

    private Integer yishengId;


    /**
     * 病例编号
     */
    @TableField(value = "bingli_uuid_number")

    private String bingliUuidNumber;


    /**
     * 病例名称
     */
    @TableField(value = "bingli_name")

    private String bingliName;


    /**
     * 现住址
     */
    @TableField(value = "bingli_address")

    private String bingliAddress;


    /**
     * 籍贯
     */
    @TableField(value = "bingli_jiguan")

    private String bingliJiguan;


    /**
     * 婚姻状况
     */
    @TableField(value = "bingli_hunyin")

    private String bingliHunyin;


    /**
     * 主诉
     */
    @TableField(value = "zhusu_content")

    private String zhusuContent;


    /**
     * 既往史
     */
    @TableField(value = "jiwangshi_content")

    private String jiwangshiContent;


    /**
     * 个人史
     */
    @TableField(value = "geren_content")

    private String gerenContent;


    /**
     * 家族遗传史
     */
    @TableField(value = "yichuan_content")

    private String yichuanContent;


    /**
     * 医生诊断
     */
    @TableField(value = "zhenduan_content")

    private String zhenduanContent;


    /**
     * 开具药方
     */
    @TableField(value = "yaofang_content")

    private String yaofangContent;


    /**
     * 看病时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "kanbing_time")

    private Date kanbingTime;


    /**
     * 缴费金额
     */
    @TableField(value = "jiaofei_jine")

    private Double jiaofeiJine;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 是否缴费
     */
    @TableField(value = "jiaofei_types")

    private Integer jiaofeiTypes;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：病例编号
	 */
    public String getBingliUuidNumber() {
        return bingliUuidNumber;
    }
    /**
	 * 获取：病例编号
	 */

    public void setBingliUuidNumber(String bingliUuidNumber) {
        this.bingliUuidNumber = bingliUuidNumber;
    }
    /**
	 * 设置：病例名称
	 */
    public String getBingliName() {
        return bingliName;
    }
    /**
	 * 获取：病例名称
	 */

    public void setBingliName(String bingliName) {
        this.bingliName = bingliName;
    }
    /**
	 * 设置：现住址
	 */
    public String getBingliAddress() {
        return bingliAddress;
    }
    /**
	 * 获取：现住址
	 */

    public void setBingliAddress(String bingliAddress) {
        this.bingliAddress = bingliAddress;
    }
    /**
	 * 设置：籍贯
	 */
    public String getBingliJiguan() {
        return bingliJiguan;
    }
    /**
	 * 获取：籍贯
	 */

    public void setBingliJiguan(String bingliJiguan) {
        this.bingliJiguan = bingliJiguan;
    }
    /**
	 * 设置：婚姻状况
	 */
    public String getBingliHunyin() {
        return bingliHunyin;
    }
    /**
	 * 获取：婚姻状况
	 */

    public void setBingliHunyin(String bingliHunyin) {
        this.bingliHunyin = bingliHunyin;
    }
    /**
	 * 设置：主诉
	 */
    public String getZhusuContent() {
        return zhusuContent;
    }
    /**
	 * 获取：主诉
	 */

    public void setZhusuContent(String zhusuContent) {
        this.zhusuContent = zhusuContent;
    }
    /**
	 * 设置：既往史
	 */
    public String getJiwangshiContent() {
        return jiwangshiContent;
    }
    /**
	 * 获取：既往史
	 */

    public void setJiwangshiContent(String jiwangshiContent) {
        this.jiwangshiContent = jiwangshiContent;
    }
    /**
	 * 设置：个人史
	 */
    public String getGerenContent() {
        return gerenContent;
    }
    /**
	 * 获取：个人史
	 */

    public void setGerenContent(String gerenContent) {
        this.gerenContent = gerenContent;
    }
    /**
	 * 设置：家族遗传史
	 */
    public String getYichuanContent() {
        return yichuanContent;
    }
    /**
	 * 获取：家族遗传史
	 */

    public void setYichuanContent(String yichuanContent) {
        this.yichuanContent = yichuanContent;
    }
    /**
	 * 设置：医生诊断
	 */
    public String getZhenduanContent() {
        return zhenduanContent;
    }
    /**
	 * 获取：医生诊断
	 */

    public void setZhenduanContent(String zhenduanContent) {
        this.zhenduanContent = zhenduanContent;
    }
    /**
	 * 设置：开具药方
	 */
    public String getYaofangContent() {
        return yaofangContent;
    }
    /**
	 * 获取：开具药方
	 */

    public void setYaofangContent(String yaofangContent) {
        this.yaofangContent = yaofangContent;
    }
    /**
	 * 设置：看病时间
	 */
    public Date getKanbingTime() {
        return kanbingTime;
    }
    /**
	 * 获取：看病时间
	 */

    public void setKanbingTime(Date kanbingTime) {
        this.kanbingTime = kanbingTime;
    }
    /**
	 * 设置：缴费金额
	 */
    public Double getJiaofeiJine() {
        return jiaofeiJine;
    }
    /**
	 * 获取：缴费金额
	 */

    public void setJiaofeiJine(Double jiaofeiJine) {
        this.jiaofeiJine = jiaofeiJine;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：是否缴费
	 */
    public Integer getJiaofeiTypes() {
        return jiaofeiTypes;
    }
    /**
	 * 获取：是否缴费
	 */

    public void setJiaofeiTypes(Integer jiaofeiTypes) {
        this.jiaofeiTypes = jiaofeiTypes;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Bingli{" +
            "id=" + id +
            ", huanzheId=" + huanzheId +
            ", yishengId=" + yishengId +
            ", bingliUuidNumber=" + bingliUuidNumber +
            ", bingliName=" + bingliName +
            ", bingliAddress=" + bingliAddress +
            ", bingliJiguan=" + bingliJiguan +
            ", bingliHunyin=" + bingliHunyin +
            ", zhusuContent=" + zhusuContent +
            ", jiwangshiContent=" + jiwangshiContent +
            ", gerenContent=" + gerenContent +
            ", yichuanContent=" + yichuanContent +
            ", zhenduanContent=" + zhenduanContent +
            ", yaofangContent=" + yaofangContent +
            ", kanbingTime=" + kanbingTime +
            ", jiaofeiJine=" + jiaofeiJine +
            ", insertTime=" + insertTime +
            ", jiaofeiTypes=" + jiaofeiTypes +
            ", createTime=" + createTime +
        "}";
    }
}
