package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	//刪除
	//查詢
}
