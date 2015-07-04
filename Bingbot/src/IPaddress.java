import java.util.*;
public class IPaddress {
	private String IP = "";
	private String port = "";
	private String country = "";
	public ArrayList<Account> accounts = new ArrayList<Account>();
	
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
	
	public void useProxy(){
		
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
