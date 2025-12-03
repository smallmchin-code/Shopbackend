package DAO;

import java.sql.*;

import model.ProductImage;
import util.*;
import java.util.*;
public class ProductImageDAO {

	public ProductImageDAO() {}
	
	// 新增圖片
    public void insert(ProductImage img) {
        String sql = "INSERT INTO product_image (product_id, image_data, is_main) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

        	 stmt.setInt(1, img.getProductId());
        	 stmt.setBytes(2, img.getImageData());
             stmt.setBoolean(3, img.isMain());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查詢商品圖片
    public List<ProductImage> findByProductId(int productId) {
        String sql = "SELECT * FROM product_image WHERE product_id=?";
        List<ProductImage> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductImage img = new ProductImage();
         
                img.setId(rs.getInt("id"));
                img.setProductId(rs.getInt("product_id"));
                img.setImageData(rs.getBytes("image_data"));
                img.setMain(rs.getBoolean("is_main"));
                list.add(img);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public void deleteByProductId(int productId) {
        String sql = "DELETE FROM product_image WHERE product_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
