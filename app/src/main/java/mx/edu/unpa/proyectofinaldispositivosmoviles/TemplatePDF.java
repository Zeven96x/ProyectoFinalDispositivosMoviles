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
    private Font fHighText=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);

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
            pdfPTable3.setWidthPercentage(50);

            for(int indexR=0;indexR<clients.size();indexR++){
                String[] row=clients.get(indexR);
                pdfPTable3 = new PdfPTable(1);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexC%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable3.addCell(pdfPCell);
                }
                pdfPCell= new PdfPCell(pdfPTable3);
                pdfPCell.setBorderColor(BaseColor.WHITE);
                pdfPTable2.addCell( pdfPCell);
            }


            paragraph.add(pdfPTable2);
            paragraph.add("\n");
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
                    if ((indexR%2)==0||indexC==4){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable2.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable2);//añade la tabla a la tabla

            PdfPTable pdfPTable3 = new PdfPTable(3);
            pdfPTable3.setWidthPercentage(100);

            for(int indexR=2;indexR<6;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable3.addCell(pdfPCell);
                }
            }
            paragraph.add(pdfPTable3);

            PdfPTable pdfPTable5 = new PdfPTable(4);
            pdfPTable5.setWidthPercentage(100);
            for(int indexR=6;indexR<8;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable5.addCell(pdfPCell);
                }
            }
            paragraph.add(pdfPTable5);

            PdfPTable pdfPTable4 = new PdfPTable(2);
            pdfPTable4.setWidthPercentage(100);

            for(int indexR=8;indexR<clients.size();indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        if (indexR==9||indexR==11&&indexC==1){
                            pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                        }else{
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                        }
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable4.addCell(pdfPCell);
                }
            }
            paragraph.add(pdfPTable4);


            paragraph.add("\n");
            document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }



    public void createP3(String header, ArrayList<String[]> clients ){
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
            PdfPTable pdfPTable2 = new PdfPTable(2);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable2.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=0;indexR<4;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable2.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable2);//añade la tabla a la tabla

            PdfPTable pdfPTable5 = new PdfPTable(3);
            pdfPTable5.setWidthPercentage(100);
            for(int indexR=4;indexR<6;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexR%2)==0||indexC==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable5.addCell(pdfPCell);
                }
            }
            paragraph.add(pdfPTable5);
            paragraph.add("\n");
            document.add(paragraph);


        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }



    public void createP4(String header, ArrayList<String[]> clients ){
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
            PdfPTable pdfPTable2 = new PdfPTable(2);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable2.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=0;indexR<clients.size();indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0||indexC==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable2.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable2);//añade la tabla a la tabla

            document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }


    public void createP5(String header, ArrayList<String[]> clients ){
        try {

            paragraph = new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable = new PdfPTable(1);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
           // pdfPCell.setFixedHeight(80);
            int indexC=0;
            //ENCABEZADO aqui solo van la s partes en grises
            while(indexC<1){
                pdfPCell= new PdfPCell(new Phrase(header,fSubTitle));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.GRAY);
                pdfPTable.addCell(pdfPCell);
                indexC++;
            }
            paragraph.add("\n");
            paragraph.add(pdfPTable);
            //esto es lo que va dentro de la tabla
            PdfPTable pdfPTable2 = new PdfPTable(2);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable2.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=0;indexR<clients.size();indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexC%2)==0||indexR==0&&indexC==1){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(40);
                    pdfPTable2.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable2);//añade la tabla a la tabla

            paragraph.add("\n");
            document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }


    public void createP6(String header, ArrayList<String[]> clients ){
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
            PdfPTable pdfPTable2 = new PdfPTable(2);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable2.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=0;indexR<4;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable2.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable2);//añade la tabla a la tabla

            PdfPTable pdfPTable4 = new PdfPTable(1);
            pdfPTable4.setWidthPercentage(100);

            for(int indexR=4;indexR<clients.size();indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable4.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable4);
            paragraph.add("\n");
            document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }

    public void createP7(String header, ArrayList<String[]> clients ){
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
            PdfPTable pdfPTable2 = new PdfPTable(4);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable2.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=0;indexR<clients.size();indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if (indexR==0||indexC==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable2.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable2);//añade la tabla a la tabla

            paragraph.add("\n");
            paragraph.add("\n");
            paragraph.add("\n");
            paragraph.add("\n");
            paragraph.add("\n");
            paragraph.add("\n");
            paragraph.add("\n");
            paragraph.add("\n");
            paragraph.add("\n");
            document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }

    public void createP8(String header, ArrayList<String[]> clients ){
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

            for(int indexR=0;indexR<clients.size();indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if (indexR==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(30);;
                    pdfPTable2.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable2);//añade la tabla a la tabla

            paragraph.add("\n");
            paragraph.add("\n");

            document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }
    public void createP9(String header, ArrayList<String[]> clients ){
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



            PdfPTable pdfPTable2 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable2.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=0;indexR<1;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    //  pdfPCell.setBackgroundColor(BaseColor.CYAN);
                    pdfPTable2.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable2);//añade la tabla a la tabla


            PdfPTable pdfPTable3 = new PdfPTable(2);
            pdfPTable3.setWidthPercentage(100);

            for(int indexR=1;indexR<2;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if (indexC==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable3.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable3);

            //parientes
            PdfPTable pdfPTable4 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable4.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=2;indexR<3;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable4.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable4);//añade la tabla a la tabla


            PdfPTable pdfPTable5 = new PdfPTable(2);
            pdfPTable5.setWidthPercentage(100);

            for(int indexR=3;indexR<4;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexC%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable5.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable5);

            //afianzado
            PdfPTable pdfPTable6 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable6.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=4;indexR<5;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    //  pdfPCell.setBackgroundColor(BaseColor.CYAN);
                    pdfPTable6.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable6);//añade la tabla a la tabla


            PdfPTable pdfPTable7 = new PdfPTable(2);
            pdfPTable7.setWidthPercentage(100);

            for(int indexR=5;indexR<6;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexC%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable7.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable7);
            //sindicato
            PdfPTable pdfPTable8 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable8.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=6;indexR<7;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    //  pdfPCell.setBackgroundColor(BaseColor.CYAN);
                    pdfPTable8.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable8);//añade la tabla a la tabla


            PdfPTable pdfPTable9 = new PdfPTable(2);
            pdfPTable9.setWidthPercentage(100);

            for(int indexR=7;indexR<8;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexC%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable9.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable9);
            //seguro de vida
            PdfPTable pdfPTable10 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable10.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=8;indexR<9;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    //  pdfPCell.setBackgroundColor(BaseColor.CYAN);
                    pdfPTable10.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable10);//añade la tabla a la tabla


            PdfPTable pdfPTable11 = new PdfPTable(2);
            pdfPTable11.setWidthPercentage(100);

            for(int indexR=9;indexR<10;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexC%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable11.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable11);

            //disposición de viajar
            PdfPTable pdfPTable12 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable12.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=10;indexR<11;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    //  pdfPCell.setBackgroundColor(BaseColor.CYAN);
                    pdfPTable12.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable12);//añade la tabla a la tabla


            PdfPTable pdfPTable13 = new PdfPTable(2);
            pdfPTable13.setWidthPercentage(100);

            for(int indexR=11;indexR<12;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexC%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable13.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable13);
            //disposición de viajar
            PdfPTable pdfPTable14 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable14.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=12;indexR<13;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    //  pdfPCell.setBackgroundColor(BaseColor.CYAN);
                    pdfPTable14.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable14);//añade la tabla a la tabla


            PdfPTable pdfPTable15 = new PdfPTable(2);
            pdfPTable15.setWidthPercentage(100);

            for(int indexR=13;indexR<14;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if ((indexC%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    pdfPTable15.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable15);
           // paragraph.add("\n\n");

            //Fecha en que podria presentarse a trabajar
            PdfPTable pdfPTable16 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable16.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=14;indexR<15;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    //  pdfPCell.setBackgroundColor(BaseColor.CYAN);
                    pdfPTable16.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable16);

            PdfPTable pdfPTable17 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable17.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=15;indexR<16;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexC%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    //  pdfPCell.setBackgroundColor(BaseColor.CYAN);
                    pdfPTable17.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable17);

            PdfPTable pdfPTable18 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable18.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=16;indexR<17;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    if ((indexR%2)==0){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(20);
                    //  pdfPCell.setBackgroundColor(BaseColor.CYAN);
                    pdfPTable18.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable18);
            paragraph.add("\n");

            document.add(paragraph);
        }catch (DocumentException e) {
            Log.e("createTable",e.toString());
        }
    }
    public void createP10(String header, ArrayList<String[]> clients ){
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


            PdfPTable pdfPTable2 = new PdfPTable(1);//sirve para indicar el numero de columnas que quieres dentro de la tabla
            pdfPTable2.setWidthPercentage(100);//sirve para ver el porcentaje dentro de la tabla que vas a agarrar dejalo siempre en 100

            for(int indexR=0;indexR<1;indexR++){//toma un arreglo de string
                String[] row=clients.get(indexR);//añade el arreglo por sepadado
                for(indexC=0;indexC<row.length;indexC++){//introduce pocicion por pocicion en el arreglo
                    pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(50);
                    //  pdfPCell.setBackgroundColor(BaseColor.CYAN);
                    pdfPTable2.addCell(pdfPCell);//añade esa columna a la tabla
                }
            }
            paragraph.add(pdfPTable2);//añade la tabla a la tabla


            PdfPTable pdfPTable3 = new PdfPTable(1);
            pdfPTable3.setWidthPercentage(100);

            for(int indexR=1;indexR<2;indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    if (indexR==1){
                        pdfPCell= new PdfPCell(new Phrase(row[indexC],fHighText));
                    }else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    }
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(25);
                    pdfPTable3.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable3);
            PdfPTable pdfPTable4 = new PdfPTable(1);
            pdfPTable4.setWidthPercentage(100);

            for(int indexR=2;indexR<clients.size();indexR++){
                String[] row=clients.get(indexR);
                for(indexC=0;indexC<row.length;indexC++){
                    pdfPCell= new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(40);
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
