package iangelov.readwriteclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

/**
 * The ReadFile class read text file and replace string on it.
 * Then write it into output file.
 */
public class ReadFile {

	/**@param keep search replace string pairs */
	private Properties prop=new Properties();

	/**@param reference for singleton instance */
	private static ReadFile rf = null;

	/** private constructor */
	private ReadFile(){	
	}

	/**
	 * read file line by line and replace given properties
	 * after it replaced string is written to file with modified name;
	 * 
	 * @param name name of input file
	 */
	public void readLineByLine(String name) {

		File file = null;
		FileInputStream fis = null;
		BufferedReader br=null;
		PrintWriter pw=null;
		
		loadProps("config.proporties");
		String fullName = (name + "_modified_" + returnDate()+".txt");


		try {

			file = new File(name);
			fis = new FileInputStream(file);
			
			if(file.exists() && file.getName().endsWith(".txt")){
			br=new BufferedReader(new InputStreamReader(fis));
			}
			else{
				FileNotFoundException fnfe=new FileNotFoundException();
				throw fnfe;
			}
		
			
			pw=new PrintWriter(fullName);
		
			String line = null;
			Set<Object> keys = prop.keySet();

			while ((line=br.readLine())!=null){

				for(Object key : keys){
					line = line.replaceAll(key.toString(), prop.get(key).toString());
				}	
				pw.println(line);			
			}

		}catch (FileNotFoundException e) {
			System.out.println("File " + name + " does not exist");
			e.printStackTrace();
		}catch (NullPointerException npe){

		}catch(IOException ioe){

		}finally{
			if(pw!=null){
				pw.close();
			}
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}


	/** set string pairs into a file 
	 * @param key  
	 * @param value	
	 */
	public void setProps(String key,String value){
		OutputStream os=null;
		try{
			File file = new File("config.proporties");
			os=new FileOutputStream(file,false);
			prop.setProperty(key,value);
			prop.store(os,null);

		}catch (IOException io){
			System.out.println(io.toString());
			io.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					System.out.println(e.toString());
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * load properties from a file
	 * @param name name of file from which we load props
	 */
	public void loadProps(String name){ 
		InputStream is=null;
		try{
			is = new FileInputStream("config.proporties");
			prop.load(is);
		}catch (IOException io){
			io.printStackTrace();
			
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
	}

	/**
	 * get the exact date
	 * @return current date to string
	 */
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
	
	/** function that return one instance of this class */
	public static ReadFile getInstance(){
		if(rf==null){
			rf=new ReadFile();
		}
		return rf;
	}
}
