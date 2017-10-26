package com.touna.leeo.roketmq;

import java.util.Date;

/**
 * <p>
 * 授信明细表
 * </p>
 *
 * @author Leeo
 * @since 2017-08-31
 */
public class CreditDetail{

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
	private String uuid;
    /**
     * 授信编码
     */
	private String code;
    /**
     * 决策结果,(0:人工审核;1:自动通过;2:自动拒绝)
     */
	private Integer resultStatus;
    /**
     * 客户ID
     */
	private String custId;
    /**
     * 客户姓名
     */
	private String custName;
    /**
     * 客户身份证号
     */
	private String custIdCard;
    /**
     * 客户手机号
     */
	private String mobile;
    /**
     * 经度
     */
	private String longitude;
    /**
     * 纬度
     */
	private String latitude;
    /**
     * 所在省份
     */
	private String province;
    /**
     * 所在市
     */
	private String city;
    /**
     * 所在地区
     */
	private String area;
    /**
     * 所在具体街道
     */
	private String address;
    /**
     * 授信提交时间
     */
	private Date createTime;
    /**
     * 授信出额时间
     */
	private Date decisionTime;
    /**
     * 授信额度
     */
	private Double creditBalance;

	/**
	 *审核结果(0:不通过;1通过)
	 */
	private Integer auditResult;
	
	/**
	 *记录插入时间
	 */
	private Date insertTime;
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustIdCard() {
		return custIdCard;
	}

	public void setCustIdCard(String custIdCard) {
		this.custIdCard = custIdCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDecisionTime() {
		return decisionTime;
	}

	public void setDecisionTime(Date decisionTime) {
		this.decisionTime = decisionTime;
	}

	public Double getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(Double creditBalance) {
		this.creditBalance = creditBalance;
	}
	
	

	public Integer getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@Override
	public String toString() {
		return "CreditDetail{" +
			"uuid=" + uuid +
			", code=" + code +
			", resultStatus=" + resultStatus +
			", custId=" + custId +
			", custName=" + custName +
			", custIdCard=" + custIdCard +
			", mobile=" + mobile +
			", longitude=" + longitude +
			", latitude=" + latitude +
			", province=" + province +
			", city=" + city +
			", area=" + area +
			", address=" + address +
			", createTime=" + createTime +
			", decisionTime=" + decisionTime +
			", creditBalance=" + creditBalance +
			"}";
	}
}
