package com.example.demo.pdf;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface PdfCustomService {

    /**
     * 生成准考证PDF
     * @param admissionCard 准考证信息
     * @param response 响应
     */
    void generatorAdmissionCard(AdmissionCard admissionCard, HttpServletResponse response) throws UnsupportedEncodingException, FileNotFoundException;
}
