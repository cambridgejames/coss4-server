package cn.net.powerinv.coss4.authority.service.impl;

import cn.net.powerinv.coss4.authority.service.ScoreAuthorityService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class ScoreAuthorityServiceImpl implements ScoreAuthorityService {

    public Map<String, Object> testCertificateWithCid() {
        PdfReader reader;
        PdfStamper stamper;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            BaseFont bf = BaseFont.createFont("/Alibaba-PuHuiTi-Regular.otf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font FontChinese = new Font(bf, 5, Font.NORMAL);
            reader = new PdfReader("/result_template.pdf");
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            form.addSubstitutionFont(bf);
            form.setField("compName", "数学与统计学院第二百三十届班徽班旗设计大赛");
            form.setField("cid", "5");
            form.setField("print_time", "2020年5月31日");
            stamper.setFormFlattening(false);
            stamper.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
