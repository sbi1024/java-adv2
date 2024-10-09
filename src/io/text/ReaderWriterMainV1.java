package io.text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV1 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        // 인코딩
        byte[] writeBytes = writeString.getBytes(UTF_8);
        System.out.println("write String = " + writeString);
        System.out.println("write bBytes = " + Arrays.toString(writeBytes));

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(TextConst.FINAL_NAME);
        fos.write(writeBytes);
        fos.close();

        // 파일에 읽기
        FileInputStream fis = new FileInputStream(TextConst.FINAL_NAME);
        byte[] readBytes = fis.readAllBytes();
        fis.close();
        
        // 디코딩
        String readString = new String(readBytes, UTF_8);
        System.out.println("read bytes = " + Arrays.toString(readBytes));
        System.out.println("readString = " + readString);
    }
}
