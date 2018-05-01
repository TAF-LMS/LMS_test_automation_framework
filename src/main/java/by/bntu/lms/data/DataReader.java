package by.bntu.lms.data;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class DataReader {

    public static Object[][] dataReaderGeneric(String excelFile, String excelSheet) throws IOException {

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
                    //Student
                    case "studentlogin":
                        testRow.setStudentLogin(value);
                        break;
                    case "studentpassword":
                        testRow.setStudentPassword(value);
                        break;
                    case "changedstudentpassword":
                        testRow.setChangedStudentPassword(value);
                        break;
                    case "studentname":
                        testRow.setStudentName(value);
                        break;
                    case "studentsurname":
                        testRow.setStudentSurname(value);
                        break;
                    case "studentpatronymic":
                        testRow.setStudentPatronymic(value);
                        break;
                    case "studentgroupnumber":
                        testRow.setStudentGroupNumber(value);
                    case "changedstudentname":
                        testRow.setChangedStudentName(value);
                        break;
                    case "changedstudentsurname":
                        testRow.setChangedStudentSurname(value);
                        break;
                    case "changedstudentpatronymic":
                        testRow.setChangedStudentPatronymic(value);
                        break;
                    case "changedstudentgroupnumber":
                        testRow.setChangedStudentPatronymic(value);
                        //Professor
                    case "professorlogin":
                        testRow.setProfessorLogin(value);
                        break;
                    case "professorpassword":
                        testRow.setProfessorPassword(value);
                        break;
                    case "professorsurname":
                        testRow.setProfessorSurname(value);
                        break;
                    case "professorname":
                        testRow.setProfessorName(value);
                        break;
                    case "professorpatronymic":
                        testRow.setProfessorPatronymic(value);
                        break;
                    case "changedprofessorname":
                        testRow.setChangedProfessorName(value);
                        break;
                    case "changedprofessorsurname":
                        testRow.setChangedProfessorSurname(value);
                        break;
                    case "changedprofessorpatronymic":
                        testRow.setChangedProfessorPatronymic(value);
                        break;
                    //Group
                    case "groupnumber":
                        testRow.setGroupNumber(value);
                        break;
                    case "groupenteringyear":
                        testRow.setGroupEnteringYear(value);
                        break;
                    case "groupgraduatingyear":
                        testRow.setGroupGraduatingYear(value);
                        break;
                    case "changedgroupnumber":
                        testRow.setChangedGroupNumber(value);
                        break;
                    case "changedgroupenteringyear":
                        testRow.setChangedGroupEnteringYear(value);
                        break;
                    case "changedgroupgraduationyear":
                        testRow.setChangedGroupGraduationYear(value);
                        break;
                        //Add subject
                    case "subjectname":
                        testRow.setSubjectName(value);
                        break;
                    case "subjectabbreviation":
                        testRow.setSubjectAbbreviation(value);
                        break;
                    case "subjectgroups":
                        testRow.setSubjectGroups(value);
                        break;
                        default:
                        // log.info("Unsupported field in test data; field name:" + key + " value:" + value);
                }
            }
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
                value.equalsIgnoreCase("TRUE");
    }


}
