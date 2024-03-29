package clarity.load;

import clarity.load.data.LoadedRecord;
import clarity.load.data.Value;
import clarity.load.excel.Template;
import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

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
            Workbook workbook = null;
            try {
                log.info("Start");
                long startMili = System.currentTimeMillis();
                workbook = WorkbookFactory.create(excelFileToLoad);

                Sheet datatypeSheet = workbook.getSheetAt(0);

                Template template = new Template();
                LoadedRecord headerRecord = new LoadedRecord();
                Integer count = 0;
                long timer = System.currentTimeMillis();
                for (Row currentRow : datatypeSheet) {
                    if (currentRow.getRowNum() == 0) { // Construct the header row first to get all the column names
                        for (Cell currentCell : currentRow) {
                            if (currentCell.getCellType() == CellType.STRING) {
                                headerRecord.addValue(currentCell.getStringCellValue(), currentCell.getColumnIndex(), currentCell.getRowIndex(), currentCell.getStringCellValue());
                            } else if (currentCell.getCellType() == CellType.NUMERIC) {
                                headerRecord.addValue(currentCell.getNumericCellValue(), currentCell.getColumnIndex(), currentCell.getRowIndex(), currentCell.getStringCellValue());
                            }
                        }
                    } else { // Loop through the rest of the data, reference header row to apply column names to data
                        LoadedRecord dataRecord = new LoadedRecord();
                        for (Cell currentCell : currentRow) {

                            Value headerColumn = headerRecord.getValueByColumnNumber(currentCell.getColumnIndex());
                            if (currentCell.getCellType() == CellType.STRING) {
                                dataRecord.addValue(currentCell.getStringCellValue(), currentCell.getColumnIndex(), currentCell.getRowIndex(), headerColumn.getColumnName());
                            } else if (currentCell.getCellType() == CellType.NUMERIC) {
                                dataRecord.addValue(currentCell.getNumericCellValue(), currentCell.getColumnIndex(), currentCell.getRowIndex(), headerColumn.getColumnName());
                            }
                        }

                        template.integrate(dataRecord);
                    }
                    count++;
                    if (count % 10 == 0) {
                        long timerTick = System.currentTimeMillis();
                        log.info(count + " - " + (timerTick - timer) + "ms");
                        timer = timerTick;
                    }
                }

                log.info("Done " + (System.currentTimeMillis() - startMili));
            } catch (IOException ex) {
                log.error(ex);
            } finally {
                if (workbook != null) {
                    try {
                        workbook.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            log.info("We are processing a file");
        } else {
            log.info("No file loaded to process");
        }
    }
}
