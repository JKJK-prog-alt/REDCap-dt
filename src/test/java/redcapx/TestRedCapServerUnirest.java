package redcapx;

/************************************************************
 * @Author: Lea Laitenberger und Katharina Schreiweis
 *
 * This class tests the methods from package redcapx:
 * TestRedCapMockServer is written By Keno, to avoid server connection problems with the "real" RedCap Server
 * **********************************************************/

import org.junit.Test;


public class TestRedCapServerUnirest {
    @Test
    public void TestRedCapServerUnirest(){
    	RedCapServerUnirest testing = new RedCapServerUnirest();
        testing.getData();
        System.out.println(testing.tryConnection());
        //System.out.println(testing.getData());
        System.out.println(testing.getMetaData());

    }

    @Test
    public void TestRedCapMockServer(){
    	RedCapServerUnirest testing = new RedCapMockServer();
        testing.getData();
        System.out.println(testing.tryConnection());
        System.out.println(testing.getData());
        System.out.println(testing.getMetaData());

    }
    
}
