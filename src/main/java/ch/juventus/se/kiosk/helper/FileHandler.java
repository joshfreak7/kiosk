package ch.juventus.se.kiosk.helper;

import ch.juventus.se.kiosk.model.article.Article;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class FileHandler {

    private File inputFile, outputFile;

    public FileHandler(File inputFile, File outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public FileHandler(File outputFile) {
        this.outputFile = outputFile;
    }

    public void importArticlesFromJSON() {
        try {
            FileReader fr = new FileReader(inputFile);
            int c;
            String bla = "";
            while((c = fr.read()) != -1) {
                System.out.println((char)c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exportArticlesToExcel(List<Article> articles) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Inventory - List of Articles");

        ListHandler lh = new ListHandler(articles);
        System.out.println("Creating excel");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue((String)"Title");
        cell = row.createCell(1);
        cell.setCellValue((String)"Single Price");
        cell = row.createCell(2);
        cell.setCellValue((String)"Quantity");
        cell = row.createCell(3);
        cell.setCellValue((String)"Total Price");
        int rowNum = 1;
        int i;

        for (i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue((String) article.getTitle());
            cell = row.createCell(1);
            cell.setCellValue((String) article.getPrice().toString());
            cell = row.createCell(2);
            cell.setCellValue((Integer) lh.getArticleCount(article));
            cell = row.createCell(3);
            cell.setCellValue((Double) lh.getAmountPerArticle(article));
        }
        row = sheet.createRow(rowNum+2);
        cell = row.createCell(0);
        cell.setCellValue((String) "GRAND TOTAL:");
        cell = row.createCell(3);
        cell.setCellValue((Double) lh.getTotalAmount());

        try {
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    public void importArticlesFromExcel() {
        try {

            FileInputStream excelFile = new FileInputStream(inputFile);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportArticlesToJSON(List<Article> articles) {
        /*Gson gson = new GsonBuilder().create();
        Article article = new Softdrink("Testing Article", new Price("CHF", 10.30), 120);

        // 1. Java object to JSON, and save into a file
        try {
            gson.toJson(article, new FileWriter(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public String read(){
        String contentString = "";
        FileInputStream in = null;
        try {
            in = new FileInputStream(inputFile);

            System.out.println("Total file size to read (in bytes) : "
                    + in.available());

            int content;
            while ((content = in.read()) != -1) {
                // convert to char and display it
                contentString += (char) content;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contentString;
    }

    public void write(String text){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outputFile);
            byte[] texttInBytes = text.getBytes();
            byte[] defaultText = "Dies ist mein Output:\n".getBytes();
            out.write(defaultText);
            out.write(texttInBytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
