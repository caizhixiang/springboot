package springboot_poi.com.caizhixiang.poi;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 积分对账DTO
 * @author lhm
 *
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IntegralDTO implements Serializable,Comparable<IntegralDTO>{
	
	private static final long serialVersionUID = -4814731601957417198L;

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
	/** 数据来源1文件2系统 */
	private String dataFrom;
		
	/** 差异原因1file无记录2sys无记录3字段有差异 */
	private String diffReason;
	
	/** 对账批次号 */
	private String billCheckBatchNo;
	
	
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
