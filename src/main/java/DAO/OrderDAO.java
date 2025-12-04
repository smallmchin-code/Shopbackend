package DAO;

import java.sql.*;

import model.*;
import util.DBUtil;

public class OrderDAO {

	public OrderDAO() {}
	//增加
		public int insert(Order o) {
			String sql = "INSERT INTO orders ( user_id,  status, total_amount) VALUES ( ?, ?, ?)";
			try (Connection conn = DBUtil.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setInt( 1 , o.getUserId());
				stmt.setString( 2 , o.getStatus());
				stmt.setDouble(3 , o.getTotalAmount());
				
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
		public boolean update( Order o) {
			String sql = "UPDATE orders SET user_id=?,  status=?, total_amount=?, WHERE id=?";

	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setInt(1, o.getUserId());
	            stmt.setString( 2 , o.getStatus());
				stmt.setDouble(3 , o.getTotalAmount());
	            stmt.setInt(4, o.getId());

	            return stmt.executeUpdate() > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
		}
		//刪除
		public boolean delete( Order o ) {
			String sql = "DELETE FROM orders WHERE id=?";

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
		public Order findById(int id) {
	        String sql = "SELECT * FROM orders WHERE id = ?";
	        Order order = null;

	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setInt(1, id);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                order = new Order(
	                    rs.getInt("id"),
	                    rs.getInt("user_id"),
	                    rs.getString("status"),
	                    rs.getDouble("total_amount")
	                );
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return order;
	    }

    

}
