package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.OrderItem;
import util.DBUtil;

public class OrderItemDAO {

	public OrderItemDAO() {}
	
	//增加
			public int insert(OrderItem oi) {
				String sql = "INSERT INTO order_items ( id , order_id, product_id, price , quantity) VALUES ( ?,? , ?, ?, ?)";
				try (Connection conn = DBUtil.getConnection();
			             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
					stmt.setInt( 1 , oi.getId());
					stmt.setInt(2 , oi.getOrderId());
					stmt.setInt(3, oi.getProductId());
					stmt.setDouble(4 , oi.getPrice());
					stmt.setInt( 5, oi.getQuantity());
					
					ResultSet rs = stmt.getGeneratedKeys();
		            if (rs.next()) {
		                return rs.getInt(1);
		            }
				} catch (Exception e) {
		            e.printStackTrace();
		        }
		        return -1;
			}
			//更新
			public boolean update( OrderItem oi) {
				String sql = "UPDATE order_items SET order_id=?, product_id=?, price=? , quantity=? WHERE id=?";

		        try (Connection conn = DBUtil.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(sql)) {

					stmt.setInt(1 , oi.getOrderId());
					stmt.setInt(2, oi.getProductId());
					stmt.setDouble(3, oi.getPrice());
					stmt.setInt( 4, oi.getQuantity());
					stmt.setInt( 5 , oi.getId());

		            return stmt.executeUpdate() > 0;

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return false;
			}
			//刪除
			public boolean delete( OrderItem o ) {
				String sql = "DELETE FROM order_items WHERE id=?";

		        try (Connection conn = DBUtil.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(sql)) {

		            stmt.setInt(1, o.getId());
		            return stmt.executeUpdate() > 0;

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return false;
			}
			//查詢
			public OrderItem findById(int id) {
		        String sql = "SELECT * FROM order_items WHERE id = ?";
		        OrderItem orderItem = null;

		        try (Connection conn = DBUtil.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(sql)) {

		            stmt.setInt(1, id);
		            ResultSet rs = stmt.executeQuery();

		            if (rs.next()) {
		                orderItem = new OrderItem(
		                    rs.getInt("id"),
		                    rs.getInt("order_id"),
		                    rs.getInt("product_id"),
		                    rs.getInt("quantity"),
		                    rs.getDouble("price")
		                );
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }

		        return orderItem;
		    }

}
