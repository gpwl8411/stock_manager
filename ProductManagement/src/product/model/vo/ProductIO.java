package product.model.vo;

import java.sql.Date;

/**
 * 
 * * IO_NO NUMBER PRIMARY KEY => sequence처리할 것.
* PRODUCT_ID VARCHAR2(30) => PRODUCT_STOCK테이블 PRODUCT_ID 참조
* IODATE DATE DEFAULT SYSDATE
* AMOUNT NUMBER
* STATUS CHAR(1) CHECK (STATUS IN ('I', 'O'))
 *
 */
public class ProductIO extends Product{
	private int inNo;
	private String productId;
	private Date ioDate;
	private int amount;
	private String status;
	public ProductIO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductIO(int inNo, String productId, Date ioDate, int amount, String status) {
		super();
		this.inNo = inNo;
		this.productId = productId;
		this.ioDate = ioDate;
		this.amount = amount;
		this.status = status;
	}
	public int getInNo() {
		return inNo;
	}
	public void setInNo(int inNo) {
		this.inNo = inNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Date getIoDate() {
		return ioDate;
	}
	public void setIoDate(Date ioDate) {
		this.ioDate = ioDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ProductIO [inNo=" + inNo + ", productId=" + productId + ", ioDate=" + ioDate + ", amount=" + amount
				+ ", status=" + status + "]";
	}
	
}
