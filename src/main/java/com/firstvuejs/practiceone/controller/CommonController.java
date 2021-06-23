package com.firstvuejs.practiceone.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CommonController {

    // 파일 업로드
    @PostMapping("/api/file/upload")
    public @ResponseBody String requestUploadFile(@RequestParam("fileList") List<MultipartFile> fileList) {
        System.err.println("파일 업로드 서버");
        
        // 폴더를 만들 디렉토리 경로(Window 기반)
        String folderPath = "D:\\vuejsSpring\\practiceone\\frontend\\src\\assets\\files";
        //String folderPath = "D:\\vuejsSpring\\practiceone\\frontend\\src\\assets\\img\\fff\\ddd\\bbb";
        
        File makeFolder = new File(folderPath);

        if(!makeFolder.exists()) {
            // 폴더를 생성합니다.
            makeFolder.mkdirs(); 
            System.out.println("폴더를 생성합니다.");
            
            // 정성적으로 폴더 생성시 true를 반환합니다.
            System.out.println("폴더가 존재하는지 체크 true/false : "+makeFolder.exists());
            
        } else {
            System.out.println("이미 해당 폴더가 존재합니다.");
        }

        try {
            for (MultipartFile multipartFile : fileList) {
                FileOutputStream writer = new FileOutputStream(folderPath+"\\" + multipartFile.getOriginalFilename());
                writer.write(multipartFile.getBytes());
                writer.close();
            }
        } catch (Exception e) {
            System.err.println("에러 : "+e);
            return "upload fail";
        }
        return "upload success";
    }

    // 파일 다운로드
    @PostMapping("/api/file/download")
    public void imageDownload(@RequestParam Map<String,Object> param, HttpServletResponse response, HttpServletRequest request){
        
        String urlPath = "D:\\vuejsSpring\\practiceone\\frontend\\src\\assets\\img\\고양이.jpg"; //full경로
        //String urlPath = (String) param.get("urlPath"); //full경로
        //테스트엑셀파일.xlsx // 고양이.jpg

        String fileName = "고양이.jpg"; //파일명
        //String fileName = (String) param.get("fileName"); //파일명
        System.err.println("들어오는 왔나 download");
        try {
            URL url = new URL("file:///"+urlPath);           
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setContentType("application/octer-stream");
            InputStream is = url.openStream();
 
            BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
            int len;
            byte[] buf = new byte[1024];
            while ( (len = is.read(buf)) > 0 ) {
                outs.write(buf, 0, len);
            }
            outs.close();
 
        } catch (Exception e) {                    
            e.printStackTrace();
        }            
    }


}
