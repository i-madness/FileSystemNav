package app;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Валерий on 10.07.2015.
 */
public class DocumentReader {

    public DocumentReader() {}
    public DocumentReader(String path) {
        try {
            content = aVoid(path);
        } catch (Docx4JException e) {
            System.out.println("oh my");
        }

    }
    public List<Object> contentList = new ArrayList<Object>();
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    private String content;

    public String aVoid(String path) throws Docx4JException {
        File docxFile = new File(path);
        WordprocessingMLPackage wmlp = WordprocessingMLPackage.load(docxFile);
        List<Object> objs = wmlp.getMainDocumentPart().getContent();
        contentList.addAll(objs);
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : objs)
            stringBuilder.append(o.toString()).append("\n");
        return stringBuilder.toString();
    }
}
