package ftpClient.iangelov;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * Create an FTP sample that uploads downloads and delete files.
 * 
 * @author IvanAngelov
 *
 */

//Client operating with files on LocalHost
public class FTPClientItself implements FTPClientInterface {
	
	//default constructor
	public FTPClientItself(){
		
	};
	
	//Connect to LocalHost 
public  FTPClient ConnectToServer(String server,String acount,String pass) {
		
		
		FTPClient client= new FTPClient();
		try {
	
			client.connect(server);
			client.login(acount, pass);
			if(client.isConnected()) {
			
				System.out.println("Connected to " + server);
				System.out.println(client.getReplyString());
			}else{
				System.out.println("Cannot connect to server: " + server);
			}
		} catch (SocketException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		client.enterLocalPassiveMode();
		return client;
	}

	//prints files on server
	public  void printFilesInfo(FTPClient client) {
		try{
			FTPFile[] listOfFiles=client.listFiles();
			for(FTPFile file : listOfFiles){
				String fileInfo=file.getName();
				if(file.isDirectory()) {
					fileInfo="["+ fileInfo +"]";
				}
				fileInfo += "\t\t\t" + file.getSize();
				fileInfo += "\t\t\t" + (file.getTimestamp().getTime().toString());
				System.out.println(fileInfo);
			}
			}catch (IOException e) {
			
				e.printStackTrace();
			}
			
	}
	
	//upload files on server
	public  void upload(FTPClient client) {
		String localAddress="G:\\java\\FTPClient\\TestFile.txt";
		String fileName = "TestFile.txt";
		File localFile=new File(fileName);
		InputStream is=null;
		
		try {
			client.setFileType(FTP.BINARY_FILE_TYPE);

			if(localFile.exists()) {
				
				 is=new FileInputStream(localAddress);
				
					System.out.println("Start uploading file" + fileName);
					if(client.storeFile(fileName, is)) {
						System.out.println("file "+ fileName + " uploaded successefully");
					}
			}
		
			
		}catch(FileNotFoundException fnfe) {
			System.out.println("file " + fileName + "does not exist");
		}
		catch (IOException e) {
			System.out.println("error"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					System.out.println("error"+e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	//download files from server
	public  void download(FTPClient client,String fileName) {
		
		if(checkFileExists(client,fileName)){
		File file = null;
		OutputStream os = null;
		try{
			file=new File(fileName);
			os = new BufferedOutputStream(new FileOutputStream(file));
			if(client.retrieveFile(fileName, os)) {
				System.out.println("File "+ fileName + " downloaded successefully");
			}
		}catch(FileNotFoundException fnfe) {
			System.out.println("Cannot Find file " + fileName);
		}catch(IOException ioe) {
			System.out.println("error" + ioe.getMessage());
			ioe.printStackTrace();
		}
		finally{
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					System.out.println("Error closing file" + fileName + ".txt");
					e.printStackTrace();
				}
			}
		}
		}
		else{
			System.out.println("There is no file " + fileName + " on server");
		}
	}
	
	//disconnecting from server
	public  void disconnect(FTPClient client) { 
		try {
			if(client != null) {
			client.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//delete files on server;
	public  void delete(FTPClient client,String fileName) {
		
		if(checkFileExists(client,fileName)) {
		try {
			if(client.deleteFile(fileName)) {
				System.out.println("file " + fileName + " deleted successfully");
			}
			else{
				System.out.println("file " + fileName + " is not deleted");		
				}
		} catch (IOException e) {
			System.out.println("Error deleting file");
			e.printStackTrace();
		}
		}
	}
	
	//check if client is open;
	public boolean isConnected(FTPClient client) {
		return client.isConnected();
	}
	
	//check if fileExist;
	 public boolean checkFileExists(FTPClient client, String filePath) {
		  	
		  	try{
	        InputStream inputStream = client.retrieveFileStream(filePath);
	        int returnCode = client.getReplyCode();
	        if (inputStream == null || returnCode == 550) {
	          	System.out.println("not exist");
	            return false;
	        }
		  	}catch(IOException ioe) {
		  		System.out.println("Error" + ioe.getMessage());
		  		ioe.printStackTrace();
		  	}
		
		    return true;
	    }

	
}
