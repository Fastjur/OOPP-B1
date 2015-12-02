package server;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class User {
	private String password, firstname, lastname, mail, phonenumber, course, university, gender, nationality, 
			description, location, picture;
	private Birthday birthday;
	private int userID, studyYear;
	private Address address;
	private ArrayList<String> coursesTeachingList, coursesLearningList, buddyList, languageList;
	private ArrayList<AvailableDate> availableList;
	
	/**
	 * User: constructor for class User
	 * @param userID - int
	 * @param password - String
	 * @param firstname - String
	 * @param lastname - String
	 * @param birthday - Birthday
	 * @param mail - String
	 * @param phonenumber - String
	 * @param address - Address
	 * @param course - String
	 * @param university - String
	 * @param studyYear - int
	 * @param gender - String
	 * @param nationality - String
	 * @param description - String
	 * @param location - String
	 * @param picture - String
	 */
	public User(int userID, String password, String firstname, String lastname, Birthday birthday, String mail,
			String phonenumber, Address address, String course, String university, int studyYear, 
			String gender, String nationality, String description, String location, String picture) {
		this.userID = userID;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.mail = mail;
		this.phonenumber = phonenumber;
		this.address = address;
		this.course = course;
		this.university = university;
		this.studyYear = studyYear;
		availableList = new ArrayList<AvailableDate>();
		coursesTeachingList = new ArrayList<String>();
		coursesLearningList = new ArrayList<String>();
		buddyList = new ArrayList<String>();
		this.gender = gender;
		this.nationality = nationality;
		languageList = new ArrayList<String>();
		this.description = description;
		this.location = location;
		this.picture = picture;
	}

    /**Empty constructor to make JUnit test code faster to write
     * and easier on the eyes
     * Author: Sebastiaan Hester
     */
    public User(){
        this.userID = -1;
        this.password = "";
        this.firstname = "";
        this.lastname = "";
        this.birthday = new Birthday(-1,-1,-1);
        this.mail = "";
        this.phonenumber = "";
        this.address = new Address("","","","");
        this.course = "";
        this.university = "";
        this.studyYear = -1;
        availableList = new ArrayList<AvailableDate>();
        coursesTeachingList = new ArrayList<String>();
        coursesLearningList = new ArrayList<String>();
        buddyList = new ArrayList<String>();
        this.gender = "";
        this.nationality = "";
        languageList = new ArrayList<String>();
        this.description = "Test User";
        this.location = "";
        this.picture = "";
    }

    /**jsonToUser: Reads JSON data from a given filepath and returns a new User Object using the jackson library
	 * Note: needs testing
     * Author: Sebastiaan Hester
	 */
    public User jsonToUser(File file){
        ObjectMapper mapper = new ObjectMapper();
        User usr = new User();
        try {
            usr = mapper.readValue(file, User.class);
        }
        catch(IOException io){
            io.printStackTrace();
        }
        return usr;
    }
	/**
	 * toString: gives a textual representation of User
	 * @return text - String
	 */
	public String toString() {
		String text = new String();
		text = Integer.toString(userID) + "\n" + password + "\n" + firstname + " " + lastname + "\n" 
				+ birthday + "\n" + mail + "\n" + phonenumber + "\n" + address.toString() + course 
				+ "\n" + university + "\n" + Integer.toString(studyYear) + "\n" ; 
		// availableList
		text += "available: ";
		for (int i = 0; i < availableList.size(); i++) {
			text += availableList.get(i);
			if (i < availableList.size()-1) {
				text += ";";
			}
		}
		text += "\n";
		
		// coursesTeachingList
		text += "Courses teaching: ";
		for (int i = 0; i < coursesTeachingList.size(); i++) {
			text += coursesTeachingList.get(i);
			if (i < coursesTeachingList.size()-1) {
				text += ";";
			}
		}
		text += "\n";
				
		// coursesLearningList
		text += "Courses learning: ";
		for (int i = 0; i < coursesLearningList.size(); i++) {
			text += coursesLearningList.get(i);
			if (i < coursesLearningList.size()-1) {
				text += ";";
			}
		}
		text += "\n";
		
		// buddieList
		text += "Courses searching Buddy: ";
		for (int i = 0; i < buddyList.size(); i++) {
			text += buddyList.get(i);
			if (i < buddyList.size()-1) {
				text += ";";
			}
		}
		
		text += "\n" + gender + "\n" + nationality + "\n";
		
		// languageList
		text += "languages: ";
		for (int i = 0; i < languageList.size(); i++) {
			text += languageList.get(i);
			if (i < languageList.size()-1) {
				text += ";";
			}
		}
		
		text += "\n" + description + "\n" + location + "\n" + picture;
		return text;
	}
	
	//make getters for the private attributes
	/**
	 * getUserID: getter for private attribute userID
	 * @return userID - int
	 */
	public int getUserID() {
		return userID;
	}
	
	/**
	 * getPasword: getter for private attribute password
	 * @return password - String
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * getFirstname: getter for private attribute firstname 
	 * @return name - String
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * getLastname: getter for private attribute lastname 
	 * @return name - String
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * getBirthday: getter for private attribute birthday
	 * @return birthday - Birthday
	 */
	public Birthday getBirthday() {
		return birthday;
	}
	
	/**
	 * getMail: getter for private attribute mail
	 * @return mail - String
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * getPhonenumber: getter for private attribute phonenumber
	 * @return phonenumber - String
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	
	/**
	 * getAddress: getter for private attribute address
	 * @return address - Address
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * getStudy: getter for private attribute course
	 * @return course - String
	 */
	public String getStudy() {
		return course;
	}
	
	/**
	 * getUniversity: getter for private attribute university
	 * @return university - String
	 */
	public String getUniversity() {
		return university;
	}
	
	/**
	 * getStudyYear: getter for private attribute studyYear
	 * @return studyYear - String
	 */
	public int getStudyYear() {
		return studyYear;
	}
	
	/**
	 * getGender: getter for private attribute gender
	 * @return gender - String
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * getNationality: getter for private attribute nationality
	 * @return nationality - String
	 */
	public String getNationality() {
		return nationality;
	}
	
	/**
	 * getDescription: getter for private attribute description
	 * @return description - String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * getLocation: getter for private attribute location
	 * @return location - String
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * getPicture: getter for private attribute picture
	 * @return picture - String
	 */
	public String getPicture() {
		return picture;
	}
	
	/**
	 * getStudysTeachingSize: getter for the size of the arrayList coursesTeachingList
	 * @return coursesTeachingList.size() - int
	 */
	public int getStudysTeachingSize() {
		 return coursesTeachingList.size();
	 }
	
	/**
	 * getStudysLearningSize: getter for the size of the arrayList coursesLearningList
	 * @return coursesLearningList.size() - int
	 */
	public int getStudysLearningSize() {
		 return coursesLearningList.size();
	 }
	
	/**
	 * getBuddySize: getter for the size of the arrayList buddyList
	 * @return buddyList.size() - int
	 */
	public int getBuddySize() {
		return buddyList.size();
	}
	
	/**
	 * getAvailableListSize: getter for the size of the arrayList availableList
	 * @return availableList.size() - int
	 */
	public int getAvailableSize() {
		return availableList.size();
	}
	
	/**
	 * getLanguageSize: getter for the size of the arrayList languageList
	 * @return languageList.size() - int
	 */
	public int getLanguageSize() {
		return languageList.size();
	}
	
	/**
	 * containsCoursesTeaching: checks if coursesTeaching is already in the arrayList 
	 * @param coursesTeaching - String
	 * @return boolean
	 */
	public boolean containsCoursesTeaching(String coursesTeaching) {
		for (int i = 0; i < coursesTeachingList.size(); i++) {
			if (coursesTeachingList.get(i).equals(coursesTeaching)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * containsCoursesLearning: checks if coursesLearning is already in the arrayList 
	 * @param coursesLearning - String
	 * @return boolean
	 */
	public boolean containsCoursesLearning(String coursesLearning) {
		for (int i = 0; i < coursesLearningList.size(); i++) {
			if (coursesLearningList.get(i).equals(coursesLearning)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * containsBuddy: checks if buddy is already in the arrayList
	 * @param buddy - String
	 * @return boolean
	 */
	public boolean containsBuddy(String buddy) {
		for (int i = 0; i < buddyList.size(); i++) {
			if (buddyList.get(i).equals(buddy)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * containsAvailable: checks if available is already in the arrayList
	 * @param available - AvailableDate
	 * @return boolean
	 */
	public boolean containsAvailable(AvailableDate available) {
		for (int i = 0; i < availableList.size(); i++) {
			if (availableList.get(i).equals(available)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * containsLanguage: checks if language is already in the arrayList
	 * @param language - String
	 * @return boolean
	 */
	public boolean containsLanguage(String language) {
		for (int i = 0; i < languageList.size(); i++) {
			if (languageList.get(i).equals(language)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * addCoursesTeaching: adds coursesTeaching in the arrayList if it isn't already in it
	 * @param coursesTeaching - String
	 */
	public void addCoursesTeaching(String coursesTeaching) {
		if(!this.containsCoursesTeaching(coursesTeaching)) {
			coursesTeachingList.add(coursesTeaching);
		}
	}
	
	/**
	 * addCoursesLearning: adds coursesLearning in the arrayList if it isn't already in it
	 * @param coursesLearning - String
	 */
	public void addCoursesLearning(String coursesLearning) {
		if (!this.containsCoursesLearning(coursesLearning)) {
			coursesLearningList.add(coursesLearning);
		}
	}
	
	/**
	 * addBuddy: adds buddy in the arrayList if it isn't already in it
	 * @param buddy - String
	 */
	public void addBuddy(String buddy) {
		if (!this.containsBuddy(buddy)) {
			buddyList.add(buddy);
		}
	}
	
	/**
	 * addAvailable: adds available in the arrayList if it isn't already in it
	 * @param available - AvailableDate
	 */
	public void addAvailable(AvailableDate available) {
		if (!this.containsAvailable(available)) {
			availableList.add(available);
		}
	}
	
	/**
	 * addLanguage: adds available in the arrayList if it isn't already in it
	 * @param language - String
	 */
	public void addLanguage(String language) {
		if (!this.containsLanguage(language)) {
			languageList.add(language);
		}
	}
	
	/**
	 * setUserID: setter for private attribute userID
	 * @param userID - int
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	/**
	 * setPassword: setter for private attribute password
	 * @param password - String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * setFirstname: setter for private attribute firstname
	 * @param firstname - String
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * setLastName: setter for private attribute lastname
	 * @param lastname - String
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * setBirthday: setter for private attribute birthday
	 * @param birthday - Birthday
	 */
	public void setBirthday(Birthday birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * setMail: setter for private attribute mail
	 * @param mail - String
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * setPhonenumber: setter for private attribute phonenumber
	 * @param phonenumber - String
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	/**
	 * setAddress: setter for private attribute address
	 * @param address - Address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/**
	 * setStudy: setter for private attribute course
	 * @param course - String
	 */
	public void setStudy(String course) {
		this.course = course;
	}
	
	/**
	 * setUniversity: setter for private attribute university
	 * @param university - String
	 */
	public void setUniversity(String university) {
		this.university = university;
	}
	
	/**
	 * setStudyYear: setter for private attribute studyYear
	 * @param studyYear - int
	 */
	public void setStudyYear(int studyYear) {
		this.studyYear = studyYear;
	}
	
	/**
	 * setGender: setter for private attribute gender
	 * @param gender - String
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * setNationality: setter for private attribute nationality
	 * @param nationality - String
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/**
	 * setDescription: setter for private attribute description
	 * @param description - String
	 */
	public void setDescriptionID(String description) {
		this.description = description;
	}
	
	/**
	 * setLocation: setter for private attribute location
	 * @param location - String
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * setPicture: setter for private attribute picture
	 * @param picture - String
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	/**
	 * equals: checks if current object is the same as the other
	 * @param other - Object
	 * @return boolean
	 */
	public boolean equals(Object other) {
		if (other instanceof User) {
			User that = (User) other;
			if (this.userID == that.getUserID() &&
					this.password.equals(that.getPassword()) &&
					this.firstname.equals(that.getFirstname()) &&
					this.lastname.equals(that.getLastname()) &&
					this.birthday.equals(that.getBirthday()) &&
					this.mail.equals(that.getMail()) &&
					this.phonenumber.equals(that.getPhonenumber()) &&
					this.address.equals(that.getAddress()) &&
					this.course.equals(that.getStudy()) &&
					this.university.equals(that.getUniversity()) &&
					this.studyYear == that.getStudyYear() &&	
					this.gender.equals(that.getGender()) &&
					this.nationality.equals(that.getNationality()) &&
					this.description.equals(that.getDescription()) &&
					this.location.equals(that.getLocation()) &&
					this.picture.equals(that.getPicture())) {
				
				if (coursesTeachingList.size() != that.getStudysTeachingSize()) {
					return false;
				}
				for (int i = 0; i < coursesTeachingList.size(); i++) {
					if (!that.containsCoursesTeaching(coursesTeachingList.get(i))) {
						return false;
					}
				} 
				if (coursesLearningList.size() != that.getStudysLearningSize()) {
					return false;
				}
				for (int i = 0; i < coursesLearningList.size(); i++) {
					if (!that.containsCoursesLearning(coursesLearningList.get(i)))
					{
						return false;
					}
				} 
				if (buddyList.size() != that.getBuddySize()) {
					return false;
				}
				for (int i = 0; i < buddyList.size(); i++) {
					if (!that.containsBuddy(buddyList.get(i)))
					{
						return false;
					}
				} 
				if (availableList.size() != that.getAvailableSize()) {
					return false;
				}
				for (int i = 0; i < availableList.size(); i++) {
					if (!that.containsAvailable(availableList.get(i))) {
						return false;
					}
				}
				if (languageList.size() != that.getLanguageSize()) {
					return false;
				}
				for (int i = 0; i < languageList.size(); i++) {
					if (!that.containsLanguage(languageList.get(i)))
					{
						return false;
					}
				} 
				return true;
			}
			return false;
		}
		return false;
	}
}
