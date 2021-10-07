package personal_project_jsp.service.user.impl;

import personal_project_jsp.dao.UserDao;
import personal_project_jsp.dao.impl.UserDaoImpl;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.user.LoginService;
import personal_project_jsp.util.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginServiceImpl implements LoginService {
    @Override
    public Map<String, String> login(User user) {
        Map<String, String> map = new HashMap<>();

        UserDao dao = UserDaoImpl.getInstance();
        user = dao.getUserInfo(user);

        if(user == null){
            map.put("res", "0");
            map.put("comment", "아이디를 다시 확인해주시기 바랍니다.");
            return map;
        }

        String plainPass = user.getOriginPass();
        String salt = user.getSalt();

        EncryptUtil enc = EncryptUtil.getInstance();

        String hashedPass = enc.getHashing(plainPass, salt);

        if(!hashedPass.equals(user.getPassword())){
            map.put("res", "0");
            map.put("comment", "비밀번호를 확인해주세요.");
            return map;
        }

        map.put("res", "1");
        map.put("comment", "로그인이 완료 되었습니다.");
        return map;

    }
}
