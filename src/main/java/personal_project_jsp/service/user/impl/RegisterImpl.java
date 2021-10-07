package personal_project_jsp.service.user.impl;

import personal_project_jsp.dao.UserDao;
import personal_project_jsp.dao.impl.UserDaoImpl;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.user.Register;
import personal_project_jsp.util.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

public class RegisterImpl implements Register
{
    
    @Override
    public Map<String, String> insertUser(User user) {
        Map<String, String> map = new HashMap<>();


        // 유효성 검사
        if(!isValid(user)){
            map.put("res", "0");
            map.put("comment", "입력이 올바르지 않습니다.");
            return map;
        }

        // 비밀번호 + salt(random) -> 단방향 해시함수
        EncryptUtil enc = EncryptUtil.getInstance();
        String salt = enc.getSalt();
        String password = enc.getHashing(user.getOriginPass(), salt);

        user.setSalt(salt);
        user.setPassword(password);

        // dao에 아이디 올리기
        UserDao dao = UserDaoImpl.getInstance();
        int res = dao.insertUser(user);
        if(res != 1){
            map.put("res", "0");
            map.put("comment", "가입 실패! 문제가 지속되면 관리자에게 문의하시기 바랍니다.");
            return map;
        }

        map.put("res", "1");
        map.put("comment", "가입 완료!");
        return map;
    }

    private boolean isValid(User user) {

        return true;
    }
}
