package com.touna.leeo.roketmq;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 注册明细表
 * </p>
 *
 * @author Rain
 * @since 2017-09-04
 */
public class RegDetail {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
	private Integer id;
    /**
     * 消息唯一ID
     */
	private String uuid;
    /**
     * 注册状态码,200表示成功
     */
	private String regCode;
    /**
     * 注册手机号
     */
	private String regMobileNum;
    /**
     * 注册时间戳
     */
	private Date regTime;
    /**
     * 注册经度
     */
	private String longitude;
    /**
     * 注册纬度
     */
	private String latitude;
    /**
     * 省
     */
	private String province;
    /**
     * 市
     */
	private String city;
    /**
     * 区
     */
	private String area;
    /**
     * 详细地址
     */
	private String address;
    /**
     * 记录录入时间
     */
	private Date insertTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getRegMobileNum() {
		return regMobileNum;
	}

	public void setRegMobileNum(String regMobileNum) {
		this.regMobileNum = regMobileNum;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@Override
	public String toString() {
		return "RegDetail{" +
			"id=" + id +
			", uuid=" + uuid +
			", regCode=" + regCode +
			", regMobileNum=" + regMobileNum +
			", regTime=" + regTime +
			", longitude=" + longitude +
			", latitude=" + latitude +
			", province=" + province +
			", city=" + city +
			", area=" + area +
			", address=" + address +
			", insertTime=" + insertTime +
			"}";
	}
}
