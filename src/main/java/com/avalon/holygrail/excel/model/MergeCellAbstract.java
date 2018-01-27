package com.avalon.holygrail.excel.model;

import com.avalon.holygrail.excel.norm.CellOption;
import com.avalon.holygrail.excel.norm.CellStyle;
import com.avalon.holygrail.excel.norm.MergeCell;

/**
 * 合并单元格属性
 * Created by 白超 on 2018/1/17.
 */
public abstract class MergeCellAbstract extends ExcelCellAbstract implements MergeCell, CellOption, CellStyle {

    /**
     * 垂直对齐方式-默认顶部对齐
     */
    protected String VAlign = "center";

    /**
     * 开始行
     */
    protected Integer startRow;

    /**
     * 开始列
     */
    protected Integer startCol;

    public MergeCellAbstract(Integer startRow, Integer startCol) {
        this.startRow = startRow;
        this.startCol = startCol;
    }

    public MergeCellAbstract(Integer startRow, Integer startCol, Integer rowSpan, Integer colSpan) {
        super(rowSpan, colSpan);
        this.startRow = startRow;
        this.startCol = startCol;
    }

    @Override
    public V_AlignType getVAlign() {
        return V_AlignType.getVAlignByName(VAlign);
    }

    @Override
    public void setVAlign(String VAlign) {
        this.VAlign = VAlign.toLowerCase();
    }

    @Override
    public Integer getStartRow() {
        return this.startRow;
    }

    @Override
    public Integer getEndRow() {
        return this.startRow + this.rowSpan - 1;
    }

    @Override
    public Integer getStartCol() {
        return startCol;
    }

    @Override
    public Integer getEndCol() {
        return this.startCol + this.colSpan - 1;
    }

}
