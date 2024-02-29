package com.zl.poi;

public class TestPoi {
    public static void main(String[] args) {
//        File file = new File("F:\\淘宝\\数据统计\\关键词数据统计.xlsx");
//        try {
//            InputStream in = Files.newInputStream(file.toPath());
//            XSSFWorkbook book = new XSSFWorkbook(in);
//            XSSFSheet sheet = book.getSheetAt(0);
//            int lastRowNum = sheet.getLastRowNum();
//            for(int i =1; i < lastRowNum; i++){
//                XSSFRow row = sheet.getRow(i);
//                short firstCellNum = row.getFirstCellNum();
//                short lastCellNum = row.getLastCellNum();
//                for(int j =firstCellNum; j <=lastCellNum-1; j ++){
//                    XSSFCell cell = row.getCell(j);
//                    if( cell != null) {
//                        CellType cellType = cell.getCellType();
//                        if(cellType.equals(CellType.FORMULA)){
//                            String rawValue = cell.getRawValue();
//                            System.out.print(rawValue + "\t");
//                        }else if(cellType.equals(CellType.NUMERIC)){
//                            if(DateUtil.isCellDateFormatted(cell)){
//                                Date dateCellValue = cell.getDateCellValue();
//                                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//                                String format = sdf.format(dateCellValue);
//                                System.out.print(format + "\t");
//                            }else {
//                                double numericCellValue = cell.getNumericCellValue();
//                                System.out.print(numericCellValue + "\t");
//                            }
//                        }else if(cellType.equals(CellType.STRING)){
//                            String stringCellValue = cell.getStringCellValue();
//                            System.out.print(stringCellValue + "\t");
//                        }else {
//                            System.out.print(cellType);
//                        }
//                    }else{
//                        System.out.print("null\t");
//                    }
//                }
//                System.out.println();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}
