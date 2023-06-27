package hello.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCorruptionExample {
    public static void main(String[] args) {
        // 손상시킬 이미지 파일 경로
        String imagePath = "/Users/jinyoung_1_2/[test-file]/pdf1.pdf";

        // 이미지 파일을 읽어옴
        try (FileInputStream fis = new FileInputStream(imagePath)) {
            // 이미지 데이터를 수정하여 손상시킴
            byte[] imageData = fis.readAllBytes();
            byte[] corruptedData = corruptImageData(imageData);

            // 손상된 이미지 파일로 저장
            String corruptedImagePath = "/Users/jinyoung_1_2/[test-file]/pdf_destroyed.pdf";
            try (FileOutputStream fos = new FileOutputStream(corruptedImagePath)) {
                fos.write(corruptedData);
                System.out.println("손상된 이미지 파일이 생성되었습니다.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 이미지 데이터를 손상시키는 메서드
    private static byte[] corruptImageData(byte[] imageData) {
        // 이미지 데이터의 일부를 수정하여 손상시킴
        // 예를 들어, 맨 앞 100바이트를 변경하거나 일부 픽셀 데이터를 수정할 수 있습니다.
        // 이 예시에서는 맨 앞 100바이트를 0으로 설정하여 손상시킵니다.
        for (int i = 0; i < 100; i++) {
            imageData[i] = 0;
        }
        return imageData;
    }
}
