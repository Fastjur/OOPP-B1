package server;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private String password, firstname, lastname, mail, phonenumber, study, university, gender, nationality,
            description, location;
    private Date birthday;
    private int userID, studyYear;
    private Address address;
    private ArrayList<String> coursesTeachingList;
    private ArrayList<String> coursesLearningList;
    private ArrayList<String> buddyList;
    private ArrayList<String> languageList;
    private ArrayList<AvailableDate> availableList;

    /**
     * User: constructor for class User
     *
     * @param userID      - int
     * @param password    - String
     * @param firstname   - String
     * @param lastname    - String
     * @param birthday    - Date
     * @param mail        - String
     * @param phonenumber - String
     * @param address     - Address
     * @param study       - String
     * @param university  - String
     * @param studyYear   - int
     * @param gender      - String
     * @param nationality - String
     * @param description - String
     * @param location    - String
     */
    public User(int userID, String password, String firstname, String lastname, Date birthday, String mail,
                String phonenumber, Address address, String study, String university, int studyYear,
                ArrayList<AvailableDate> available, ArrayList<String> teaching, ArrayList<String> learning, ArrayList<String> buddys, String gender,
                String nationality, String description, String location) {
        this.userID = userID;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.mail = mail;
        this.phonenumber = phonenumber;
        this.address = address;
        this.study = study;
        this.university = university;
        this.studyYear = studyYear;
        this.availableList = available;
        this.coursesTeachingList = teaching;
        this.coursesLearningList = learning;
        this.buddyList = buddys;
        this.gender = gender;
        this.nationality = nationality;
        this.languageList = new ArrayList<>();
        this.description = description;
        this.location = location;
    }

    /**
     * Empty constructor to make JUnit test code faster to write
     * and easier on the eyes
     * Author: Sebastiaan Hester
     */
    public User() {
        this.userID = -1;
        this.password = "";
        this.firstname = "";
        this.lastname = "";
        this.birthday = new Date(0);
        this.mail = "";
        this.phonenumber = "";
        this.address = new Address("", "", "", "");
        this.study = "";
        this.university = "";
        this.studyYear = -1;
        this.availableList = new ArrayList<>();
        this.coursesTeachingList = new ArrayList<>();
        this.coursesLearningList = new ArrayList<>();
        this.buddyList = new ArrayList<>();
        this.gender = "";
        this.nationality = "";
        this.languageList = new ArrayList<>();
        this.description = "Test User";
        this.location = "";
    }

    /**
     * jsonToUser: Reads JSON data from a given filepath and returns a new User Object using the jackson library
     * Note: needs testing
     * Author: Sebastiaan Hester
     */
    public static User jsonToUser(String jsonstring) {
        ObjectMapper mapper = new ObjectMapper();
        User usr = new User();
        try {
            usr = mapper.readValue(jsonstring, User.class);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return usr;
    }

    /**
     * toString: gives a textual representation of User
     *
     * @return text - String
     */
    public String toString() {
        String text;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        text = Integer.toString(userID) + "\n" + password + "\n" + firstname + " " + lastname + "\n"
                + df.format(birthday) + "\n" + mail + "\n" + phonenumber + "\n" + address.toString() + study
                + "\n" + university + "\n" + Integer.toString(studyYear) + "\n";
        // availableList
        text += "available: ";
        for (int i = 0; i < availableList.size(); i++) {
            text += availableList.get(i);
            if (i < availableList.size() - 1) {
                text += ";";
            }
        }
        text += "\n";

        // coursesTeachingList
        text += "Courses teaching: ";
        for (int i = 0; i < coursesTeachingList.size(); i++) {
            text += coursesTeachingList.get(i);
            if (i < coursesTeachingList.size() - 1) {
                text += ";";
            }
        }
        text += "\n";

        // coursesLearningList
        text += "Courses learning: ";
        for (int i = 0; i < coursesLearningList.size(); i++) {
            text += coursesLearningList.get(i);
            if (i < coursesLearningList.size() - 1) {
                text += ";";
            }
        }
        text += "\n";

        // buddieList
        text += "Courses searching Buddy: ";
        for (int i = 0; i < buddyList.size(); i++) {
            text += buddyList.get(i);
            if (i < buddyList.size() - 1) {
                text += ";";
            }
        }

        text += "\n" + gender + "\n" + nationality + "\n";

        // languageList
        text += "languages: ";
        for (int i = 0; i < languageList.size(); i++) {
            text += languageList.get(i);
            if (i < languageList.size() - 1) {
                text += ";";
            }
        }

        text += "\n" + description + "\n" + location;
        return text;
    }

    //make getters for the private attributes

    /**
     * getUserID: getter for private attribute userID
     *
     * @return userID - int
     */
    public int getUserID() {
        return userID;
    }

    /**
     * getPasword: getter for private attribute password
     *
     * @return password - String
     */
    public String getPassword() {
        return password;
    }

    /**
     * getFirstname: getter for private attribute firstname
     *
     * @return name - String
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * getLastname: getter for private attribute lastname
     *
     * @return name - String
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * getBirthday: getter for private attribute birthday
     *
     * @return birthday - Date
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * getMail: getter for private attribute mail
     *
     * @return mail - String
     */
    public String getMail() {
        return mail;
    }

    /**
     * getPhonenumber: getter for private attribute phonenumber
     *
     * @return phonenumber - String
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * getAddress: getter for private attribute address
     *
     * @return address - Address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * getStudy: getter for private attribute study
     *
     * @return study - String
     */
    public String getStudy() {
        return study;
    }

    /**
     * getUniversity: getter for private attribute university
     *
     * @return university - String
     */
    public String getUniversity() {
        return university;
    }

    /**
     * getStudyYear: getter for private attribute studyYear
     *
     * @return studyYear - String
     */
    public int getStudyYear() {
        return studyYear;
    }

    /**
     * getGender: getter for private attribute gender
     *
     * @return gender - String
     */
    public String getGender() {
        return gender;
    }

    /**
     * getNationality: getter for private attribute nationality
     *
     * @return nationality - String
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * getDescription: getter for private attribute description
     *
     * @return description - String
     */
    public String getDescription() {
        return description;
    }

    /**
     * getLocation: getter for private attribute location
     *
     * @return location - String
     */
    public String getLocation() {
        return location;
    }

    /**
     * getCoursesTeachingList: getter for private arraylist coursesteaching
     * @return CoursesTeaching - ArrayList<String>
     */
    public ArrayList<String> getCoursesTeachingList() {
        return this.coursesTeachingList;
    }

    /**
     * getCoursesLearningList: getter for private arraylist courseslearning
     * @return CoursesLearning - ArrayList<String>
     */
    public ArrayList<String> getCoursesLearningList() {
        return this.coursesLearningList;
    }

    /**
     * getBuddyList: getter for private arraylist buddylist
     * @return BuddyList - ArrayList<String>
     */
    public ArrayList<String> getBuddyList() {
        return this.buddyList;
    }

    /**
     * getLanguageList: getter for private arraylist languagelist
     * @return LanguageList - ArrayList<String>
     */
    public ArrayList<String> getLanguageList() {
        return this.languageList;
    }

    /**
     * getAvailableList: getter for private arraylist availablelist
     * @return AvailableList - ArrayList<AvaliableDate>
     */
    public ArrayList<AvailableDate> getAvailableList() {
        return this.availableList;
    }

    /**
     * setUserID: setter for private attribute userID
     *
     * @param userID - int
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * setPassword: setter for private attribute password
     *
     * @param password - String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * setFirstname: setter for private attribute firstname
     *
     * @param firstname - String
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * setLastName: setter for private attribute lastname
     *
     * @param lastname - String
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * setBirthday: setter for private attribute birthday
     *
     * @param birthday - Date
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * setMail: setter for private attribute mail
     *
     * @param mail - String
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * setPhonenumber: setter for private attribute phonenumber
     *
     * @param phonenumber - String
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * setAddress: setter for private attribute address
     *
     * @param address - Address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * setStudy: setter for private attribute study
     *
     * @param study - String
     */
    public void setStudy(String study) {
        this.study = study;
    }

    /**
     * setUniversity: setter for private attribute university
     *
     * @param university - String
     */
    public void setUniversity(String university) {
        this.university = university;
    }

    /**
     * setStudyYear: setter for private attribute studyYear
     *
     * @param studyYear - int
     */
    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    /**
     * setGender: setter for private attribute gender
     *
     * @param gender - String
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * setNationality: setter for private attribute nationality
     *
     * @param nationality - String
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * setDescription: setter for private attribute description
     *
     * @param description - String
     */
    public void setDescriptionID(String description) {
        this.description = description;
    }

    /**
     * setLocation: setter for private attribute location
     *
     * @param location - String
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * equals: checks if current object is the same as the other
     *
     * @param other - Object
     * @return boolean
     */
    public boolean equals(Object other) {
        if (other instanceof User) {
            User that = (User) other;
            return this.userID == that.getUserID() &&
                    this.password.equals(that.getPassword()) &&
                    this.firstname.equals(that.getFirstname()) &&
                    this.lastname.equals(that.getLastname()) &&
                    this.birthday.equals(that.getBirthday()) &&
                    this.mail.equals(that.getMail()) &&
                    this.phonenumber.equals(that.getPhonenumber()) &&
                    this.address.equals(that.getAddress()) &&
                    this.study.equals(that.getStudy()) &&
                    this.university.equals(that.getUniversity()) &&
                    this.studyYear == that.getStudyYear() &&
                    this.gender.equals(that.getGender()) &&
                    this.nationality.equals(that.getNationality()) &&
                    this.description.equals(that.getDescription()) &&
                    this.location.equals(that.getLocation()) &&
                    this.coursesLearningList.equals(that.getCoursesLearningList()) &&
                    this.coursesTeachingList.equals(that.getCoursesTeachingList()) &&
                    this.buddyList.equals(that.getBuddyList()) &&
                    this.languageList.equals(that.getLanguageList()) &&
                    this.availableList.equals(that.getAvailableList());
        }
        return false;
    }
}
