package com.wxf.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.util.PoitlIOUtils;
import com.wxf.entity.Author;
import com.wxf.entity.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/word")
public class WordExportController {

    @GetMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {

        Author author = new Author();
        author.setAge(1000);
        author.setName("哈利波特");

        Data data = new Data();
        data.setName("指环王");
        data.setStart("2021-08-05 12:12:12");
        data.setAuthor(author);


        XWPFTemplate compile = XWPFTemplate.compile("E:\\wordExport\\templates\\test2.docx");
        XWPFTemplate template = compile.render(data);


        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=\"" + "out_template.docx" + "\"");
        OutputStream out = null;
        BufferedOutputStream bos = null;
        try {
            out = response.getOutputStream();
            bos = new BufferedOutputStream(out);
            template.write(bos);
            bos.flush();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            PoitlIOUtils.closeQuietlyMulti(template, bos, out);
        }

    }
}
