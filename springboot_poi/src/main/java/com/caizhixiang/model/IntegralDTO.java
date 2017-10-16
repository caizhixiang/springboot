package com.caizhixiang.model;

import java.io.Serializable;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IntegralDTO implements Serializable,Comparable<IntegralDTO>{

	private String serialNum;
	private String orderNo;
	private String integral;
	private Date orderTime;
	private String orderStatus;
	private String payStatus;
	private String sendStatus;
	private String getAccount;
	private String goodsName;
	private String faceValue;
	private String calcPrice;
	private String quantity;
	
	@Override
	public int compareTo(IntegralDTO integralDTO) {
		String integral=integralDTO.getIntegral();
		String payStatus=integralDTO.getPayStatus();
		Date time = integralDTO.getOrderTime();
		if (integral.equals(this.integral)&&payStatus.equals(this.payStatus)
				&&time.compareTo(this.orderTime)==0) {
			return 0;
		}
		return 1;
	}
	
}
