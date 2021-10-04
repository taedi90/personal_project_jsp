package personal_project_jsp.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class RandUtil {
	
	private static final RandUtil instance = new RandUtil();
	public static RandUtil getInstance() {return instance;}
	private RandUtil() {}

	public String getSecureRand() {
		
		
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
                };
        
        StringBuffer sb = new StringBuffer();

		SecureRandom sr = null;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

        int idx = 0;
        int len = charSet.length;
        
        for (int i=0; i<20; i++) {
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }

		//System.out.println(sb.toString());
		return sb.toString();

	}
}