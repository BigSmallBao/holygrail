package pub.avalon.holygrail.excel.bean;

import pub.avalon.holygrail.excel.exception.ExcelException;
import pub.avalon.holygrail.excel.exception.ExportException;
import pub.avalon.holygrail.excel.model.BaseExcelTitleCell;
import pub.avalon.holygrail.excel.model.AbstractSXSSFExcelParser;
import pub.avalon.holygrail.excel.norm.CellStyle;
import pub.avalon.holygrail.excel.norm.ExcelSheetExport;
import pub.avalon.holygrail.excel.norm.ExcelWorkBookExport;
import pub.avalon.holygrail.excel.norm.Font;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

/**
 * SXSSFWorkbook 导出Excel
 *
 * @author 白超
 * @date 2018/1/17
 */
public class SXSSFExcelWorkBookExport extends AbstractSXSSFExcelParser implements ExcelWorkBookExport {

    protected SXSSFWorkbook sxssfWorkbook;

    protected ArrayList<SXSSFExcelSheetExport> sheets = new ArrayList<>();

    private ArrayList<CellStyle> cellStyles = new ArrayList<>();

    private ArrayList<Font> fonts = new ArrayList<>();

    public SXSSFExcelWorkBookExport() {
        this.sxssfWorkbook = new SXSSFWorkbook();
    }

    public SXSSFExcelWorkBookExport(int rowAccessWindowSize) {
        this.sxssfWorkbook = new SXSSFWorkbook(rowAccessWindowSize);
    }

    public SXSSFExcelWorkBookExport(SXSSFWorkbook workbook) {
        this.sxssfWorkbook = workbook;
    }

    @Override
    public ExcelSheetExport createSheet() throws ExportException {
        return this.createSheet("sheet" + sheets.size());
    }

    @Override
    public ExcelSheetExport createSheet(String sheetName) throws ExportException {
        SXSSFExcelSheetExport sheet = new SXSSFExcelSheetExport(sheetName, this);
        this.sheets.add(sheet);
        return sheet;
    }

    @Override
    public ExcelSheetExport getSheet(int index) {
        return this.sheets.get(index);
    }

    @Override
    public CellStyle findCellStyle(int index) {
        return this.cellStyles.get(index);
    }

    @Override
    public Font findFont(int index) {
        return this.fonts.get(index);
    }

    @Override
    public CellStyle createCellStyle(int index) {
        CellStyle cellStyle = new CellStyleProxy(this.sxssfWorkbook.createCellStyle());
        this.cellStyles.add(index, cellStyle);
        return cellStyle;
    }

    @Override
    public Font createFont(int index) {
        Font font = new FontProxy(this.sxssfWorkbook.createFont());
        this.fonts.add(index, font);
        return font;
    }

    @Override
    public int getSheetSize() {
        return sheets.size();
    }

    @Override
    public void export(File outFile) throws IOException {
        FileOutputStream fos = null;
        OutputStream osw = null;
        try {
            fos = new FileOutputStream(outFile);
            osw = new BufferedOutputStream(fos);
            this.sxssfWorkbook.write(osw);
        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public BaseExcelTitleCell buildExcelTitleCell(BaseExcelTitleCell excelTitle, int startRow, int endRow, int startCol, int endCol) {
        excelTitle.setStartRowNum(startRow + 1);
        excelTitle.setStartColNum(startCol + 1);
        return excelTitle;
    }
}