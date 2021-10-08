package personal_project_jsp.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
		user.setSalt(rs.getString("salt"));
		user.setOriginPass(rs.getString("origin_pass"));
		user.setEmail(rs.getString("email"));
		user.setRegDate(rs.getDate("reg_date"));
		user.setWithdrawDate(rs.getDate("withdraw_date"));
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
		String sql = "select * from user where id = ?";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			
			pstmt.setString(1, user.getId());
			
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					return getUser(rs);
				}
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
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
	public int withdrawUser(User user) {
		String sql = "update user set withdraw_date = ?  where id = ?";

		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){

			pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
			pstmt.setString(2, user.getId());

			return pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int insertUser(User user) {
		String sql = "insert into user(id, name, password, salt, origin_pass, email) values (?, ?, ?, ?, ?, ?)";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getSalt());
			pstmt.setString(5, user.getOriginPass());
			pstmt.setString(6, user.getEmail());

			return pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
				
	}

	@Override
	public int updateUser(User user) {
		String sql = "update user set password = ?, origin_pass = ?, email = ?, root_permission = ?  where id = ?";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getOriginPass());
			pstmt.setString(3, user.getEmail());
			pstmt.setInt(4, user.getRootPermission());
			pstmt.setString(5, user.getId());
			
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
