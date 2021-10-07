package personal_project_jsp.util;

import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptUtil {
    private static final EncryptUtil instance = new EncryptUtil();
    public static EncryptUtil getInstance() {return instance;}
    private EncryptUtil() {}


    public String getHashing(String password, String salt) {

        System.out.println("original password :" + password);
        System.out.println("original salt :" + salt);

        byte[] bytePassword = password.getBytes();

        int stretching = 10000; // key stretching 횟수
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < stretching; i++){
            String temp = byteToString(bytePassword) + salt; // 패스워드와 salt 결합
            md.update(temp.getBytes()); // temp 문자열을 해싱해 md에 저장
            bytePassword = md.digest(); // 패스워드 갱신
        }

        System.out.println("hashed password :" + byteToString(bytePassword));

        return byteToString(bytePassword);
    }

    private String byteToString(byte[] temp) {
        StringBuilder sb = new StringBuilder();
        for(byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }


    public String getSalt() {

        int saltLength = 16; // SALT 길이

        byte[] temp = new byte[saltLength];
        StringBuffer sb = new StringBuffer();

        SecureRandom rnd = null;
        try {
            rnd = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        rnd.nextBytes(temp);

        return byteToString(temp);

    }

    // jbcrypt 사용
    public String hashingToBcrypt(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isValidPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

}
