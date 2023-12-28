package com.dhx.apicore.manager;

import com.dhx.apicore.model.enums.InterfaceStatusEnum;
import com.dhx.apicore.util.CategoryBitMapUtil;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adorabled4
 * @className ApiSDKTest
 * @date : 2023/12/28/ 18:30
 **/
public class ApiSDKTest {

    Configuration cfg = null;

    {
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/templates"));
        cfg.setDefaultEncoding("UTF-8");
    }

    @Test
    public void Test() throws IOException, TemplateException {
        Template template = cfg.getTemplate("api-sdk-method.java.ftl");
        Map<String, Object> map = getData(true);
        String fileName = generate(template, map, "tmp");
    }

    @Test
    public void handleCodeTab() throws IOException {
        String fileName = "api-sdk-method.java.ftl";
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
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
            if (line.charAt(line.length() - 1) == '}' || line.charAt(0) == '}') {
                tmp.delete(0, 7);
                depth--;
            }
            if (line.charAt(line.length() - 1) == '{') {
                depth++;
            }
            tmp.append(line).append("\n");
            sb.append(tmp.toString());
        }
        br.close(); // 关闭读取流
        // 将处理后的内容更新到文件
        FileWriter fileWriter = new FileWriter("tmp/" + fileName + ".txt");
        fileWriter.write(sb.toString());
        fileWriter.close();
        is.close();
    }

    private String generate(Template template, Map<String, Object> map, String path) throws IOException, TemplateException {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = path + "/" + map.get("name") + template.getName().replace(".ftl", "");
        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter out = new OutputStreamWriter(fos);
        template.process(map, out);
        fos.close();
        out.close();
        return fileName;
    }


    private Map<String, Object> getData(boolean isExample) {
        Map<String, Object> vo = new HashMap<>();
        vo.put("name", "this is api-name");
        vo.put("requestMethod", "GET");
        vo.put("status", InterfaceStatusEnum.AVAILABLE.getName());
        vo.put("serviceAddress", "http://turboapi.dhx.icu/");
        vo.put("categories", CategoryBitMapUtil.parse2String(8960L));
        vo.put("callPath", "api/v1/test/api");
        vo.put("description", "this is desc");
        if (isExample) {
            vo.put("requestExample", "{\n\t'name':1232423\n}");
            vo.put("responseExample", "{\n" +
                    "  \"code\":200,\n" +
                    "  \"data\":{\n" +
                    "    \"author\":\"李白\",\n" +
                    "    \"dynasty\":\"唐\",\n" +
                    "    \"title\":\"梦游天姥吟留别\",\n" +
                    "    \"poetry\":\"千岩万转路不定，迷花倚石忽已暝。\"\n" +
                    "  },\n" +
                    "  \"message\":\"ok\"\n" +
                    "}");
        }
        vo.put("basePackage", "com.dhx.apisdk");
        vo.put("methodName", "getRandomPoet");
        vo.put("modelName", "Poet");
        vo.put("className", "HxApiClient" + vo.get("callPath").toString().substring(4, 6));
        return vo;
    }

}
