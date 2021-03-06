package personal_project_jsp.dao;

import java.util.ArrayList;
import java.util.Map;

import personal_project_jsp.dto.User;

public interface UserDao {
//insert, update, selectall, delete
	
	ArrayList<User> selectUserByAll();
	
	User getUserInfo(User user);
	
	Map loginChk(User user);

	int withdrawUser(User user);

	int idChk(User user);
	int insertUser(User user);
	int updateUser(User user);
	int deleteUser(User user);
	
}
