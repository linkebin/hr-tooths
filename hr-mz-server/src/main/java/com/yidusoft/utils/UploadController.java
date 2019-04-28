package com.yidusoft.utils;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * UploadController
 * 上传
 * Created by zcansheng on 2017/3/7.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    SecUserService secUserService;

    Logger logger = LoggerFactory.getLogger(UploadController.class);
    static List<String> pictures = null;//图片
    static List<String> docs = null;//文档
    static List<String> videos = null;//视频
    static List<String> tottents = null;//种子
    static List<String> audios = null;//音乐
    static List<String> others = null;//其他

    static {
        //初始化文件类型
        pictures = new ArrayList<String>(Arrays.asList("JPEG", "JPG", "PNG", "GIF", "TIFF", "BMP", "DWG", "DWG", "PSD"));
        List<String> docs = new ArrayList<String>(Arrays.asList("PPT", "PPTX", "RTF", "XML", "HTML", "CSS", "JS", "EML", "DBX", "PST", "XLS_DOC", "XLSX_DOCX", "VSD", "MDB",
                "MDB", "WPS", "WPD", "EPS", "PDF", "QDF", "PWL", "ZIP", "RAR", "JSP", "JAVA", "CLASS",
                "JAR", "MF", "EXE", "CHM"));
        List<String> videos = new ArrayList<String>(Arrays.asList("AVI", "RAM", "RM", "MPG", "MOV", "ASF", "MP4", "FLV", "MID"));
        List<String> tottents = new ArrayList<String>(Arrays.asList("TORRENT"));
        List<String> audios = new ArrayList<String>(Arrays.asList("WAV", "MP3"));
        List<String> others = new ArrayList<String>(Arrays.asList("JPEG", "PNG", "GIF", "TIFF", "BMP", "DWG", "DWG", "PSD"));
    }



    /**
     * 判断文件类型是否正确
     *
     * @param fileType 要求的类型
     * @param type     实际的类型
     * @return
     */
    public static boolean isRightType(String fileType, String type) {
        if ("pictures".equals(fileType)) {
            return pictures.contains(type.toUpperCase());
        } else if ("docs".equals(fileType)) {
            return docs.contains(type.toUpperCase());
        } else if ("videos".equals(fileType)) {
            return videos.contains(type.toUpperCase());
        } else if ("tottents".equals(fileType)) {
            return tottents.contains(type.toUpperCase());
        } else if ("audios".equals(fileType)) {
            return audios.contains(type.toUpperCase());
        } else {
            return true;
        }
    }

    // 上传图片 "JPG","PNG"
    @PostMapping("/uploadImg")
    public String uploadImg(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap();
        try {
            String fileName = file.getOriginalFilename();// 文件原名称

            String type = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            ;
            if (type.equals(".jpg") || type.equals(".png")) {
                String realPath = System.getProperty("user.dir");
                // 自定义的文件名称
                String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                // 设置存放图片文件的路径
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String childPath = "/upload/image/" + format.format(new Date());
                String path = realPath + childPath;

                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                file.transferTo(new File(path + "/" + trueFileName));
                String img = Base64ToImage.getImageStr(path + "/" + trueFileName);
                map.put("imgPath", path + "/" + trueFileName);
                map.put("img", img);

            } else {
                return ResultGenerator.genFailResult("只能上传png与jpg的文件！").toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult(map).toString();
    }


    @PostMapping("/uploadImglayUi")
    @ResponseBody
    public String uploadImglayUi(HttpServletRequest request, @RequestParam("file") MultipartFile file, String userId) {
        FileResponseData fileResponseData = null;
        try {
            String fileName = file.getOriginalFilename();// 文件原名称

            String type = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

            if (type.equals(".jpg") || type.equals(".png")) {
                String realPath = System.getProperty("user.dir");
                String childPath = "/upload/headImg";

                String path = realPath + childPath;
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                file.transferTo(new File(path + "/" + userId + type));

                fileResponseData = new FileResponseData();
                fileResponseData.setCode(0);
                fileResponseData.setMsg("上传成功");
                Data data = new Data();
                data.setSrc(childPath + "/" + userId + type);
                fileResponseData.setData(data);

            } else {
                fileResponseData.setCode(500);
                fileResponseData.setMsg("只能上传png与jpg的文件！");
                return JSON.toJSONString(fileResponseData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(fileResponseData);
    }

    //通用图片上传
    @PostMapping("/comUploadImg")
    public String comUploadImg(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();// 文件原名称
        String type = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if(fileName.contains(":")){
            fileName = fileName.substring(fileName.lastIndexOf("\\")+1).toLowerCase();
        }
        if (type.equals(".jpg") || type.equals(".png")) {
            String realPath = System.getProperty("user.dir");
            // 自定义的文件名称
            String saveFileName = String.valueOf(System.currentTimeMillis()) + type;
            // 设置存放图片文件的路径
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String childPath = "/upload/image/" + format.format(new Date());
            String path = realPath + childPath;
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                file.transferTo(new File(path + "/" + saveFileName));
                return JSON.toJSONString(ResultGenerator.genSuccessResult(childPath + "/" + saveFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return JSON.toJSONString(ResultGenerator.genFailResult("文件上传失败"));
        } else {
            return JSON.toJSONString(ResultGenerator.genFailResult("只支持jpg与png的格式上传！"));
        }
    }

    /**
     * 获取限制的文件类型
     *
     * @param fileType
     * @return
     */
    public static List<String> getTypes(String fileType) {
        if ("pictures".equals(fileType)) {
            return pictures;
        } else if ("docs".equals(fileType)) {
            return docs;
        } else if ("videos".equals(fileType)) {
            return videos;
        } else if ("tottents".equals(fileType)) {
            return tottents;
        } else if ("audios".equals(fileType)) {
            return audios;
        } else {
            return null;
        }
    }



    @PostMapping("/upload/cropHeadImg")
    public Result cropHeadImg(String image) {
        String saveFileName = "";
        String path = "";
        FileResponseData fileResponseData = null;
        String childPath = "";
        try {
            if (image != null && !"".equals(image)) {
                //   去掉base64数据头部data:image/png;base64,和尾部的” " “
                String[] ww = image.split(",");
                image = ww[1];
                String[] aa = image.split("\"");
                image = aa[0];

                String realPath = System.getProperty("user.dir");
                // 自定义的文件名称
                saveFileName = Security.getUserId() + ".jpg";
                childPath = "/upload/headImg/";
                path = realPath + childPath;
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    BASE64Decoder decoder = new BASE64Decoder();
                    try {
                        // Base64解码
                        byte[] bytes = decoder.decodeBuffer(image);
                        for (int i = 0; i < bytes.length; ++i) {
                            if (bytes[i] < 0) {// 调整异常数据
                                bytes[i] += 256;
                            }
                        }
                        // 生成jpeg图片
                        OutputStream out = new FileOutputStream(path + "/" + saveFileName);
                        out.write(bytes);
                        out.flush();
                        out.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                SecUser secUser = secUserService.findById(Security.getUserId());
                secUser.setHeadImg(childPath + saveFileName);

                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("userSessionId", secUser.getId());
                session.setAttribute("userSession", secUser);

                secUserService.update(secUser);
                return ResultGenerator.genSuccessResult(childPath + "/" + saveFileName);
            }
        } catch (Exception e) {
            return ResultGenerator.genFailResult("上传图片失败！");
        }
        return ResultGenerator.genSuccessResult(childPath + "/" + saveFileName);
    }


    /**
     * 检查是否上传资质证明
     * @return
     */
    @PostMapping("/findUserCertification")
    public Result findUserCertification(String id){
        try {
            String userId = id;
            SecUser secUser = secUserService.findById(userId);
            return ResultGenerator.genSuccessResult(secUser);
        }catch (Exception e){
            return ResultGenerator.genFailResult("请上传资质证明的材料！");
        }
    }

    @PostMapping("/findCertification")
    public Result findCertification(){
        String userId = Security.getUserId();
        SecUser secUser = secUserService.findById(userId);
        return ResultGenerator.genSuccessResult(secUser);
    }



    /**
     * 百度富文本的配置参数
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/ueConfig")
    @ResponseBody
    public JSON ueConfig(HttpServletRequest request) {
        String config ="\n" +
                "{\n" +
                "   \n" +
                "    \"imageActionName\": \"uploadimage\",\n" +
                "    \"imageFieldName\": \"upfile\",\n" +
                "    \"imageMaxSize\": 2048000, \n" +
                "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "    \"imageCompressEnable\": true,\n" +
                "    \"imageCompressBorder\": 1600,\n" +
                "    \"imageInsertAlign\": \"none\",\n" +
                "    \"imageUrlPrefix\": \"\",\n" +
                "    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "                           \n" +
                "\n" +
                "  \n" +
                "    \"scrawlActionName\": \"uploadscrawl\",\n" +
                "    \"scrawlFieldName\": \"upfile\",\n" +
                "    \"scrawlPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "    \"scrawlMaxSize\": 2048000, \n" +
                "    \"scrawlUrlPrefix\": \"\",\n" +
                "    \"scrawlInsertAlign\": \"none\",\n" +
                "\n" +
                "   \n" +
                "    \"snapscreenActionName\": \"uploadimage\",\n" +
                "    \"snapscreenPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "    \"snapscreenUrlPrefix\": \"\",\n" +
                "    \"snapscreenInsertAlign\": \"none\",\n" +
                "\n" +
                "   \n" +
                "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"],\n" +
                "    \"catcherActionName\": \"catchimage\",\n" +
                "    \"catcherFieldName\": \"source\",\n" +
                "    \"catcherPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "    \"catcherUrlPrefix\": \"\",\n" +
                "    \"catcherMaxSize\": 2048000, \n" +
                "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "\n" +
                "   \n" +
                "    \"videoActionName\": \"uploadvideo\",\n" +
                "    \"videoFieldName\": \"upfile\",\n" +
                "    \"videoPathFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "    \"videoUrlPrefix\": \"\",\n" +
                "    \"videoMaxSize\": 102400000,\n" +
                "    \"videoAllowFiles\": [\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"],\n" +
                "\n" +
                "   \n" +
                "    \"fileActionName\": \"uploadfile\",\n" +
                "    \"fileFieldName\": \"upfile\",\n" +
                "    \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
                "    \"fileUrlPrefix\": \"\",\n" +
                "    \"fileMaxSize\": 51200000, \n" +
                "    \"fileAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
                "    ],\n" +
                "\n" +
                "   \n" +
                "    \"imageManagerActionName\": \"listimage\",\n" +
                "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\",\n" +
                "    \"imageManagerListSize\": 20,\n" +
                "    \"imageManagerUrlPrefix\": \"\",\n" +
                "    \"imageManagerInsertAlign\": \"none\",\n" +
                "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
                "\n" +
                "   \n" +
                "    \"fileManagerActionName\": \"listfile\",\n" +
                "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\",\n" +
                "    \"fileManagerUrlPrefix\": \"\",\n" +
                "    \"fileManagerListSize\": 20,\n" +
                "    \"fileManagerAllowFiles\": [\n" +
                "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
                "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
                "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
                "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
                "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
                "    ]\n" +
                "\n" +
                "}";

        return JSON.parseObject(config);
    }


    /**
     * 百度富文本的图片上传
     *
     * @return
     */
    @PostMapping("/ueUploadImg")
    public JSON ueUploadImg(MultipartFile upfile) {
        String fileName = upfile.getOriginalFilename();
        Result result = JSON.parseObject(this.comUploadImg(upfile),Result.class);
        if ("200".equals(result.getCode()+"")) {
            String path = result.getData().toString();
            String config ="{\"state\": \"SUCCESS\",\"url\": \"/files" + path + "\",\"title\": \""+fileName+"\",\"original\": \""+fileName+"\" }";
            return JSON.parseObject(config);

        }
        return null;
    }
}