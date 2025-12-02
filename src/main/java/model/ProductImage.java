package model;

public class ProductImage {

	public ProductImage() {}
	
	private int id;
	private long imageData;
	private int productId;
	private boolean isMain;
	
	
	public ProductImage(int id, long imageData, int productId, boolean isMain) {
		this.id = id;
		this.imageData = imageData;
		this.productId = productId;
		this.isMain = isMain;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getImageData() {
		return imageData;
	}
	public void setImageData(long imageData) {
		this.imageData = imageData;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public boolean isMain() {
		return isMain;
	}
	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}
	

}
