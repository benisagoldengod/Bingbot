import java.util.*;
public class IPaddress {
	private String IP = "";
	private String port = "";
	private String country = "";
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	public IPaddress(String address, String port, String country){
		IP = address;
		this.country = country;
		this.port = port;
		for(int i = 0; i < 5; i++){
			accounts.add(InfoGenerator.getNewAccount());
		}
	}
	
	public IPaddress(String address, String port, String country, ArrayList<Account> accounts){
		IP = address;
		this.country = country;
		this.port = port;
		this.accounts = accounts;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getIP() {
		return IP;
	}
	
	@Override
	public String toString(){
		String out = IP + "::" + port + "::" + country;
		for(Account a : accounts){
			out += "::" + a.toString();
		}
		return out;
	}
}
