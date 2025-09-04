package com.entity.model;

import com.entity.YishengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 医生
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YishengModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 医生工号
     */
    private String yishengUuidNumber;


    /**
     * 医生姓名
     */
    private String yishengName;


    /**
     * 医生手机号
     */
    private String yishengPhone;


    /**
     * 医生身份证号
     */
    private String yishengIdNumber;


    /**
     * 医生头像
     */
    private String yishengPhoto;


    /**
     * 医生擅长
     */
    private String yishengShanchang;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 科室
     */
    private Integer keshiTypes;


    /**
     * 职位
     */
    private Integer zhiweiTypes;


    /**
     * 挂号费
     */
    private Double guahaofei;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 医生详细介绍
     */
    private String yishengContent;


    /**
     * 创建时间 show2 photoShow
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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：医生工号
	 */
    public String getYishengUuidNumber() {
        return yishengUuidNumber;
    }


    /**
	 * 设置：医生工号
	 */
    public void setYishengUuidNumber(String yishengUuidNumber) {
        this.yishengUuidNumber = yishengUuidNumber;
    }
    /**
	 * 获取：医生姓名
	 */
    public String getYishengName() {
        return yishengName;
    }


    /**
	 * 设置：医生姓名
	 */
    public void setYishengName(String yishengName) {
        this.yishengName = yishengName;
    }
    /**
	 * 获取：医生手机号
	 */
    public String getYishengPhone() {
        return yishengPhone;
    }


    /**
	 * 设置：医生手机号
	 */
    public void setYishengPhone(String yishengPhone) {
        this.yishengPhone = yishengPhone;
    }
    /**
	 * 获取：医生身份证号
	 */
    public String getYishengIdNumber() {
        return yishengIdNumber;
    }


    /**
	 * 设置：医生身份证号
	 */
    public void setYishengIdNumber(String yishengIdNumber) {
        this.yishengIdNumber = yishengIdNumber;
    }
    /**
	 * 获取：医生头像
	 */
    public String getYishengPhoto() {
        return yishengPhoto;
    }


    /**
	 * 设置：医生头像
	 */
    public void setYishengPhoto(String yishengPhoto) {
        this.yishengPhoto = yishengPhoto;
    }
    /**
	 * 获取：医生擅长
	 */
    public String getYishengShanchang() {
        return yishengShanchang;
    }


    /**
	 * 设置：医生擅长
	 */
    public void setYishengShanchang(String yishengShanchang) {
        this.yishengShanchang = yishengShanchang;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：科室
	 */
    public Integer getKeshiTypes() {
        return keshiTypes;
    }


    /**
	 * 设置：科室
	 */
    public void setKeshiTypes(Integer keshiTypes) {
        this.keshiTypes = keshiTypes;
    }
    /**
	 * 获取：职位
	 */
    public Integer getZhiweiTypes() {
        return zhiweiTypes;
    }


    /**
	 * 设置：职位
	 */
    public void setZhiweiTypes(Integer zhiweiTypes) {
        this.zhiweiTypes = zhiweiTypes;
    }
    /**
	 * 获取：挂号费
	 */
    public Double getGuahaofei() {
        return guahaofei;
    }


    /**
	 * 设置：挂号费
	 */
    public void setGuahaofei(Double guahaofei) {
        this.guahaofei = guahaofei;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 设置：赞
	 */
    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 设置：踩
	 */
    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：医生详细介绍
	 */
    public String getYishengContent() {
        return yishengContent;
    }


    /**
	 * 设置：医生详细介绍
	 */
    public void setYishengContent(String yishengContent) {
        this.yishengContent = yishengContent;
    }
    /**
	 * 获取：创建时间 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
