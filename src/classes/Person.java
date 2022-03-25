package classes;

import enums.Gender;

import java.time.LocalDate;

public class Person {
    private String fName;
    private String lName;
    private String phoneNum;
    private LocalDate birthday;
    private Gender gender;

    public Person(String fName, String lName, String phoneNum, LocalDate birthday, Gender gender) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
        this.gender = gender;
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

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public Gender getGender() {
        return gender;
    }

    // Other

    @Override
    public String toString() {
        return this.fName + " " + this.lName;
    }
}
