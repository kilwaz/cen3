package clarity.load;

import clarity.load.data.Record;
import clarity.load.excel.Template;
import log.AppLogger;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class Excel implements Loader {
    private static Logger log = AppLogger.logger();

    private File excelFileToLoad = null;

    public Excel() {
        super();
    }

    public Excel file(File excelFileToLoad) {
        this.excelFileToLoad = excelFileToLoad;
        return this;
    }

    @Override
    public void process() {
        if (excelFileToLoad != null && excelFileToLoad.exists()) {
            try {
                Workbook workbook = new XSSFWorkbook(excelFileToLoad);
                Sheet datatypeSheet = workbook.getSheetAt(0);

                Template template = new Template();
                for (Row currentRow : datatypeSheet) {
                    Record record = new Record();
                    for (Cell currentCell : currentRow) {
                        if (currentCell.getCellType() == CellType.STRING) {
                            //log.info("String " + currentCell.getStringCellValue());
                            record.addValue(currentCell.getStringCellValue(), currentCell.getColumnIndex(), currentCell.getRowIndex());
                        } else if (currentCell.getCellType() == CellType.NUMERIC) {
                            //log.info("Number " + currentCell.getNumericCellValue());
                            record.addValue(currentCell.getNumericCellValue(), currentCell.getColumnIndex(), currentCell.getRowIndex());
                        }
                    }

                    if (currentRow.getRowNum() == 0) {
                        template.headerRecord(record);
                    } else {
                        template.addRecord(record);
                    }
                }

                template.integrate();

                log.info("Done");
            } catch (IOException | InvalidFormatException e) {
                e.printStackTrace();
            }

            log.info("We are processing a file");
        } else {
            log.info("No file loaded to process");
        }
    }
}
