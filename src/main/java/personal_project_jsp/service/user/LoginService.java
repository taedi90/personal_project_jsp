package personal_project_jsp.service.user;

import personal_project_jsp.dto.User;

import java.util.Map;

public interface LoginService {
    Map<String, String> login(User user);
}
