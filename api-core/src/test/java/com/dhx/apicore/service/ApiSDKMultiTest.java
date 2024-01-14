package com.dhx.apicore.service;

import com.dhx.apicommon.util.FileUtil;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adorabled4
 * @className ApiSDKMultiTest
 * @date : 2023/12/28/ 20:00
 **/
public class ApiSDKMultiTest {

    Configuration cfg = null;

    {
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(),"/tmp"));
        cfg.setDefaultEncoding("UTF-8");
    }

    @Test
    public void hTest() throws IOException {
        // 代码模板格式化
        FileUtil.handleCodeTab("templates/api-sdk-client-base.java.ftl");
    }
    @Test
    public void Test() throws IOException, TemplateException {
        Template template = cfg.getTemplate("api-sdk-client.java.ftl");
        Map<String, Object> map = getData(true);
        // 生成 (withOut Spring)
        String fileName = generate(template, map, "tmp");
    }

    private String generate(Template template, Map<String, Object> map, String path) throws IOException, TemplateException {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
//        String fileName = path + "/" + map.get("name") + template.getName().replace(".ftl", "");
        String fileName = "../"  + "TurboAPIClientImpl.java" ;
//        String fileName = "../api-sdk/src/main/java/com/dhx/apisdk/client/gen" + "/" + "TurboAPIClientImpl.java" ;
        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter out = new OutputStreamWriter(fos);
        template.process(map, out);
        fos.close();
        out.close();
        return fileName;
    }

    private Map<String, Object> getData(boolean isExample) {
        Map<String, Object> context = new HashMap<>();
        List objs = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Map<String, Object> api = new HashMap<>();
            api.put("modelName", "com.dhx.apicommon.model.v1.Poet");
            api.put("sdkMethodName", "callRandomPoet" + i);
            api.put("name", "this is api-name");
            api.put("requestMethod", "GET");
            api.put("version", "v1");
            api.put("docUrl", "http://blog.dhx.icu");
//            api.put("sdkParamName", "com.dhx.apicommon.model.v1.query.PoetQuery");
            api.put("callPath", "api/v1/common/poet/random");
            api.put("description", "this is desc");
            if (isExample) {
                api.put("requestExample", "{\n\t'name':1232423\n}");
                api.put("responseExample", "{\n" +
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
            objs.add(api);
        }
        context.put("apis", objs);
        context.put("basePackage", "com.dhx.apisdk");
        context.put("methodName", "getRandomPoet");
        context.put("time", "2023-12-29");
        context.put("className", "TurboAPIClientImpl");
        return context;
    }

}
