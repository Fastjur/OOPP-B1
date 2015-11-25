package server;
import java.util.ArrayList;

public class User {
	private String name, mail, phonenumber, course, university, gender, nationality, 
			description;
	private Date birthday;
	private int userID, experienceInYears;
	private Address address;
	private ArrayList<String> helpOfferedList, helpWantedList;
	private ArrayList<AvailableDate> availableList;
	
	public User(int userID, String name, Date birthday, String mail,
			String phonenumber, Address address, String course, String university, int experienceInYears, 
			String gender, String nationality, String description) {
		this.userID = userID;
		this.name = name;
		this.birthday = birthday;
		this.mail = mail;
		this.phonenumber = phonenumber;
		this.address = address;
		this.course = course;
		this.university = university;
		this.experienceInYears = experienceInYears;
		availableList = new ArrayList<AvailableDate>();
		helpOfferedList = new ArrayList<String>();
		helpWantedList = new ArrayList<String>();
		this.gender = gender;
		this.nationality = nationality;
		this.description = description;
	}

	public User() {
	}


	public String toString() {
		String text = new String();
		text = Integer.toString(userID) + "\n" + name + "\n" + birthday.toString() + "\n" + mail +
				"\n" + phonenumber + "\n" + address.toString() + course + "\n" + university +
				"\n" + Integer.toString(experienceInYears) + "\n";
		// availableList
		text += "available:";
		for (int i = 0; i < availableList.size(); i++) {
			text += availableList.get(i);
			if (i < availableList.size()-1) {
				text += ";";
			}
		}
		text += "\n";
		
		// helpOfferedList
		text += "helpOffered:";
		for (int i = 0; i < helpOfferedList.size(); i++) {
			text += helpOfferedList.get(i);
			if (i < helpOfferedList.size()-1) {
				text += ";";
			}
		}
		text += "\n";
				
		// helpWantedList
		text += "helpWanted:";
		for (int i = 0; i < helpWantedList.size(); i++) {
			text += helpWantedList.get(i);
			if (i < helpWantedList.size()-1) {
				text += ";";
			}
		}
		text += "\n";
		
		text += gender + "\n" + nationality +
				"\n" + description;
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
	 * getName: getter for private attribute name 
	 * @return name - String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getBirthday: getter for private attribute birthday
	 * @return birthday - Date
	 */
	public Date getBirthday() {
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
	 * @return address - String
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * getCourse: getter for private attribute course
	 * @return course - String
	 */
	public String getCourse() {
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
	 * getExperienceInYears: getter for private attribute experienceInYears
	 * @return experienceInYears - String
	 */
	public int getExperienceInYears() {
		return experienceInYears;
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
	 * getHelpOfferedSize: getter for the size of the arrayList helpOfferedList
	 * @return helpOfferdList.size() - int
	 */
	public int getHelpOfferedSize() {
		 return helpOfferedList.size();
	 }
	
	/**
	 * getHelpWantedSize: getter for the size of the arrayList helpWantedList
	 * @return helpWantedList.size() - int
	 */
	public int getHelpWantedSize() {
		 return helpWantedList.size();
	 }
	
	/**
	 * getAvailableListSize: getter for the size of the arrayList timeList
	 * @return availableList.size() - int
	 */
	public int getAvailableSize() {
		return availableList.size();
	}
	
	/**
	 * containsHelpOffered: checks if helpOffered is already in the arrayList 
	 * @param helpOffered - String
	 * @return boolean
	 */
	public boolean containsHelpOffered(String helpOffered) {
		for (int i = 0; i < helpOfferedList.size(); i++) {
			if (helpOfferedList.get(i).equals(helpOffered)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * containsHelpWanted: checks if helpWanted is already in the arrayList 
	 * @param helpWanted - String
	 * @return boolean
	 */
	public boolean containsHelpWanted(String helpWanted) {
		for (int i = 0; i < helpWantedList.size(); i++) {
			if (helpWantedList.get(i).equals(helpWanted)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * containsAvailable: checks if available is alreadu in the arrayList
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
	 * addHelpOffered: adds helpOffered in the arrayList if it isn't already in it
	 * @param helpOffered - String
	 */
	public void addHelpOffered(String helpOffered) {
		if(!this.containsHelpOffered(helpOffered)) {
			helpOfferedList.add(helpOffered);
		}
	}
	
	/**
	 * addHelpWanted: adds helpWanted in the arrayList if it isn't already in it
	 * @param helpWanted - String
	 */
	public void addHelpWanted(String helpWanted) {
		if (!this.containsHelpWanted(helpWanted)) {
			helpWantedList.add(helpWanted);
		}
	}
	
	/**
	 * addAvailable: adds available in the arrayList if it isn't already in it
	 * @param available
	 */
	public void addAvailable(AvailableDate available) {
		if (!this.containsAvailable(available)) {
			availableList.add(available);
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
	 * setName: setter for private attribute name
	 * @param name - String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * setBirthday: setter for private attribute birthday
	 * @param birthday - Date
	 */
	public void setBirthday(Date birthday) {
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
	 * setCourse: setter for private attribute course
	 * @param course - String
	 */
	public void setCourse(String course) {
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
	 * setExperienceInYears: setter for private attribute experienceInYears
	 * @param experienceInYears - int
	 */
	public void setExperienceInYears(int experienceInYears) {
		this.experienceInYears = experienceInYears;
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
	 * equals: checks if current object is the same as the other
	 */
	public boolean equals(Object other) {
		if (other instanceof User) {
			User that = (User) other;
			if (this.userID == that.getUserID() &&
					this.name.equals(that.getName()) &&
					this.birthday.equals(that.getBirthday()) &&
					this.mail.equals(that.getMail()) &&
					this.phonenumber.equals(that.getPhonenumber()) &&
					this.address.equals(that.getAddress()) &&
					this.course.equals(that.getCourse()) &&
					this.university.equals(that.getUniversity()) &&
					this.experienceInYears == that.getExperienceInYears() &&	
					this.gender.equals(that.getGender()) &&
					this.nationality.equals(that.getNationality()) &&
					this.description.equals(that.getDescription())) {
				
				if (helpOfferedList.size() != that.getHelpOfferedSize()) {
					return false;
				}
				for (int i = 0; i < helpOfferedList.size(); i++) {
					if (!that.containsHelpOffered(helpOfferedList.get(i))) {
						return false;
					}
				} 
				if (helpWantedList.size() != that.getHelpWantedSize()) {
					return false;
				}
				for (int i = 0; i < helpWantedList.size(); i++) {
					if (!that.containsHelpWanted(helpWantedList.get(i)))
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
				return true;
			}
			return false;
		}
		return false;
	}
	
	
}
