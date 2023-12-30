package com.dhx.apicore.service;

import com.dhx.apicore.model.DTO.InterfaceMetaDataDTO;
import com.dhx.apicommon.model.enums.InterfaceStatusEnum;
import com.dhx.apicore.util.CategoryBitMapUtil;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author adorabled4
 * @className ApiDocTest
 * @date : 2023/12/28/ 17:15
 **/
public class ApiDocTest {

    Configuration cfg = null;

    {
        cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/templates"));
        cfg.setDefaultEncoding("UTF-8");
    }

    public void Test() throws IOException, TemplateException {
        Template template = cfg.getTemplate("api-doc.md.ftl");
        InterfaceMetaDataDTO detailVO = createInterfaceDetail(true);
        generate(template, detailVO, "tmp");
    }

    private void generate(Template template, InterfaceMetaDataDTO api, String path) throws IOException, TemplateException {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = path + "/" + api.getName() + template.getName().replace(".ftl", "");
        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter out = new OutputStreamWriter(fos);
        template.process(api, out);
        fos.close();
        out.close();
    }

    private InterfaceMetaDataDTO createInterfaceDetail(boolean isExample) {
        InterfaceMetaDataDTO vo = new InterfaceMetaDataDTO();
        vo.setName("this is api-name");
        vo.setRequestMethod("GET");
        vo.setStatus(InterfaceStatusEnum.AVAILABLE.getName());
        vo.setServiceAddress("http://turboapi.dhx.icu/");
        vo.setCategories(CategoryBitMapUtil.parse2String(8960L));
        vo.setCallPath("api/v1/test/api");
        vo.setDescription("this is desc");
        if (isExample) {
            vo.setRequestExample("{\n\t'name':1232423\n}");
            vo.setResponseExample("{\n" +
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
        return vo;
    }


}
