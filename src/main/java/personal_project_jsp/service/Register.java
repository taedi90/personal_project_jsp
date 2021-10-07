package personal_project_jsp.service;

import personal_project_jsp.dto.User;

public interface Register {
    // checkInput(입력받은 값 체크)
    boolean isAllCorrect(User user);
    // getSalt
    // hashing

    User insertUser(User user);
}
