package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Product;
import model.User;
import util.DBUtil;

public class UserDAO {

	public UserDAO() {}
	
	//增加
	public int insert(User u) {
		String sql = "INSERT INTO user (username, password, email) VALUES ( ?, ?, ?)";
		try (Connection conn = DBUtil.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString( 1 , u.getUsername());
			stmt.setString( 2 , u.getPassword());
			stmt.setString( 3 , u.getEmail());
			
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
	public boolean update( User u) {
		String sql = "UPDATE user SET username=?, password=?, email=? WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getEmail());
            stmt.setInt(4, u.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
	//刪除
	public boolean delete( User u ) {
		String sql = "DELETE FROM user WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, u.getId());
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
	//查詢
	public User findById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        User user = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

}
