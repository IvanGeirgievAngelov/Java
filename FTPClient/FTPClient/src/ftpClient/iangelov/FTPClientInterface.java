/**
 * 
 */
package ftpClient.iangelov;

import org.apache.commons.net.ftp.FTPClient;

/**
 * @author Ivan
 *
 */
public interface FTPClientInterface {
	
	public  FTPClient ConnectToServer(String server,String acount,String pass);
	
	public  void printFilesInfo(FTPClient client);
	
	public  void upload(FTPClient client);
	
	public  void download(FTPClient client,String fileName);
	
	public  void disconnect(FTPClient client);
	
	public  void delete(FTPClient client,String fileName);
	
	public boolean isConnected(FTPClient client);
	
	boolean checkFileExists(FTPClient client, String filePath);
	  
}
