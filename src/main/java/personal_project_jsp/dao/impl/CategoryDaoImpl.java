package personal_project_jsp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import personal_project_jsp.dao.CategoryDao;
import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Category;
import personal_project_jsp.util.JdbcUtil;

public class CategoryDaoImpl implements CategoryDao {
	
	private static final CategoryDaoImpl instance = new CategoryDaoImpl();
	public static CategoryDaoImpl getInstance() {return instance;}
	private CategoryDaoImpl() {}

	private Category getCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		
		category.setCategory(rs.getString("category"));
		
		return category;
	}
	
	
	@Override
	public ArrayList<Category> selectCategoryByAll() {
		String sql = "select `category` from category";
		ArrayList<Category> categoryArr = null;
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			
			if(rs.next()) {
				categoryArr = new ArrayList<>();
				do {
					categoryArr.add(getCategory(rs));
				} while(rs.next());
				
				return categoryArr;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public Category selectTitleByNo(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertCategory(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCategory(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCategory(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}

}
