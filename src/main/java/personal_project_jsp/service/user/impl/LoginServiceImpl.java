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
        User dbUser = dao.getUserInfo(user);

        if(dbUser == null){
            map.put("res", "0");
            map.put("comment", "아이디를 다시 확인해주시기 바랍니다.");
            return map;
        }

        if(dbUser.getWithdrawDate() != null){
            map.put("res", "0");
            map.put("comment", "이미 탈퇴한 회원입니다.");
            return map;
        }


        String plainPass = user.getOriginPass(); // 패스워드는 사용자 입력에서
        String salt = dbUser.getSalt(); // salt는 db 정보에서

        EncryptUtil enc = EncryptUtil.getInstance();

        String hashedPass = enc.getHashing(plainPass, salt);

        if(!hashedPass.equals(dbUser.getPassword())){
            map.put("res", "0");
            map.put("comment", "비밀번호를 확인해주세요.");
            return map;
        }

        map.put("res", "1");
        map.put("comment", "로그인이 완료 되었습니다.");
        map.put("name", dbUser.getName());
        return map;

    }
}
