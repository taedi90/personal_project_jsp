package personal_project_jsp.dao;

import java.util.ArrayList;

import personal_project_jsp.dto.Category;


public interface CategoryDao {
	
	ArrayList<Category> selectCategoryByAll();
	
	Category selectTitleByNo(Category category);
	
	int insertCategory(Category category);
	int updateCategory(Category category);
	int deleteCategory(Category category);
	
}
