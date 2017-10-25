package com.springmvc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelUtil {


	public static Map<String, String> readExcel(String filePath, String title) {

		Map<String, String> map = new HashMap<String, String>();

		// 创建对Excel工作簿文件的引用
		try {
			// filePath是文件地址。
			XSSFWorkbook wookbook = new XSSFWorkbook(new FileInputStream(filePath));
			// 在Excel文档中，第一张工作表的缺省索引是0
			XSSFSheet sheet = wookbook.getSheetAt(0);
			// 获取到Excel文件中的所有行数
			int rows = sheet.getPhysicalNumberOfRows();
			int max_cells = 0;
			// 获取最长的列，在实践中发现如果列中间有空值的话，那么读到空值的地方就停止了。所以我们需要取得最长的列。<br>
			// //如果每个列正好都有一个空值得话，通过这种方式获得的列长会比真实的列要少一列。所以我自己会在将要倒入数据库中的EXCEL表加一个表头<br>
			// //防止列少了，而插入数据库中报错。
			for (int i = 0; i < rows; i++) {
				XSSFRow row = sheet.getRow(i);
				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();
					if (max_cells < cells) {
						max_cells = cells;
					}

				}
			}
			System.out.println(max_cells);
			// 遍历行
			for (int i = 0; i < rows; i++) {
				// 读取左上端单元格
				XSSFRow row = sheet.getRow(i);
				// 行不为空
				if (row != null) {
					String value = "";
					// 遍历列
					String b_id = null;
					for (int j = 0; j < max_cells; j++) {
						// 获取到列的值
						XSSFCell cell = row.getCell(j);
						// 把所有是空值的都换成NULL
						if (cell == null) {
							value += "NULL,";
						} else {
							switch (cell.getCellType()) {// 如果是公式的话，就读取得出的值
							case HSSFCell.CELL_TYPE_FORMULA:
								try {
									value += "'" + String.valueOf(cell.getNumericCellValue()).replaceAll("'", "")
											+ "',";
								} catch (IllegalStateException e) {
									value += "'" + String.valueOf(cell.getRichStringCellValue()).replaceAll("'", "")
											+ "',";
								}
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								// 如果有日期的话，那么就读出日期格式
								// 如果是数字的话，就写出数字格式
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date date2 = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
									String date1 = dff.format(date2);
									value += "'" + date1.replaceAll("'", "") + "',";

								} else {
									value += "'" + (int) cell.getNumericCellValue() + "',";
								}
								break;
							case HSSFCell.CELL_TYPE_STRING:
								String ss = cell.getStringCellValue().replaceAll("'", "");// 如果文本有空值的话，就把它写成null
								if (ss == null || "".equals(ss)) {
									value += "NULL,";
								} else {
									value += "'" + cell.getStringCellValue().replaceAll("'", "") + "',";
									System.out.println(value);
								}
								break;
							default:
								value += "NULL,";
								break;
							}
						}
						if (j == 0) {
						b_id = value.substring(1, value.length() - 2);
						}
					}
					String valueresult = value.substring(0, value.length() - 1);
					map.put(b_id, valueresult);

				}
			}

			wookbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.remove(title);

		// Iterator<String> keys = map.keySet().iterator();
		//
		// while (keys.hasNext()) {
		// String key = (String) keys.next();
		// String value = map.get(key);
		// System.out.println(key + "==::" + value);
		// }

		return map;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> readExcel = readExcel("D://mstarlang.xls", "langcode");
		System.out.println(readExcel);
		Iterator<String> iterator = readExcel.keySet().iterator();
/*		while(iterator.hasNext()) {
			String key = iterator.next();
			String value = readExcel.get(key);
			System.out.println(key + "====" + value);
		}*/
		System.out.println(readExcel.size());
	}
}
