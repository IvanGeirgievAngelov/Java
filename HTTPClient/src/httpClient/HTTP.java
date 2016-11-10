package httpClient;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HTTP {
public static void main(String[] args) {
	
	HttpURLConnection connect = null;
	URL url= null;
	
	 try {
		 //open connection to google.
	 url= new URL("https://www.google.bg/");
	 connect = (HttpURLConnection) url.openConnection();
	 
	 
	 //send request to server'
	 connect.setRequestMethod("GET");
	 
	 //print response
	 System.out.println(connect.getResponseCode());
	 System.out.println(connect.getResponseMessage());
	 
	 //initialize what we need
	 File file=null;
	 FileWriter fw=null;
	 BufferedWriter bw =null;
	 
	 try{
	 file=new File("Response.txt");

	 //open write options;
	 fw = new FileWriter(file,false);
	 bw = new BufferedWriter(fw);
	 
	 //write response code and message to file 
	 bw.write(new Integer(connect.getResponseCode()).toString()+"\n");
	 bw.write(connect.getResponseMessage());
	 
	 }catch (IOException ioe) {
		 System.out.println("Something went wrong");
		 ioe.printStackTrace();
	 }finally{
		 if(bw!=null) {
		 bw.close();
		 }
		 if(fw!=null) {
		 fw.close(); 
		 }
	 }
	 	
	 }catch (MalformedURLException e1) {
		 	System.out.println("Malformed url");
			e1.printStackTrace();
		}catch (ProtocolException e) {
		e.printStackTrace();
	}catch (IOException e) {
		e.printStackTrace();
	}finally{
		if(connect!=null) {
		connect.disconnect();
		}
	}
}	

}
