package mx.edu.unpa.proyectofinaldispositivosmoviles;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfTable;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

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


    /*public TemplatePDF(Context context) {
        this.context = context;
    }*/

    public void openDocument(String nombreDel_pdf){
        createFile(nombreDel_pdf+".pdf");
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
    String DIRECTORY_NAME = "MyPDFs";

    private  void createFile(String nombreDel_pdf){
        File folder= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),DIRECTORY_NAME);
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
  //checqar
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
    public void addParagraph(String text){
        try {
            paragraph= new Paragraph(text,fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("addParagraph",e.toString());
        }
    }

    public void createTable(String[] header, ArrayList<String[]> clients ){
        try {
            paragraph = new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable = new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
        int indexC=0;
        while(indexC<header.length){
            pdfPCell= new PdfPCell(new Phrase(header[indexC++],fSubTitle));
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.setBackgroundColor(BaseColor.GREEN);
            pdfPTable.addCell(pdfPCell);
        }
        for(int indexR=0;indexR<clients.size();indexR++){
            String[] row=clients.get(indexR);
            for(indexC=0;indexC<header.length;indexC++){
                pdfPCell= new PdfPCell(new Phrase(row[indexC]));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
        }
        paragraph.add(pdfPTable);

        document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }

    public void createP(String header, ArrayList<String[]> clients ){
        try {
            paragraph = new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable = new PdfPTable(1);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC=0;
            //ENCABEZADO
            while(indexC<1){
                pdfPCell= new PdfPCell(new Phrase(header,fSubTitle));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.GRAY);
                pdfPTable.addCell(pdfPCell);
                indexC++;
            }
            paragraph.add(pdfPTable);

            PdfPTable pdfPTable3 = new PdfPTable(1);
            PdfPTable pdfPTable2 = new PdfPTable(3);
            pdfPTable2.setWidthPercentage(100);

            pdfPTable3 = new PdfPTable(1);
            pdfPTable3.setWidthPercentage(50);

            for(int indexR=0;indexR<clients.size();indexR++){
                String[] row=clients.get(indexR);
                pdfPTable3 = new PdfPTable(1);
                for(indexC=0;indexC<row.length;indexC++){
                    pdfPCell= new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable3.addCell(pdfPCell);
                }
                pdfPTable2.addCell(pdfPTable3);
            }


            paragraph.add(pdfPTable2);


            document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }




    public void createP2(String header, ArrayList<String[]> clients ){
        try {
            paragraph = new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable = new PdfPTable(1);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC=0;
            //ENCABEZADO aqui solo van la s partes en grises
            while(indexC<1){
                pdfPCell= new PdfPCell(new Phrase(header,fSubTitle));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.GRAY);
                pdfPTable.addCell(pdfPCell);
                indexC++;
            }
            paragraph.add(pdfPTable);
            //esto es lo que va dentro de la tabla
            PdfPTable pdfPTable2 = new PdfPTable(5);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable2.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=0;indexR<2;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    pdfPCell= new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable2.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable2);//añade la tabla a la tabla

            PdfPTable pdfPTable3 = new PdfPTable(3);
            pdfPTable3.setWidthPercentage(100);

            for(int indexR=2;indexR<6;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    pdfPCell= new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable3.addCell(pdfPCell);
                }
            }
            paragraph.add(pdfPTable3);

            PdfPTable pdfPTable5 = new PdfPTable(5);
            pdfPTable5.setWidthPercentage(100);
            for(int indexR=6;indexR<8;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    pdfPCell= new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable5.addCell(pdfPCell);
                }
            }
            paragraph.add(pdfPTable5);

            PdfPTable pdfPTable4 = new PdfPTable(2);
            pdfPTable4.setWidthPercentage(100);

            for(int indexR=8;indexR<clients.size();indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    pdfPCell= new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable4.addCell(pdfPCell);
                }
            }
            paragraph.add(pdfPTable4);

            document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }


}
