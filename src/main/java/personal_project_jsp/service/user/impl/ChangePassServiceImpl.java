package personal_project_jsp.service.user.impl;

import personal_project_jsp.dao.PasswordHistoryDao;
import personal_project_jsp.dao.UserDao;
import personal_project_jsp.dao.impl.PasswordHistoryDaoImpl;
import personal_project_jsp.dao.impl.UserDaoImpl;
import personal_project_jsp.dto.PasswordHistory;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.user.ChangePassService;
import personal_project_jsp.util.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

public class ChangePassServiceImpl implements ChangePassService {

    @Override
    public Map<String, String> changePass(Map<String, String> data) {
        Map<String, String> map = new HashMap<>();

        // 로그인 정보 확인
        if(data.get("id") == null){
            map.put("res", "0");
            map.put("comment", "로그인 상태를 확인해주세요.");
            return map;
        }

        // confirm 일치여부 확인
        if(!data.get("newPassword").equals(data.get("confirm"))){
            map.put("res", "0");
            map.put("comment", "확인 비밀번호가 일치하지 않습니다.");
            return map;
        }

        // 기존 password 일치여부 확인
        User user = new User();
        user.setId(data.get("id"));

        UserDao dao = UserDaoImpl.getInstance();
        user = dao.getUserInfo(user);

        EncryptUtil enc = EncryptUtil.getInstance();

        String hashedPass = enc.getHashing(data.get("originPass"), user.getSalt());


        if(!hashedPass.equals(user.getPassword())){
            map.put("res", "0");
            map.put("comment", "기존 비밀번호가 일치하지 않습니다.");
            return map;
        }

        // password 변경
        String hashedNewPass = enc.getHashing(data.get("newPassword"), user.getSalt());
        user.setPassword(hashedNewPass);
        user.setOriginPass(data.get("newPassword"));


        // 비밀번호 이력에 사용했던 이력이 있는지 확인
        PasswordHistoryDao phDao = PasswordHistoryDaoImpl.getInstance();
        if(phDao.isUsed(user)){
            map.put("res", "0");
            map.put("comment", "이미 사용한 비밀번호는 다시 사용할 수 없습니다.");
            return map;
        }

        int res = dao.updateUser(user);

        if(res != 1){
            map.put("res", "0");
            map.put("comment", "비밀번호 변경에 실패하였습니다!");
            return map;
        }

        // 결과 반환
        map.put("res", "1");
        map.put("comment", "비밀번호 변경 완료!");
        return map;

    }
}
