package ch.juventus.se.kiosk.helper;

import ch.juventus.se.kiosk.model.article.Article;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * @author: Joni
 * @version: 1.0
 * @since: 07.09.2017
 */
public class ExcelFileHandler implements FileHandler{

    private File inputFile, outputFile;

    public ExcelFileHandler(File inputFile, File outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     * Reads articles from an Excel File.
     * @return Returns a List of Articles
     */
    public List<Article> readArticles() {
        List<Article> articles = new ArrayList<>();
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
        return articles;
    }

    /**
     * Saves articles to an Excel File.
     * @param articles List of articles to be saved.
     */
    public void writeArticles(List<Article> articles) {
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

        Set<Article> articlesSet = new HashSet<>(articles);
        for (Article article : articlesSet) {
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
}
