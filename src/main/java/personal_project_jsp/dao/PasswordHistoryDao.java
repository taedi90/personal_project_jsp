package personal_project_jsp.dao;

import personal_project_jsp.dto.User;

public interface PasswordHistoryDao {
    boolean isUsed(User user);
}
