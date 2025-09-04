package com.entity.view;

import com.entity.BingliEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 病例
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("bingli")
public class BingliView extends BingliEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 是否缴费的值
		*/
		private String jiaofeiValue;



		//级联表 huanzhe
			/**
			* 患者姓名
			*/
			private String huanzheName;
			/**
			* 患者手机号
			*/
			private String huanzhePhone;
			/**
			* 患者身份证号
			*/
			private String huanzheIdNumber;
			/**
			* 患者头像
			*/
			private String huanzhePhoto;
			/**
			* 电子邮箱
			*/
			private String huanzheEmail;
			/**
			* 余额
			*/
			private Double newMoney;

		//级联表 yisheng
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
			* 科室
			*/
			private Integer keshiTypes;
				/**
				* 科室的值
				*/
				private String keshiValue;
			/**
			* 职位
			*/
			private Integer zhiweiTypes;
				/**
				* 职位的值
				*/
				private String zhiweiValue;
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

	public BingliView() {

	}

	public BingliView(BingliEntity bingliEntity) {
		try {
			BeanUtils.copyProperties(this, bingliEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 是否缴费的值
			*/
			public String getJiaofeiValue() {
				return jiaofeiValue;
			}
			/**
			* 设置： 是否缴费的值
			*/
			public void setJiaofeiValue(String jiaofeiValue) {
				this.jiaofeiValue = jiaofeiValue;
			}













				//级联表的get和set huanzhe

					/**
					* 获取： 患者姓名
					*/
					public String getHuanzheName() {
						return huanzheName;
					}
					/**
					* 设置： 患者姓名
					*/
					public void setHuanzheName(String huanzheName) {
						this.huanzheName = huanzheName;
					}

					/**
					* 获取： 患者手机号
					*/
					public String getHuanzhePhone() {
						return huanzhePhone;
					}
					/**
					* 设置： 患者手机号
					*/
					public void setHuanzhePhone(String huanzhePhone) {
						this.huanzhePhone = huanzhePhone;
					}

					/**
					* 获取： 患者身份证号
					*/
					public String getHuanzheIdNumber() {
						return huanzheIdNumber;
					}
					/**
					* 设置： 患者身份证号
					*/
					public void setHuanzheIdNumber(String huanzheIdNumber) {
						this.huanzheIdNumber = huanzheIdNumber;
					}

					/**
					* 获取： 患者头像
					*/
					public String getHuanzhePhoto() {
						return huanzhePhoto;
					}
					/**
					* 设置： 患者头像
					*/
					public void setHuanzhePhoto(String huanzhePhoto) {
						this.huanzhePhoto = huanzhePhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getHuanzheEmail() {
						return huanzheEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setHuanzheEmail(String huanzheEmail) {
						this.huanzheEmail = huanzheEmail;
					}

					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}










				//级联表的get和set yisheng

					/**
					* 获取： 医生工号
					*/
					public String getYishengUuidNumber() {
						return yishengUuidNumber;
					}
					/**
					* 设置： 医生工号
					*/
					public void setYishengUuidNumber(String yishengUuidNumber) {
						this.yishengUuidNumber = yishengUuidNumber;
					}

					/**
					* 获取： 医生姓名
					*/
					public String getYishengName() {
						return yishengName;
					}
					/**
					* 设置： 医生姓名
					*/
					public void setYishengName(String yishengName) {
						this.yishengName = yishengName;
					}

					/**
					* 获取： 医生手机号
					*/
					public String getYishengPhone() {
						return yishengPhone;
					}
					/**
					* 设置： 医生手机号
					*/
					public void setYishengPhone(String yishengPhone) {
						this.yishengPhone = yishengPhone;
					}

					/**
					* 获取： 医生身份证号
					*/
					public String getYishengIdNumber() {
						return yishengIdNumber;
					}
					/**
					* 设置： 医生身份证号
					*/
					public void setYishengIdNumber(String yishengIdNumber) {
						this.yishengIdNumber = yishengIdNumber;
					}

					/**
					* 获取： 医生头像
					*/
					public String getYishengPhoto() {
						return yishengPhoto;
					}
					/**
					* 设置： 医生头像
					*/
					public void setYishengPhoto(String yishengPhoto) {
						this.yishengPhoto = yishengPhoto;
					}

					/**
					* 获取： 医生擅长
					*/
					public String getYishengShanchang() {
						return yishengShanchang;
					}
					/**
					* 设置： 医生擅长
					*/
					public void setYishengShanchang(String yishengShanchang) {
						this.yishengShanchang = yishengShanchang;
					}

					/**
					* 获取： 科室
					*/
					public Integer getKeshiTypes() {
						return keshiTypes;
					}
					/**
					* 设置： 科室
					*/
					public void setKeshiTypes(Integer keshiTypes) {
						this.keshiTypes = keshiTypes;
					}


						/**
						* 获取： 科室的值
						*/
						public String getKeshiValue() {
							return keshiValue;
						}
						/**
						* 设置： 科室的值
						*/
						public void setKeshiValue(String keshiValue) {
							this.keshiValue = keshiValue;
						}

					/**
					* 获取： 职位
					*/
					public Integer getZhiweiTypes() {
						return zhiweiTypes;
					}
					/**
					* 设置： 职位
					*/
					public void setZhiweiTypes(Integer zhiweiTypes) {
						this.zhiweiTypes = zhiweiTypes;
					}


						/**
						* 获取： 职位的值
						*/
						public String getZhiweiValue() {
							return zhiweiValue;
						}
						/**
						* 设置： 职位的值
						*/
						public void setZhiweiValue(String zhiweiValue) {
							this.zhiweiValue = zhiweiValue;
						}

					/**
					* 获取： 挂号费
					*/
					public Double getGuahaofei() {
						return guahaofei;
					}
					/**
					* 设置： 挂号费
					*/
					public void setGuahaofei(Double guahaofei) {
						this.guahaofei = guahaofei;
					}

					/**
					* 获取： 赞
					*/
					public Integer getZanNumber() {
						return zanNumber;
					}
					/**
					* 设置： 赞
					*/
					public void setZanNumber(Integer zanNumber) {
						this.zanNumber = zanNumber;
					}

					/**
					* 获取： 踩
					*/
					public Integer getCaiNumber() {
						return caiNumber;
					}
					/**
					* 设置： 踩
					*/
					public void setCaiNumber(Integer caiNumber) {
						this.caiNumber = caiNumber;
					}

					/**
					* 获取： 医生详细介绍
					*/
					public String getYishengContent() {
						return yishengContent;
					}
					/**
					* 设置： 医生详细介绍
					*/
					public void setYishengContent(String yishengContent) {
						this.yishengContent = yishengContent;
					}












}
