package com.numbering.demo.NUM_PACKAGE;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.numbering.demo.DTO.DocumentStyleDTO;
import com.numbering.demo.DTO.StyleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@RestController
public class NUMController {

    @Autowired
    NUMService ns;

    @Autowired
    StyleService styleService;

    @PostMapping("/new/files/data")
    public void saveDocument(@RequestParam("file") MultipartFile file) throws Exception {
        License lic = new License();
        lic.setLicense("D:\\ZYLIQ\\TASK-BUG-R&D-ANALYZE\\ASPOSE\\LICENCES\\Aspose.WordsforJava.lic");
        byte[] bytes = file.getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        Document document = new Document(inputStream);
        Type listType = new TypeToken<List<DocumentStyleDTO>>() {}.getType();
        List<DocumentStyleDTO> styles = new Gson().fromJson(StyleConstant.docStyle, listType);

        //Testing
//        Document document1 = new Document();
        Map<String,ListInfo> listofIds = ns.createDocumentStyles(styles,document);
        styleService.updateStyleAndNumbering(document,styles,listofIds);
        styleService.applyDocumentFormatting(document,styles,listofIds);
        styleService.normalizeParagraphStyles(document,listofIds);
        
        


        String outputPath = "D:\\ZYLIQ\\TASK-BUG-R&D-ANALYZE\\ASPOSE\\DOWNLOADED-OR-TEST-DOCUMENTS\\ASPOSE-DOWNLOADS\\25-08-2025\\ASPOSE-final-2.docx";
        new File("D:\\ZYLIQ\\TASK-BUG-R&D-ANALYZE\\ASPOSE\\DOWNLOADED-OR-TEST-DOCUMENTS\\ASPOSE-DOWNLOADS").mkdirs();
        document.save(outputPath);
    }
}

