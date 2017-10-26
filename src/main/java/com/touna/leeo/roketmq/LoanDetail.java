package com.touna.leeo.roketmq;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 成功放款数据表
 * </p>
 *
 * @author Rain
 * @since 2017-09-04
 */
public class LoanDetail{

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
     * 客户Id,参照CRM里面的客户Id
     */
	private String customerId;
    /**
     * 订单ID
     */
	private String orderId;
    /**
     * 类型
     */
	private String type;
    /**
     * 放款金额
     */
	private Double amount;
    /**
     * 操作时间(放款时间)
     */
	private Date operateDate;
    /**
     * 备注
     */
	private String remark;
    /**
     * 记录插入时间
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	@Override
	public String toString() {
		return "LoanDetail{" +
			"id=" + id +
			", uuid=" + uuid +
			", customerId=" + customerId +
			", orderId=" + orderId +
			", type=" + type +
			", amount=" + amount +
			", operateDate=" + operateDate +
			", remark=" + remark +
			", insertTime=" + insertTime +
			"}";
	}
}
