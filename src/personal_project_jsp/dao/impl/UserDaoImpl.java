package personal_project_jsp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import personal_project_jsp.dao.UserDao;
import personal_project_jsp.dto.User;
import personal_project_jsp.util.JdbcUtil;

public class UserDaoImpl implements UserDao {
	
	private static final UserDaoImpl instance = new UserDaoImpl();
	public static UserDaoImpl getInstance() {return instance;}
	private UserDaoImpl() {}
	
	private User getUser(ResultSet rs) throws SQLException {
		User user = new User();
		
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setRegDate(rs.getDate("reg_date"));
		user.setRootPermission(rs.getInt("root_permission"));
		
		return user;
	}

	@Override
	public ArrayList<User> selectUserByAll() {
		String sql = "select id, name, password, email, reg_date, root_permission from user";
		ArrayList<User> userArr = null;
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			
			if(rs.next()) {
				userArr = new ArrayList<>();
				do {
					userArr.add(getUser(rs));
				} while(rs.next());
				
				return userArr;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public User getUserInfo(User user) {
		String sql = "select count(*), name, email, reg_date, root_permission from user where id = ?";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			
			pstmt.setString(1, user.getId());
			
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {		
					user.setEmail(rs.getString("email"));
					user.setName(rs.getString("name"));
					user.setRegDate(rs.getDate("reg_date"));
					user.setRootPermission(rs.getInt("root_permission"));
					return user;
				}
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public Map loginChk(User user) {
		String sql = "select count(*), name from user where id = ? and password = ?";
		Map<String, Object> map = new HashMap<>();
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {					
					map.put("res", rs.getInt(1));
					map.put("name", rs.getString("name"));
					return map;
				}
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		map.put("res", 0);
		return map;
	}




	@Override
	public int insertUser(User user) {
		String sql = "insert into user(id, name, password, email) values (?, ?, ?, ?)";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getEmail());
			
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
				
	}

	@Override
	public int updateUser(User user) {
		String sql = "update user set password = ?, email = ?, root_permission = ?  where id = ?";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setInt(3, user.getRootPermission());
			pstmt.setString(4, user.getId());
			
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
				
	}

	@Override
	public int deleteUser(User user) {
		String sql = "delete from user where id = ?";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			
			pstmt.setString(1, user.getId());
			
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
				
	}
	@Override
	public int idChk(User user) {
		String sql = "select count(*) from user where id = ?";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			
			pstmt.setString(1, user.getId());
			
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					return rs.getInt(1);
				}
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


}
