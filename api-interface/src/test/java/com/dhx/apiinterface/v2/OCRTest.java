package com.dhx.apiinterface.v2;

import com.dhx.apicommon.model.enums.LanguageEnum;
import com.dhx.apiinterface.util.OCRUtil;
import com.github.houbb.heaven.util.io.FileUtil;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author adorabled4
 * @className OCRTest
 * @date : 2024/01/14/ 21:26
 **/
public class OCRTest {

    Tesseract tesseract;
    long start;

    @BeforeEach
    public void init() {
        tesseract= new Tesseract();
        start = System.currentTimeMillis();
    }

    @AfterEach
    public void consume(){
        System.out.println("测试耗时 : " + (System.currentTimeMillis()-start) + " ms");
    }
    @Test
    public void chineseOCRTest() throws TesseractException {
        //加载要识别的图片
        File image = new File("tmp/ocr/chi.png");
        //设置配置文件夹微视、识别语言、识别模式
        tesseract.setDatapath("src/main/resources/data/tessdata/fast");
        //设置识别语言为中文简体，（如果要设置为英文可改为"eng"）
        tesseract.setLanguage("chi_sim");
        //使用 OSD 进行自动页面分割以进行图像处理
        tesseract.setPageSegMode(1);
        //设置引擎模式是神经网络LSTM引擎
        tesseract.setOcrEngineMode(1);
        //开始识别整张图片中的文字
        String result = tesseract.doOCR(image);
        System.out.println(result);
    }

    @Test
    public void englishOCRTest() throws TesseractException {
        //加载要识别的图片
        File image = new File("tmp/ocr/eng.png");
        String base64 = FileUtil.fileToBase64("tmp/ocr/eng.png");
        System.out.println(base64);
        FileUtil.base64ToFile(base64,"tmp/base64.png");
        File file = new File("tmp/base64.png");
        //设置配置文件夹微视、识别语言、识别模式
        tesseract.setDatapath("src/main/resources/data/tessdata/fast");
        //设置识别语言为中文简体，（如果要设置为英文可改为"eng"）
        tesseract.setLanguage("eng");
        //使用 OSD 进行自动页面分割以进行图像处理
        tesseract.setPageSegMode(1);
        //设置引擎模式是神经网络LSTM引擎
        tesseract.setOcrEngineMode(1);
        //开始识别整张图片中的文字
        String result = tesseract.doOCR(file);
        System.out.println(result);
    }

    @Test
    public void utilTest() throws TesseractException {
        String base64 = com.dhx.apicommon.util.FileUtil.fileToBase64("D:\\j2ee_project\\TurboAPI-backend\\api-interface\\tmp\\ocr\\chi.png");
        File file = com.dhx.apicommon.util.FileUtil.base64ToFile(base64,"png");
        System.out.println(OCRUtil.doOCR(file, LanguageEnum.CHI));
    }
}
