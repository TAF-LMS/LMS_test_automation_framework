package by.bntu.lms.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestData {
    private String testName;

    //Student
    private String studentLogin;
    private String studentPassword;
    private String studentName;
    private String studentSurname;
    private String studentPatronymic;
    private String studentGroupNumber;
    private String changedStudentPassword;
    private String changedStudentName;
    private String changedStudentSurname;
    private String changedStudentPatronymic;
    private String changedStudentGroupNumber;

    //Professor
    private String professorLogin;
    private String professorPassword;
    private String professorName;
    private String professorSurname;
    private String professorPatronymic;
    private String changedProfessorName;
    private String changedProfessorSurname;
    private String changedProfessorPatronymic;

    //Group
    private String groupNumber;
    private String groupEnteringYear;
    private String groupGraduatingYear;
    private String changedGroupNumber;
    private String changedGroupEnteringYear;
    private String changedGroupGraduationYear;

    //Add subjects
    private String subjectName;
    private String subjectAbbreviation;
    private String subjectGroups;

    //Error handling
    private String errorMessage;

}
