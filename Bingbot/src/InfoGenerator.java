import java.util.*;
import java.io.*;
public class InfoGenerator{
	
	public static ArrayList<String> names = new ArrayList<String>();
	public static Random r = new Random();
	public static final int PASSWORD_LENGTH = 10;
	//constructor; prepares list of names from names.txt
	public InfoGenerator(String outputFileName){
//		try {
//			BufferedReader br = new BufferedReader(new FileReader("names.txt"));
//			String line = br.readLine();
//			while((line = br.readLine()) != null){
//				names.add(line.substring(0,15).trim().toLowerCase());
//			}
//			br.close();
//			ArrayList<String> ips = new ArrayList<String>();
//			ArrayList<String> ports = new ArrayList<String>();
//			ArrayList<String> countrys = new ArrayList<String>();
//			BufferedReader br01 = new BufferedReader(new FileReader("IPaddresses.txt"));
//			PrintWriter writer = new PrintWriter(outputFileName);
//			int i = 0;
//			String IP;
//			while ((line = br01.readLine()) != null) {
//				i++;
//				if (i % 2 != 0) {
//					int j = line.indexOf(".");
//					while(j > 0 && !line.substring(j-1, j).equals("-")){
//						j--;
//					}
//					line = line.substring(j);
//					ips.add(line.split("-")[0]);
//					ports.add(line.split("-")[1]);
//					countrys.add(line.split("-")[line.split("-").length-1]);
//				}
//			}
//			br01.close();
//			for(int j = 0; j < ips.size(); j++){
//				writer.println(new IPaddress(ips.get(j), ports.get(j), countrys.get(j)));
//			}
//			writer.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
	//generates password of given length composed randomly of ascii 48 - 90 inclusive
	public static String makePassword(){
		String out = "";
		for(int i = 0; i < PASSWORD_LENGTH/2; i++){
			out += (char)(r.nextInt(10) + 48);
			out += (char)(r.nextInt(26) + 65);
		}
		return out;
	}
	//returns random name from the names list
	public static String getRandomName(){
		return names.get(r.nextInt(names.size()));
	}
	
	//returns username based on given name
	public static String makeEmail(String firstName, String lastName){
		return firstName.substring(0, firstName.length()/2) + lastName.substring(lastName.length()/2) + 10000/firstName.length();
	}
	
	public static Account getNewAccount(){
		return new Account(getRandomName(), getRandomName(), makePassword(), 0);
	}
	
	public static ArrayList<IPaddress> deserialize(String inputFile){
		try{
			ArrayList<IPaddress> ips= new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();
			while((line = br.readLine()) != null){
				ArrayList<Account> accounts = new ArrayList<>();
				String[] splitLine = line.split("::");
				for(int i = 3; i < 8; i++){
					String[] s = splitLine[i].split("-");
					accounts.add(new Account(s[0], s[1], s[2], Integer.parseInt(s[3])));
				}
				ips.add(new IPaddress(splitLine[0], splitLine[1], splitLine[2], accounts));
			}
			br.close();
			return ips;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void serialize(String outputFile, ArrayList<IPaddress> ips){
		//writer will write ip adress info and associated accounts like this:
		//ip::port::country::firstname-lastname-email-password::firstname-lastname-email-password...
		//one ip per line
		PrintWriter writer;
		try {
			writer = new PrintWriter(outputFile);
			for(IPaddress ip : ips){
				writer.println(ip);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
