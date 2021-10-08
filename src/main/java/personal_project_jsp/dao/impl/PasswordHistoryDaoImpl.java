package personal_project_jsp.dao.impl;

import personal_project_jsp.dao.PasswordHistoryDao;
import personal_project_jsp.dto.User;
import personal_project_jsp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordHistoryDaoImpl implements PasswordHistoryDao {

    private static final PasswordHistoryDaoImpl instance = new PasswordHistoryDaoImpl();
    public static PasswordHistoryDaoImpl getInstance() {return instance;}
    private PasswordHistoryDaoImpl() {}

    @Override
    public boolean isUsed(User user) {
        // 아이디랑 패스워드 넘겨서 확인하기
        // salt는 안바뀌기 때문에 password만 확인하면 됨
        String sql = "select count(*) from user where id = ? and password = ?";

        try(Connection con = JdbcUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
        ){

            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());

            try(ResultSet rs = pstmt.executeQuery();){
                if(rs.next()) {
                    if(rs.getInt(1) > 0){
                        return true;
                    }
                }
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
