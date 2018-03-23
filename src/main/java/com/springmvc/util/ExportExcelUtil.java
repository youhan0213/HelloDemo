package com.springmvc.util;

import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExportExcelUtil<T> {
	public String[] exportExcelHeader(Class<T> clz) {
		Field fields[] = clz.getDeclaredFields();
		String[] name = new String[fields.length];
		try {
			Field.setAccessible(fields, true);
			for (int i = 1; i < fields.length; i++) {
				name[i] = fields[i].getName();
				System.out.println(name[i] + "-> ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public void exportExcel(Collection<T> dataset, OutputStream out) {
		exportExcel("导出EXCEL文档", null, null, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out) {
		exportExcel("导出EXCEL文档", null, headers, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out) {
		exportExcel(title, null, headers, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String title, String[] headerTitle, String[] headers, Collection<T> dataset,
			OutputStream out) {
		exportExcel(title, headerTitle, headers, dataset, out, "yyyy-MM-dd");
	}

	public void exportExcel(String[] headerTitle, String[] headers, Collection<T> dataset, OutputStream out,
			String pattern) {
		exportExcel("导出EXCEL文档", headerTitle, headers, dataset, out, pattern);
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headerTitle
	 *            表格属性列名数组（标题）若为null，默认显示headers
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public void exportExcel(String title, String[] headerTitle, String[] headers, Collection<T> dataset,
			OutputStream out, String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
//		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
//		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		int sheetCount = 1000;
		if (dataset.size() > sheetCount) {
			Iterator<T> it = dataset.iterator();
			for (int i = 0; i <= 4; i++) {
				int index = 0;
				List<T> list = new ArrayList<T>();
				while (it.hasNext()) {
					index++;
					if (index < sheetCount) {
						list.add(it.next());
					} else {
						break;
					}
				}
				try {
					generateSheet(list, style, style2, workbook, pattern, headerTitle, headers, title + "_" + (i + 1));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				generateSheet(dataset, style, style2, workbook, pattern, headerTitle, headers, title);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void generateSheet(Collection<T> dataset, HSSFCellStyle style, HSSFCellStyle style2, HSSFWorkbook workbook,
			String pattern, String[] headerTitle, String[] headers, String title) throws Exception {
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("author");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		String[] showTitle = headers;
		if (headerTitle != null)
			showTitle = headerTitle;
		int indexTitle = 0;// excel列下标
		for (short i = 0; i < showTitle.length; i++) {
			if (StringUtils.isEmpty(showTitle[i]))
				continue;
			HSSFCell cell = row.createCell(indexTitle);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(showTitle[i]);
			cell.setCellValue(text);
			indexTitle++;
		}
		if (dataset == null)
			return;
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			Class tCls = t.getClass();
			Method[] methods = t.getClass().getMethods();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = new Field[headers.length];

			for (short j = 0; j < headers.length; j++) {
				if (StringUtils.isEmpty(headers[j]))
					continue;
				Field field1 = tCls.getDeclaredField(headers[j]);
				fields[j] = field1;
			}
			int indexText = 0;// excel列下标
			for (short i = 0; i < fields.length; i++) {
				if (fields[i] == null)
					continue;

				Field field = fields[i];
				HSSFCell cell = row.createCell(indexText);

				cell.setCellStyle(style2);

				String fieldName = field.getName();
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, tCls);
				Method getMethod = pd.getReadMethod();
				Object value = getMethod.invoke(t, new Object[] {});
				// 判断值的类型后进行强制类型转换
				String textValue = null;
				if (value instanceof Boolean) {
					boolean bValue = (Boolean) value;
					textValue = "是";
					if (!bValue)
						textValue = "否";
				} else if (value instanceof Date) {
					Date date = (Date) value;
					SimpleDateFormat sdf = new SimpleDateFormat(pattern);
					textValue = sdf.format(date);
				} else if (value instanceof byte[]) {
					// 有图片时，设置行高为60px;
					row.setHeightInPoints(60);
					// 设置图片所在列宽度为80px,注意这里单位的一个换算
					sheet.setColumnWidth(indexText, (short) (35.7 * 80));
					// sheet.autoSizeColumn(i);
					byte[] bsValue = (byte[]) value;
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6, index);
//					anchor.setAnchorType(2);
					patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
				} else {
					// 其它数据类型都当作字符串简单处理
					if (value == null)
						value = "";

					textValue = value.toString();
				}
				// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
				if (textValue != null) {
					Pattern p = Pattern.compile("^//d+(//.//d+)?{1}quot;");
					Matcher matcher = p.matcher(textValue);
					if (matcher.matches()) {
						// 是数字当作double处理
						cell.setCellValue(Double.parseDouble(textValue));
					} else {
						HSSFRichTextString richString = new HSSFRichTextString(textValue);
						HSSFFont font3 = workbook.createFont();
						font3.setColor(HSSFColor.BLACK.index);
						richString.applyFont(font3);
						cell.setCellValue(richString);
					}
				}
				indexText++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ExportExcelUtil<MenuVo> ex = new ExportExcelUtil<MenuVo>();
		String[] headers = ex.exportExcelHeader(MenuVo.class);
		List<MenuVo> list = new ArrayList<MenuVo>();// (List<MenuVo>)
													// hashMap.get("list");

		MenuVo mv1 = new MenuVo();
		mv1.setId(100);
		mv1.setName("m1");
		mv1.setPrivilege(1);
		MenuVo mv2 = new MenuVo();
		mv2.setId(200);
		mv2.setName("m2");
		mv2.setPrivilege(2);
		MenuVo mv3 = new MenuVo();
		mv3.setId(300);
		mv3.setName("m3");
		mv3.setPrivilege(3);
		list.add(mv1);
		list.add(mv2);
		list.add(mv3);
		String[] headerTitle = { "编号", "名称", "权限" };
		String[] headerss = { "id", "name", "privilege" };
		OutputStream out = new FileOutputStream("C://Users//Administrator//Desktop//c.xls");
		// ex.exportExcel(headerss, list, out);
		ex.exportExcel(headerTitle, headerss, list, out, "");
		out.close();
		JOptionPane.showMessageDialog(null, "导出成功!");
		System.out.println("excel导出成功！");
	}

}
