package cockpit;

import kong.unirest.UnirestException;
import server.Server;
import upload.Controller;

/************************************************************
 * @Author: Julia Kurashvili
 * 
 * This class starts the upload process
 * 
 * Important note:
 * This program uses mock data as input. If you want to use 
 * data from the RedCapServer, then you need to look into the
 * Controller class in the upload package. There are
 * instructions to change the input in the transferData()
 * method.
 * 
 * 	Method:
 * 		+ main()
 * **********************************************************/

public class App {
	public static void main(String[] args) {

		try {
			Server server = new Server();
			if(server.testConnection()) {
				Controller.transferData(server);
			}
		}catch(UnirestException e) {
			System.out.println("Couldn't connect to local server, make sure it's running before starting this process");
		}


	}
}
