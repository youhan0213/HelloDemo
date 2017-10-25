package com.springmvc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.fabric.xmlrpc.base.Array;

public class ExcelUtil {

	public static Map<String,Map<String,String>> readXls(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		return readXls(is);
	}
	public static Map<String,Map<String,String>> readXls(InputStream is) throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		return readXls(hssfWorkbook);
	}
	private static Map<String,Map<String,String>> readXls(HSSFWorkbook hssfWorkbook) {
		// 循环工作表Sheet
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		Map<String,Map<String,String>> result = new LinkedHashMap<String,Map<String,String>>(hssfSheet.getLastRowNum());
		// 循环行Row
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (hssfRow == null) {
				continue;
			}

			// 循环列Cell
			for (int cellNum = 1; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
				HSSFCell hssfCell = hssfRow.getCell(cellNum);
				if (hssfCell == null || StringUtils.isBlank(getValue(hssfCell))) {
					continue;
				}
				//option
				HSSFCell option = hssfRow.getCell(0);
				//无效数据跳过
				if(option == null){
					continue;
				}
				String option_key = getValue(option);
				Map<String, String> option_content_map = result.get(option_key);
				if(option_content_map == null){
					option_content_map = new LinkedHashMap<>(hssfRow.getLastCellNum());
					result.put(option_key, option_content_map);
				}
				//option_content 信息
				HSSFCell tag = hssfSheet.getRow(0).getCell(cellNum);
				//无效数据跳过
				if(tag == null){
					continue;
				}
				//eg: key-value  =  zh - 许可信息
				option_content_map.put(getValue(tag), getValue(hssfCell));
			}
		}
		return result;
	}

	public static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	/**
	 * 入口
	 * @param book
	 * @return
	 * @throws IOException
	 */
	public static Map<String,Map<String,String>> read(Object book) throws IOException {
		if(book instanceof XSSFWorkbook){
			return readXlsx((XSSFWorkbook)book);
		}else if(book instanceof HSSFWorkbook){
			return readXls((HSSFWorkbook)book);
		}
		throw new RuntimeException("类型不对");
	}
	// xlsx
	public static Map<String,Map<String,String>> readXlsx(String path) throws IOException {
		String fileName = path;
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileName);
		return readXlsx(xssfWorkbook);
	}
	public static Map<String,Map<String,String>> readXlsx(InputStream is) throws IOException {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		return readXlsx(xssfWorkbook);
	}
	private static Map<String, Map<String, String>> readXlsx(XSSFWorkbook xssfWorkbook) {
		// 循环工作表Sheet
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		
		Map<String,Map<String,String>> result = new LinkedHashMap<String,Map<String,String>>(xssfSheet.getLastRowNum()+1);
		XSSFRow firstRow = xssfSheet.getRow(0);
		int lastcellnum = 0;
		for (int cellNum = 0; cellNum <= firstRow.getLastCellNum(); cellNum++) {
			XSSFCell xssfCell = firstRow.getCell(cellNum);
			if (xssfCell == null || StringUtils.isBlank(getsValue(xssfCell))) {
				break ;
			}
			lastcellnum++;
		}
		// 循环行Row
		for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			if (xssfRow == null) {
				continue;
			}
			// 循环列Cell
			for (int cellNum = 1; cellNum <= lastcellnum; cellNum++) {
				XSSFCell xssfCell = xssfRow.getCell(cellNum);
				//option
				XSSFCell option = xssfRow.getCell(0);
				//无效数据跳过
				if(option == null || StringUtils.isBlank(getsValue(option))){
					continue;
				}
				String option_key = getsValue(option);
				Map<String, String> option_content_map = result.get(option_key);
				if(option_content_map == null){
					option_content_map = new LinkedHashMap<>(xssfRow.getLastCellNum());
					result.put(option_key, option_content_map);
				}
				//option_content 信息
				XSSFCell tag = xssfSheet.getRow(0).getCell(cellNum);
				//无效数据跳过
				if(tag == null){
					continue;
				}
				//eg: key-value  =  zh - 许可信息
				option_content_map.put(getsValue(tag).toLowerCase(), xssfCell == null ? null : getsValue(xssfCell));
			}
		}
		return result;
	}

	public static String getsValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfCell.getNumericCellValue());
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}
	/**
	 * 将string 转换成带有<p>标签
	 * @param str
	 * @return
	 */
	public static String formatHtml(String str){
		if(StringUtils.isBlank(str)){
			return str;
		}
		String[] split = str.split("\\n");
		StringBuilder stringBuilder = new StringBuilder();
		if(split.length > 0){
			for (String e : split) {
				if(StringUtils.isBlank(e)){
					e = "&nbsp;";
				}
				stringBuilder.append("<p>").append(e).append("</p>");
			}
		}else{
			return str;
		}
		return stringBuilder.toString();
	}
	public static void main(String[] args) throws IOException {
		Map<String, Map<String, String>> readXlsx = readXlsx("D://mstarlang.xls");
		/*String header = "SmartTV ToU_wk1402.4 [header_text]";
		String regex = "^SmartTV ToU_wk1402.4 \\[\\d+.*";
		String langCode = "ar";
		String result = null ;
		for (String key : readXlsx.keySet()) {
			if(key.equals(header) || Pattern.matches(regex, key)){
				Map<String, String> map = readXlsx.get(key);
				String string = map.get(langCode);
				if(result == null){
					result = string;
				}else{
					if(!result.endsWith("\n")){
						result += "\n";
					}
					result += "\n"+string;
				}
			}
		}*/
		System.out.println(readXlsx.toString());
	}
}
