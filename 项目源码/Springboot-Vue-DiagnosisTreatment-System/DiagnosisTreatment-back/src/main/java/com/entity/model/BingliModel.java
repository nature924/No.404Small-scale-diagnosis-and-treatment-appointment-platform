package com.entity.model;

import com.entity.BingliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 病例
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class BingliModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 患者
     */
    private Integer huanzheId;


    /**
     * 医生
     */
    private Integer yishengId;


    /**
     * 病例编号
     */
    private String bingliUuidNumber;


    /**
     * 病例名称
     */
    private String bingliName;


    /**
     * 现住址
     */
    private String bingliAddress;


    /**
     * 籍贯
     */
    private String bingliJiguan;


    /**
     * 婚姻状况
     */
    private String bingliHunyin;


    /**
     * 主诉
     */
    private String zhusuContent;


    /**
     * 既往史
     */
    private String jiwangshiContent;


    /**
     * 个人史
     */
    private String gerenContent;


    /**
     * 家族遗传史
     */
    private String yichuanContent;


    /**
     * 医生诊断
     */
    private String zhenduanContent;


    /**
     * 开具药方
     */
    private String yaofangContent;


    /**
     * 看病时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date kanbingTime;


    /**
     * 缴费金额
     */
    private Double jiaofeiJine;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 是否缴费
     */
    private Integer jiaofeiTypes;


    /**
     * 创建时间
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
	 * 获取：病例编号
	 */
    public String getBingliUuidNumber() {
        return bingliUuidNumber;
    }


    /**
	 * 设置：病例编号
	 */
    public void setBingliUuidNumber(String bingliUuidNumber) {
        this.bingliUuidNumber = bingliUuidNumber;
    }
    /**
	 * 获取：病例名称
	 */
    public String getBingliName() {
        return bingliName;
    }


    /**
	 * 设置：病例名称
	 */
    public void setBingliName(String bingliName) {
        this.bingliName = bingliName;
    }
    /**
	 * 获取：现住址
	 */
    public String getBingliAddress() {
        return bingliAddress;
    }


    /**
	 * 设置：现住址
	 */
    public void setBingliAddress(String bingliAddress) {
        this.bingliAddress = bingliAddress;
    }
    /**
	 * 获取：籍贯
	 */
    public String getBingliJiguan() {
        return bingliJiguan;
    }


    /**
	 * 设置：籍贯
	 */
    public void setBingliJiguan(String bingliJiguan) {
        this.bingliJiguan = bingliJiguan;
    }
    /**
	 * 获取：婚姻状况
	 */
    public String getBingliHunyin() {
        return bingliHunyin;
    }


    /**
	 * 设置：婚姻状况
	 */
    public void setBingliHunyin(String bingliHunyin) {
        this.bingliHunyin = bingliHunyin;
    }
    /**
	 * 获取：主诉
	 */
    public String getZhusuContent() {
        return zhusuContent;
    }


    /**
	 * 设置：主诉
	 */
    public void setZhusuContent(String zhusuContent) {
        this.zhusuContent = zhusuContent;
    }
    /**
	 * 获取：既往史
	 */
    public String getJiwangshiContent() {
        return jiwangshiContent;
    }


    /**
	 * 设置：既往史
	 */
    public void setJiwangshiContent(String jiwangshiContent) {
        this.jiwangshiContent = jiwangshiContent;
    }
    /**
	 * 获取：个人史
	 */
    public String getGerenContent() {
        return gerenContent;
    }


    /**
	 * 设置：个人史
	 */
    public void setGerenContent(String gerenContent) {
        this.gerenContent = gerenContent;
    }
    /**
	 * 获取：家族遗传史
	 */
    public String getYichuanContent() {
        return yichuanContent;
    }


    /**
	 * 设置：家族遗传史
	 */
    public void setYichuanContent(String yichuanContent) {
        this.yichuanContent = yichuanContent;
    }
    /**
	 * 获取：医生诊断
	 */
    public String getZhenduanContent() {
        return zhenduanContent;
    }


    /**
	 * 设置：医生诊断
	 */
    public void setZhenduanContent(String zhenduanContent) {
        this.zhenduanContent = zhenduanContent;
    }
    /**
	 * 获取：开具药方
	 */
    public String getYaofangContent() {
        return yaofangContent;
    }


    /**
	 * 设置：开具药方
	 */
    public void setYaofangContent(String yaofangContent) {
        this.yaofangContent = yaofangContent;
    }
    /**
	 * 获取：看病时间
	 */
    public Date getKanbingTime() {
        return kanbingTime;
    }


    /**
	 * 设置：看病时间
	 */
    public void setKanbingTime(Date kanbingTime) {
        this.kanbingTime = kanbingTime;
    }
    /**
	 * 获取：缴费金额
	 */
    public Double getJiaofeiJine() {
        return jiaofeiJine;
    }


    /**
	 * 设置：缴费金额
	 */
    public void setJiaofeiJine(Double jiaofeiJine) {
        this.jiaofeiJine = jiaofeiJine;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：是否缴费
	 */
    public Integer getJiaofeiTypes() {
        return jiaofeiTypes;
    }


    /**
	 * 设置：是否缴费
	 */
    public void setJiaofeiTypes(Integer jiaofeiTypes) {
        this.jiaofeiTypes = jiaofeiTypes;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
