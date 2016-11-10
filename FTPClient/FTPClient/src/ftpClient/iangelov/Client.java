package ftpClient.iangelov;

import java.util.Scanner;
import org.apache.commons.net.ftp.FTPClient;

public class Client {


	public static void main(String[] args) {

		//login information
		String server="localhost";
		String acount="ivanangelov";
		String pass="123";

		//command option and menu
		int command = 1;

		//printing menu
		System.out.println("Choose a command from a list");
		System.out.println("\t 1) Upload file");
		System.out.println("\t 2) Download file"); 
		System.out.println("\t 3) Delete file"); 
		System.out.println("\t 4) Show files on server");
		System.out.println("\t\t anything else exit");

		//create an instance of scanner to read command options;
		Scanner sc=new Scanner(System.in);

		//create an instance of FTPclient
		FTPClientItself newClient=new FTPClientItself();

		FTPClient client = null;

		//connect to server;
		client = newClient.ConnectToServer(server,acount,pass);

		while(command<6 && command >0) {
			//getting command;
			command=sc.nextInt();

			//applying command;
			switch (command) {

			case 1: {
				//upload file and show all the files on server
				newClient.upload(client);
				break;
			}
			case 2: {
				//downloads file from server 
				newClient.download(client,"TestFile.txt");
				break;

			}
			case 3: {
				//delete file from server and show all the files
				newClient.delete(client,"TestFile.txt");
				break;
			}
			case 4: {
				//print server files information
				newClient.printFilesInfo(client);

			}

			}

		}

		//close resources
		if(sc != null) {
			sc.close();
		}
		if(newClient.isConnected(client)) {
			//disconnect from server
			System.out.println("Disconnecting from " + server);
			newClient.disconnect(client);
		}
		System.out.println("Program exit successfully");
	}	
}


