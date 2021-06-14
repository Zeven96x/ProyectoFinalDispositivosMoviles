package mx.edu.unpa.proyectofinaldispositivosmoviles;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.PageSize;

import java.io.File;
import java.io.FileOutputStream;

import static com.lowagie.text.PageSize.*;

public class TemplatePDF {
    private Context context;
    private File pdfFile;
    private Document document;
    private com.itextpdf.text.pdf.PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font fTitle=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    private Font fSubTitle=new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
    private Font fText=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
    private Font fHighText=new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD,BaseColor.RED);


    public TemplatePDF(Context context) {
        this.context = context;
    }

    public void openDocument(String nombreDel_pdf){
        createFile(nombreDel_pdf);
        try {
            document= new Document(com.itextpdf.text.PageSize.A4);
            pdfWriter= PdfWriter.getInstance(document,new FileOutputStream(pdfFile));
            document.open();
        }catch (Exception e){
            Log.e("openDocument",e.toString());
        }
    }

    public  void  closeDocument(){
        document.close();
    }

    private  void createFile(String nombreDel_pdf){
        File folder= new File(Environment.getExternalStorageDirectory().toString(),"MyAPPpdf");
        if (!folder.exists()){
            folder.mkdirs();
        }
        pdfFile=new File(folder,nombreDel_pdf);
    }

    public  void addMetaData(String title,String subject,String author){
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }

    public void  addTitles(String title,String subject,String date){
        try {
        paragraph= new Paragraph();
        addChildP(new Paragraph(title,fTitle));
        addChildP(new Paragraph(subject,fSubTitle));
        addChildP(new Paragraph("Generado: "+date,fHighText));
        paragraph.setSpacingAfter(30);
        document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("addTitles",e.toString());
        }

    }

    private  void  addChildP(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }


}
