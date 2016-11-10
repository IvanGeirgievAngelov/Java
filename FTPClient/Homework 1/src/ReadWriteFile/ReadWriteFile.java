package ReadWriteFile;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;


public class ReadWriteFile {

	//very dummy function;
	
	public static String returnDate(){
		
		Calendar date = Calendar.getInstance();
		int day=date.get(Calendar.YEAR);
		int day1=date.get(Calendar.MONTH);
		int day2=date.get(Calendar.DAY_OF_MONTH);
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(day);
		sb.append(day1);
		sb.append(day2);
		
		return sb.toString();
}
	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner sc = new Scanner(System.in);
		//System.out.println(returnDate());
	
		
		readFile nf = new readFile("Test.txt");
		nf.readLineByLine();
	
		System.out.println("Enter string which we want to replace");
		String which = sc.nextLine();
		System.out.println("Enter String to replace");
		String what = sc.nextLine();
		sc.close();
		
		nf.replaceString(which, what);
		System.out.println(nf.getFileContent());
		
		nf.writeItTofile(nf.getFileContent(), returnDate());
	
	}

}
