package DAO;

import java.sql.*;
import java.util.*;

import model.ProductVariant;
import util.DBUtil;

public class ProductVariantDAO {

	public ProductVariantDAO() {}
	
	public void insert(ProductVariant pv) {
        String sql = "INSERT INTO product_variant (product_id, size, stock) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pv.getProductId());
            stmt.setString(2, pv.getSize());
            stmt.setInt(3, pv.getStock());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查詢某商品所有尺寸
    public List<ProductVariant> findByProductId(int productId) {
        String sql = "SELECT * FROM product_variant WHERE product_id = ?";
        List<ProductVariant> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new ProductVariant(
                    rs.getInt("id"),
                    rs.getInt("product_id"),
                    rs.getString("size"),
                    rs.getInt("stock")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public void deleteByProductId(int productId) {
        String sql = "DELETE FROM product_variant WHERE product_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
