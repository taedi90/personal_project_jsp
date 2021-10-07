package personal_project_jsp;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class EncryptProperties {
    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(System.getenv("TAEDI_ENC_KEY"));
        encryptor.setIvGenerator(new RandomIvGenerator());
        encryptor.setAlgorithm("PBEWithHmacSHA512AndAES_256");
        encryptor.setKeyObtentionIterations(100); //해싱 횟수

        ArrayList<String> Strings = new ArrayList<>();

        Strings.add("jdbc:mysql://taedi.iptime.org:20111/my_board2?useSSL=false");
        Strings.add("아이디");
        Strings.add("패스");

        for (String str : Strings) {
            String encStr = encryptor.encrypt(str);
            String decStr = encryptor.decrypt(encStr);
            System.out.printf("str : %s%nencStr : %s%n", decStr, encStr);
        }

    }
}