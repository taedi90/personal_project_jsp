package personal_project_jsp.util;

import java.awt.Graphics; 
import java.awt.Image; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import javax.imageio.ImageIO; 

public class ThumbUtil { 
	
	
	private static final ThumbUtil instance = new ThumbUtil();
	public static ThumbUtil getInstance() {return instance;}
	private ThumbUtil() {}
	
	
	
	public static int thumbTrim(String imgOriginalPath, String imgTargetPath) { 
		//String imgOriginalPath= "C:\\Users\\z\\Desktop\\imagetest\\TEST\\1.jpg"; // 원본 이미지 파일명 
		//String imgTargetPath= "C:\\Users\\z\\Desktop\\imagetest\\TEST\\2.jpg"; // 새 이미지 파일명 
		String imgFormat = "png"; 
		int newWidth = 200; 
		int newHeight = 200; 
		
		Image image; 
		int imageWidth; 
		int imageHeight; 
		try{ 
			// 원본 이미지 가져오기 
			image = ImageIO.read(new File(imgOriginalPath)); 
			// 원본 이미지 사이즈 가져오기 
			imageWidth = image.getWidth(null); 
			imageHeight = image.getHeight(null); 
			System.out.println("imageWidth : " + imageWidth); 
			System.out.println("imageHeight : " + imageHeight); 
			
			// 이미지 리사이즈 
			// Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용 
			// Image.SCALE_FAST : 이미지 부드러움보다 속도 우선 
			// Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘 
			// Image.SCALE_SMOOTH : 속도보다 이미지 부드러움을 우선 
			// Image.SCALE_AREA_AVERAGING : 평균 알고리즘 사용 
			Image resizeImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); 
			System.out.println("reimageWidth : " + resizeImage.getWidth(null)); 
			System.out.println("reimageHeight : " + resizeImage.getHeight(null)); 
			// 새 이미지 저장하기 
			BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB); 
			Graphics g = newImage.getGraphics(); g.drawImage(resizeImage, 0, 0, null); 
			g.dispose(); 
			ImageIO.write(newImage, imgFormat, new File(imgTargetPath)); 
			return 1;
		}catch (Exception e){ 
			e.printStackTrace(); 
			return 0;
		} 
		

		
	}
}

//출처: https://ktko.tistory.com/entry/자바-이미지-리사이즈 [KTKO 개발 블로그와 여행 일기]
