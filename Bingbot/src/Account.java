
public class Account {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	public static final String PHONE_NUM = "425-922-8125";
	private int credits;
	
	public Account(String firstName, String lastName, String password, int credits){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = InfoGenerator.makeEmail(firstName, lastName);
		this.password = password;
		this.credits = credits;
	}
	/**
	 * @return the username
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param username the username to set
	 */
	public void setEmail(String username) {
		this.email = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the creditsFromToday
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * @param creditsFromToday the creditsFromToday to set
	 */
	public void incrementCredits() {
		credits++;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString(){
		return getFirstName() + "-" + getLastName() + "-" + getPassword() + "-" + getCredits();
	}
}
