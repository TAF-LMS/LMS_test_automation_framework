package by.bntu.lms.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestData {
    private String testName;
    private String adminLogin;
    private String adminPassword;
    private String login;
    private String password;
    private String groupNumber;
    private String enteringYear;
    private String graduatingYear;
    private String changedGroupNumber;
    private String changedEnteringYear;
    private String changedGraduationYear;
    private String userName;
    private String name;
    private String surname;
    private String patronymic;
    private String changedName;
    private String changedSurname;
    private String changedPatronymic;
    private boolean expectFailure;

}
