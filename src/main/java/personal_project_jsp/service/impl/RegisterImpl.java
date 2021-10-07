package personal_project_jsp.service.impl;

import personal_project_jsp.dto.User;
import personal_project_jsp.service.Register;

public class RegisterImpl implements Register
{
    @Override
    public boolean isAllCorrect(User user) {
        return false;
    }

    @Override
    public User insertUser(User user) {

        return null;
    }

    private String hashing(){

        return null;
    }

}
