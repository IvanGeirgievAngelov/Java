package ReadWriteFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class readFile {

	private String filename;
	private ArrayList<String> arr;

	public readFile(String name) {
		filename = name;
		arr = new ArrayList<String>();

	}

	public ArrayList<String> getFileContent() {
		return arr;
	}

	public void readLineByLine() {

		File file = null;
		Scanner sc = null;

		try {

			file = new File(filename);
			
			if (!file.isDirectory() && file.getName().endsWith(".txt")) {
				sc = new Scanner(file);

				while (sc.hasNextLine()) {
					arr.add(sc.nextLine() + "\n");
				}

			}
		} catch (FileNotFoundException fnf) {
			System.out.println("file" + filename + " does not exist");
		} catch (NullPointerException npe) {
			System.out.println("File " + filename + " does not exist");
		} catch (Exception e){
			System.out.println("Something went wrong" +e.getMessage());
		}
		finally {
			if (sc != null) {
				sc.close();
			}
		}

	}


	public void writeItTofile(ArrayList<String> array, String name) throws FileNotFoundException {
		
		String filename1=(filename + "_modified_" + name+".txt");
		File file = null;
		PrintWriter writer=null;
		
		try{
		file= new File(filename1);
		
		if(file.createNewFile()){
			
		writer = new PrintWriter(file);
		int size = array.size();
		
		for (int i = 0; i < size; i++) {
			writer.println(array.get(i));
		}
		
		}
		
		}catch (FileNotFoundException fnfe){
			System.out.println(filename1);
		}catch (IOException e){
			System.out.println("Something went wrong" + e.getMessage());
			e.printStackTrace();
		}finally{
			if(writer!=null){
				writer.close();
			}
		}
	}



	public void replaceString(String find, String replace) {
		for (int i = 0; i < arr.size(); i++) {
			arr.set(i, arr.get(i).replace(find, replace));
		}
	}

}
