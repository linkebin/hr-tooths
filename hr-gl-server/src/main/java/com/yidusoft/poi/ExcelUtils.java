package com.yidusoft.poi;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * User: libf
 * Time: 2018-11-26 15:45
 */
public class ExcelUtils {
	public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws Exception {
		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/vnd.ms-excel");
		// 下载文件的默认名称
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
		exportExcel(data, response.getOutputStream());
	}

	public static void exportExcel(ExcelData data, OutputStream out) throws Exception {

		XSSFWorkbook wb = new XSSFWorkbook();
		try {
			String sheetName = data.getName();
			if (null == sheetName) {
				sheetName = "Sheet1";
			}
			XSSFSheet sheet = wb.createSheet(sheetName);
			writeExcel(wb, sheet, data);

			wb.write(out);
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			//此处需要关闭 wb 变量
			out.close();
		}
	}

	private static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {

		int rowIndex = 0;

		rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
		writeRowsToExcel(wb, sheet, data.getRows(),data.getCellType(), rowIndex);
		autoSizeColumns(sheet, data.getTitles().size() + 1);

	}

	private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
		int rowIndex = 0;
		int colIndex = 0;

		Font titleFont = wb.createFont();
		titleFont.setFontName("simsun");
		//titleFont.setBoldweight(Short.MAX_VALUE);
		// titleFont.setFontHeightInPoints((short) 14);
		titleFont.setColor(IndexedColors.BLACK.index);

		XSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
		titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setFont(titleFont);
		setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

		Row titleRow = sheet.createRow(rowIndex);
		// titleRow.setHeightInPoints(25);
		colIndex = 0;

		for (String field : titles) {
			Cell cell = titleRow.createCell(colIndex);
			cell.setCellValue(field);
			cell.setCellStyle(titleStyle);
			colIndex++;
		}

		rowIndex++;
		return rowIndex;
	}

	private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows,List<String> cellType, int rowIndex) {
		int colIndex = 0;

		Font dataFont = wb.createFont();
		dataFont.setFontName("simsun");
		// dataFont.setFontHeightInPoints((short) 14);
		dataFont.setColor(IndexedColors.BLACK.index);

		for (List<Object> rowData : rows) {
			Row dataRow = sheet.createRow(rowIndex);
			// dataRow.setHeightInPoints(25);
			colIndex = 0;
			for (Object cellData : rowData) {
				Cell cell = dataRow.createCell(colIndex);
				if (cellData != null) {
					if(cellType!=null && cellType.size()>colIndex) {
						if ("日期".equals(cellType.get(colIndex))) {
							SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy/MM/dd" );
							try {
								cell.setCellValue(sdf.parse(cellData.toString()));
							} catch (ParseException e) {
								e.printStackTrace();
							}
						} else if ("双精度浮点数".equals(cellType.get(colIndex))) {
							cell.setCellValue(Double.valueOf(cellData.toString()));
						} else if ("浮点数".equals(cellType.get(colIndex))) {
							cell.setCellValue(Float.valueOf(cellData.toString()));
						} else if ("整数".equals(cellType.get(colIndex))) {
							cell.setCellValue(Integer.valueOf(cellData.toString()));
						} else {
							cell.setCellValue(cellData.toString());
						}
					}else{
						cell.setCellValue(cellData.toString());
					}
				} else {
					cell.setCellValue("");
				}
				XSSFCellStyle dataStyle = wb.createCellStyle();
				dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
				dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
				dataStyle.setFont(dataFont);
				setBorder(dataStyle, BorderStyle.THIN,  new XSSFColor(new Color(0, 0, 0)));
				if(cellType!=null && cellType.size()>colIndex){
					XSSFDataFormat format= wb.createDataFormat();

					if("日期".equals(cellType.get(colIndex))){
						dataStyle.setDataFormat(format.getFormat("yyyy/mm/dd"));
					}else if("双精度浮点数".equals(cellType.get(colIndex))){
						dataStyle.setDataFormat(format.getFormat("#,##0.00"));
					}else if("浮点数".equals(cellType.get(colIndex))){
						dataStyle.setDataFormat(format.getFormat("#,##0.0"));
					}else if("整数".equals(cellType.get(colIndex))){
						dataStyle.setDataFormat(format.getFormat("#,##0"));
					}else if("文本".equals(cellType.get(colIndex))){
						dataStyle.setDataFormat(format.getFormat("@"));
					}
				}


				cell.setCellStyle(dataStyle);
				colIndex++;
			}
			rowIndex++;
		}
		return rowIndex;
	}

	private static void autoSizeColumns(Sheet sheet, int columnNumber) {

		for (int i = 0; i < columnNumber; i++) {
			int orgWidth = sheet.getColumnWidth(i);
			sheet.autoSizeColumn(i, true);
			int newWidth = (int) (sheet.getColumnWidth(i) + 100);
			if (newWidth > orgWidth) {
				sheet.setColumnWidth(i, newWidth);
			} else {
				sheet.setColumnWidth(i, orgWidth);
			}
		}
	}

	private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
		style.setBorderTop(border);
		style.setBorderLeft(border);
		style.setBorderRight(border);
		style.setBorderBottom(border);
		style.setBorderColor(BorderSide.TOP, color);
		style.setBorderColor(BorderSide.LEFT, color);
		style.setBorderColor(BorderSide.RIGHT, color);
		style.setBorderColor(BorderSide.BOTTOM, color);
	}
}
