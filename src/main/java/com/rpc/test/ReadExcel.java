package com.rpc.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rpc.test.bean.AppStarBean;

public class ReadExcel {

    private static final String EXCEL_PATH = "D:\\readExcel.xls";

    /**
     * 直接抽取excel中的数据
     */
    public static void extract(String path) {
        InputStream inp = null;
        Workbook workbook = null;
        ExcelExtractor extractor = null;
        XSSFExcelExtractor xssfExtractor = null;
        String text = "";
        try {
            inp = new FileInputStream(path);
            workbook = WorkbookFactory.create(inp);
            if (workbook instanceof HSSFWorkbook) {
                extractor = new ExcelExtractor((HSSFWorkbook) workbook);
                extractor.setFormulasNotResults(true);
                extractor.setIncludeSheetNames(false);
                text = extractor.getText();
            } else if (workbook instanceof XSSFWorkbook) {
                xssfExtractor = new XSSFExcelExtractor((XSSFWorkbook) workbook);
                xssfExtractor.setFormulasNotResults(true);
                xssfExtractor.setIncludeSheetNames(false);
                text = xssfExtractor.getText();
            } else {
                return;
            }
            System.out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (extractor != null) {
                try {
                    extractor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (xssfExtractor != null) {
                try {
                    xssfExtractor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inp != null) {
                try {
                    inp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 原样返回数值单元格的内容
     */
    public static String formatNumericCell(Double value, Cell cell) {
        if(cell.getCellTypeEnum() != CellType.NUMERIC && cell.getCellTypeEnum() != CellType.FORMULA) {
            return null;
        }
        //isCellDateFormatted判断该单元格是"时间格式"或者该"单元格的公式算出来的是时间格式"
        if(DateUtil.isCellDateFormatted(cell)) {
            //cell.getDateCellValue()碰到单元格是公式,会自动计算出Date结果
            Date date = cell.getDateCellValue();
            DataFormatter dataFormatter = new DataFormatter();
            Format format = dataFormatter.createFormat(cell);
            return format.format(date);
        } else {
            DataFormatter dataFormatter = new DataFormatter();
            Format format = dataFormatter.createFormat(cell);
            return format.format(value);

        }
    }

    /*
     * 通过对单元格遍历的形式来获取信息 ，这里要判断单元格的类型才可以取出值
     */
    /**
     * @param file
     * @return
     */
    public static Map<String,Object> readWorkbook(File file) {
    	Map<String,Object> map = new HashMap<>();
    	List<AppStarBean> list = new ArrayList<AppStarBean>();
        InputStream inp = null;
        Workbook workbook = null;
        try {
//            inp = new FileInputStream(path);
        	inp = new FileInputStream(file);
            workbook = WorkbookFactory.create(inp);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            // for/in是iterator的简写, 最终会被编译器编译为iterator
            // for(Iterator<Sheet> iterator=workbook.iterator(); iterator.hasNext();) {
            // Sheet sheet = iterator.next();
            int count = 0;
            boolean b = true;
            for (Sheet sheet : workbook) {
                System.out.println("----------" + sheet.getSheetName() + "----------");
                for (Row row : sheet) {
                	if(count == 0) {
                		count++;
                		Cell cell = row.getCell(0);
                		String value = cell.getStringCellValue();
                		System.out.println("第一列表头 :" + value);
                		Cell c = row.getCell(1);
                		String v = c.getStringCellValue();
                		System.out.println("第二列表头： " + v);
                		if(!value.equalsIgnoreCase("appid") || ! v.equalsIgnoreCase("stars")) {
                			map.put("error", 1);
                			map.put("message", "excel 格式不符！");
                			return map;
                		}
                		continue;
                	}
                	AppStarBean asb = new AppStarBean();
                    for (Cell cell : row) {
                        switch (cell.getCellTypeEnum()) {
                        case _NONE:
                            System.out.print("_NONE" + "\t");
                            break;
                        case BLANK:
                            System.out.print("BLANK" + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case ERROR:
                            System.out.print("ERROR(" + cell.getErrorCellValue() + ")" + "\t");
                            break;
                        case FORMULA:
                            // 会打印出原本单元格的公式
                            // System.out.print(cell.getCellFormula() + "\t");
                            // NumberFormat nf = new DecimalFormat("#.#");
                            // String value = nf.format(cell.getNumericCellValue());
                            CellValue cellValue = evaluator.evaluate(cell);
                            switch (cellValue.getCellTypeEnum()) {
                            case _NONE:
                                System.out.print("_NONE" + "\t");
                                break;
                            case BLANK:
                                System.out.print("BLANK" + "\t");
                                break;
                            case BOOLEAN:
                                System.out.print(cellValue.getBooleanValue() + "\t");
                                break;
                            case ERROR:
                                System.out.print("ERROR(" + cellValue.getErrorValue() + ")" + "\t");
                                break;
                            case NUMERIC:
                                System.out.print(formatNumericCell(cellValue.getNumberValue(), cell) + "\t");
                                break;
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "\t");
                                // System.out.print(cell.getRichStringCellValue() + "\t");
                                break;
                            default:
                                break;
                            }
                            break;
                        case NUMERIC:
                            System.out.print(formatNumericCell(cell.getNumericCellValue(), cell) + "\t");
                            if(b) {
                            	asb.setAppId(Integer.parseInt(formatNumericCell(cell.getNumericCellValue(), cell)));
                            	b = false;
                            }else {
                            	asb.setStars(Integer.parseInt(formatNumericCell(cell.getNumericCellValue(), cell)));
                            	b = true;
                            }
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            // System.out.print(cell.getRichStringCellValue() + "\t");
                            break;
                        }
                    }
                    list.add(asb);
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inp != null) {
                try {
                    inp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        map.put("error", 0);
        map.put("data", list);
		return map;
    }

    public static void main(String[] args) {
//        extract(EXCEL_PATH);
//        readWorkbook(EXCEL_PATH);
    }

}