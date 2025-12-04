package DAO;

import java.sql.*;
import java.util.*;

import model.ProductImage;
import model.ProductVariant;
import util.DBUtil;

public class ProductVariantDAO {

	public ProductVariantDAO() {}
	//新增
	public void insert(ProductVariant pv) {
        String sql = "INSERT INTO product_variant (product_id, size, stock) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)) {

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
    
    // 更新商品圖片
    public boolean update(ProductVariant pv) {
        String sql = "UPDATE product_variant id=?, size=?, stock=? WHERE product_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setInt(1, pv.getId());
        	stmt.setString(2, pv.getSize());
            stmt.setInt(3, pv.getStock());
            stmt.setInt(4, pv.getProductId());
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //刪除
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
