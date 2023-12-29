package com.dhx.apicore.util;

import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author <a href="https://blog.dhx.icu/"> adorabled4 </a>
 * @className FileUtil
 * @date : 2023/07/05/ 15:45
 **/
public class FileUtil {


    public static final String[] PICTURE_TYPES = new String[]{".jpg", ".png", ".jpeg", ".gif", ".bmp", ".webp", ".GIF", ".BMP", ".WEBP", ".JPG", ".PNG", "JPEG",};
    public static final int MAX_SIZE = 20 * 1024 * 1000;

    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     *
     * @param file
     */
    public static void deleteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }

    /**
     * 检查图片是否符合规范 大小/类型
     *
     * @param file
     * @return
     */
    public static boolean checkFile(File file) throws IOException {
        String name = file.getName();
        long fileSize = getFileSize(file);
        if (fileSize >= MAX_SIZE) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小限制在20MB");
        }
        String suffix = name.substring(name.lastIndexOf("."));
        for (String picture_type : PICTURE_TYPES) {
            if (picture_type.equals(suffix)) {
                return true;
            }
        }
        throw new BusinessException(ErrorCode.PARAMS_ERROR, "请上传图片类型文件");
    }


    /**
     * @param file 文件
     * @return 返回文件大小 byte
     * @throws IOException
     */
    public static long getFileSize(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        return fc.size();
    }

    /**
     * 渲染ftl模板中的制表符
     *
     * @param fileName 文件名称 如 api-sdk-client.java.ftl
     * @throws IOException ioexception
     */
    public static String handleCodeTab(String fileName) throws IOException {
        InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        int depth = 0;
        String line;
        StringBuilder tmp = new StringBuilder();
        while ((line = br.readLine()) != null) {
            tmp.delete(0, tmp.length());
            for (int i = 0; i < depth; i++) {
                tmp.append("${\"\\t\"}");
            }
            // 6 chars
            // 排除前面的制表符
            if(!line.equals("")){
                if ( line.charAt(line.length() - 1) == '}' || line.charAt(0) == '}') {
                    tmp.delete(0, 7);
                    depth--;
                }
                if (line.charAt(line.length() - 1) == '{') {
                    depth++;
                }
            }
            tmp.append(line).append("\n");
            sb.append(tmp.toString());
        }
        br.close(); // 关闭读取流
        // 将处理后的内容更新到文件
        String handledFile = "tmp/" + fileName;
        FileWriter fileWriter = new FileWriter(handledFile);
        fileWriter.write(sb.toString());
        fileWriter.close();
        is.close();
        return handledFile;
    }

}
