package com.dhx.apiinterface.util;

import com.dhx.apicommon.model.enums.LanguageEnum;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author adorabled4
 * @className OCRUtil
 * @date : 2024/01/14/ 23:31
 **/
public class OCRUtil {

    static Tesseract tesseract;

    static {
        tesseract=new Tesseract();
        try {
            File file = ResourceUtils.getFile("classpath:data/tessdata/fast");
            tesseract.setDatapath(file.getPath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static String doOCR(File image, LanguageEnum type) throws TesseractException {
        try{
            tesseract.setLanguage(type.getValue());
            //设置配置文件夹微视、识别语言、识别模式
            //使用 OSD 进行自动页面分割以进行图像处理
            tesseract.setPageSegMode(1);
            //设置引擎模式是神经网络LSTM引擎
            tesseract.setOcrEngineMode(1);
            //开始识别整张图片中的文字
            return tesseract.doOCR(image);
        }finally {
            // 识别完成之后需要删除文件
            image.delete();
        }
    }
}
