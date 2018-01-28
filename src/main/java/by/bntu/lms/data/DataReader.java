package by.bntu.lms.data;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    protected static final String ADMIN_LOGIN = "admin";
    protected static final String ADMIN_PASSWORD = "123456";

    public static void readFromExcel(String file) throws IOException {
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheet("UsersData");
        HSSFRow row = myExcelSheet.getRow(0);
        if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
            String name = row.getCell(0).getStringCellValue();
            System.out.println("name : " + name);
        }

        if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            String birthdate = row.getCell(1).getStringCellValue();
            System.out.println("birthdate :" + birthdate);
        }

        myExcelBook.close();
    }

    public static Object[][] dataReaderGeneric(String excelFile, String excelSheet) {
        List<HashMap<String, String>> rawData = readTestDataList(excelSheet, excelFile);
        List<Object> testData = new ArrayList<>();
        int i = 1;
        for (HashMap<String, String> row : rawData) {
            i++;
            TestData testRow = new TestData();
            // set test case name
            testRow.setTestName("Sheet: " + excelSheet + " row: " + i);

            for (String key : row.keySet()) {
                String value = row.get(key);

                switch (key.toLowerCase()) {
                 /*   case "testdescription":
                        testRow.setTestDescription(value);
                        break;*/
                    case "adminlogin":
                        testRow.setAdminLogin(value);
                        break;
                    case "adminpassword":
                        testRow.setAdminPassword(value);
                        break;
                    case "login":
                        testRow.setLogin(value);
                        break;
                    case "password":
                        testRow.setPassword(value);
                        break;
                    case "groupnumber":
                        testRow.setGroupNumber(value);
                        break;
                    case "enteringyear":
                        testRow.setEnteringYear(value);
                        break;
                    case "graduatingyear":
                        testRow.setGraduatingYear(value);
                        break;
                    case "changedgroupnumber":
                        testRow.setChangedGroupNumber(value);
                        break;
                    case "changedenteringyear":
                        testRow.setChangedEnteringYear(value);
                        break;
                    case "changedgraduationyear":
                        testRow.setChangedGraduationYear(value);
                        break;
                    case "username":
                        testRow.setUserName(value);
                        break;
                    case "surname":
                        testRow.setSurname(value);
                        break;
                    case "name":
                        testRow.setName(value);
                        break;
                    case "patronymic":
                        testRow.setPatronymic(value);
                        break;
                    case "changedname":
                        testRow.setChangedName(value);
                        break;
                    case "changedsurname":
                        testRow.setChangedSurname(value);
                        break;
                    case "changedpatronymic":
                        testRow.setChangedPatronymic(value);
                        break;
                    case "expectfailure":
                        testRow.setExpectFailure(convertToBoolean(value));
                        break;
                    default:
                        // log.info("Unsupported field in test data; field name:" + key + " value:" + value);
                }
            }
            testRow.setAdminLogin(ADMIN_LOGIN);
            testRow.setAdminPassword(ADMIN_PASSWORD);
            testData.add(testRow);
        }

        // convert data into Object[][]
        Object[][] testNgData = new Object[testData.size()][1];
        for (i = 0; i < testData.size(); i++) {
            testNgData[i][0] = testData.get(i);
        }
        return testNgData;

    }

    private static List<HashMap<String, String>> readTestDataList(String dataSheetName, String xlsFileName) {
        try {
            String e = "./" + xlsFileName;
            // log.info("Reading from: " + xlsFileName + " Sheet: " + dataSheetName);
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(e)));
            HSSFSheet suiteSheet = workbook.getSheet(dataSheetName);
            int rowSuiteAmount = suiteSheet.getPhysicalNumberOfRows();
            HSSFRow suiteRow;
            HSSFCell suiteCell;
            HSSFCell keyCell;
            HSSFRow firstRow = suiteSheet.getRow(0);
            ArrayList resultList = new ArrayList();

            for (int k = 1; k < rowSuiteAmount; ++k) {
                suiteRow = suiteSheet.getRow(k);
                int cellAmount = suiteRow.getLastCellNum();
                HashMap dataMap = new HashMap();

                for (int n = 0; n < cellAmount; ++n) {
                    suiteCell = suiteRow.getCell(n);
                    keyCell = firstRow.getCell(n);
                    if (suiteCell != null) {
                        suiteCell.setCellType(1);
                        if (!(keyCell == null)) {
                            String cellString = suiteCell.getStringCellValue().trim();
                            keyCell.setCellType(1);
                            String keyString = keyCell.getStringCellValue().trim();
                            dataMap.put(keyString, cellString);
                        }
                    }
                }

                resultList.add(dataMap);
            }

            return resultList;
        } catch (Exception e) {
            // log.error("Problems at reading Excel sheet: " + dataSheetName + " from file:" + xlsFileName + "\n" +
            //          e.getMessage());
            return null;
        }
    }

    private static boolean convertToBoolean(String value) {
        return value.equalsIgnoreCase("YES") ||
                value.equalsIgnoreCase("TRUE") ? true : false;
    }

}
