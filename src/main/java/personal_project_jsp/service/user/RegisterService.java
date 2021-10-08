package personal_project_jsp.service.user;

import personal_project_jsp.dto.User;

import java.util.Map;

public interface RegisterService {

    Map<String, String> insertUser(User user);

}
