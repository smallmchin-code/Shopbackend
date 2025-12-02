package DAO;

import java.sql.*;
import util.*;
import model.Product;

public class ProductDAO {

	public ProductDAO() {}
	
	public int insert(Product p) {
        String sql = "INSERT INTO product (name, description, price, category) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setDouble(3, p.getPrice());
            stmt.setString(4, p.getCategory());

            stmt.executeUpdate();

            // 回傳自動產生的 id
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // 查詢單一商品（不含 variant / image）
    public Product findById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        Product product = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("description"),
                    rs.getString("category")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    // 查詢商品 + 尺寸變體 + 圖片（完整）
   
    // 更新商品
    public boolean update(Product p) {
        String sql = "UPDATE product SET name=?, description=?, price=?, category_id=? WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setDouble(3, p.getPrice());
            stmt.setString(4, p.getCategory());
            stmt.setInt(5, p.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 刪除商品
    public boolean delete(int id) {
        String sql = "DELETE FROM product WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
