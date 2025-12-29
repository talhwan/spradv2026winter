package com.thc.sprbasic2025fall.controller.page;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.io.IOUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

@RequestMapping("")
@Controller// 컨트롤러임을 알리기 위해 붙이는 어노테이션!
public class DefaultPageController {

    @RequestMapping("/index") //찾아올수 있는 주소값을 설정한 것입니다!
    public String index(){
        System.out.println("DefaultPageController.index()");
        return "index"; //페이지컨트롤러 리턴값! src->resources->templates 아래에 있는 리턴값, index.html을 찾아서 파씽해줌!!
    }

    @ResponseBody
    @RequestMapping(value = "/image/{file_name:.+}", method = {RequestMethod.GET,RequestMethod.POST})
    public byte[] getImage(@PathVariable("file_name") String file_name, HttpServletRequest request) throws Exception {
        byte[] return_byte = null;
        String path = "/Users/a01/workspace/uploadfile2025/";
        //해당 이미지를 byte[]형태로 반환
        File file = new File(path + file_name);
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return_byte = IOUtils.toByteArray(in);
        } catch (FileNotFoundException e) {
            //logger.info("FileNotFoundException / file_name : " + file_name);
            //e.printStackTrace();
        } catch (IOException e) {
            //logger.info("IOException / file_name : " + file_name);
            //e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    //logger.info("final Exception / file_name : " + file_name);
                }
            }
        }
        return return_byte;
    }
    @ResponseBody
    @RequestMapping(value = "/download/{file_name:.+}", method = RequestMethod.GET)
    public void download(@PathVariable("file_name") String file_name, @RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //logger.info("root_path : " + root_path);
        String path = "/Users/a01/workspace/uploadfile2025/";
        File file = new File(path + file_name);

        //여기는 response 에 설정해주는 부분인데, 어려우면 당분간은 패쓰!!
        String mimeType= URLConnection.guessContentTypeFromName(file_name);		//--- 파일의 mime타입을 확인합니다.
        if(mimeType==null){			//--- 마임타입이 없을 경우 application/octet-stream으로 설정합니다.
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);	//--- response에 mimetype을 설정합니다.
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode(file.getName(), "utf-8") +"\"");
        //

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));	//--- inputstream 객체를 얻습니다.
        FileCopyUtils.copy(inputStream, response.getOutputStream());		//--- inputstream으로 파일을 읽고 outputsream으로 파일을 씁니다.
    }

}
