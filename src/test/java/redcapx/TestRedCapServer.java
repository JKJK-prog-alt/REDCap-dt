package redcapx;
import org.junit.Test;
public class TestRedCapServer {

	@Test
	public void TestReceivingData() {
		RedCapServer testing = new RedCapServer();
		System.out.println(testing.tryConnection());
		//testing.tryConnection();
		RedCapServer.Format test = RedCapServer.Format.JSON; //Decide what Format u want
		System.out.println(testing.getData(test));
	}
}
