package iangelov.readwriteclass;

import java.util.Scanner;

public class StringReplace {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ReadFile rf=ReadFile.getInstance();
		
		System.out.println("Enter search replace pairs separate by comma");
		String input=sc.next();
		while(!input.contains("end")){
			String[] props=input.split(",");
			rf.setProps(props[0],props[1]);
			input=sc.next();
		}
	
		System.out.println("Enter file name of file we want to read");
		String fileName=sc.next();
		rf.readLineByLine(fileName);

		
		sc.close();
	}

}
