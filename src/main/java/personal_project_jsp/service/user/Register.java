package personal_project_jsp.service.user;

import personal_project_jsp.dto.User;

import java.util.Map;

public interface Register {

    Map<String, String> insertUser(User user);

}
