package model;

public class ProductImage {

	public ProductImage() {}
	
	private int id;
	private byte[] imageData;
	private int productId;
	private boolean isMain;
	
	
	public ProductImage(int id, byte[] imageData, int productId, boolean isMain) {
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
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
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
