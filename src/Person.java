import java.time.LocalDate;

public class Person {
    private String fName;
    private String lName;
    private String phoneNum;
    private LocalDate birthday;

    public Person(String fName, String lName, String phoneNum, LocalDate birthday) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
    }

    // Setters

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPhoneNum(String lName) {
        this.phoneNum = phoneNum;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    // Getters

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    // Other

    @Override
    public String toString() {
        return this.fName + " " + this.lName;
    }
}
