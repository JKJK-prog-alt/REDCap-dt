package redcapx;
import org.junit.Test;
public class TestRedCapServer {

	@Test
	public void TestReceivingData() {
		RedCapServer testing = new RedCapServer();
		System.out.println(testing.tryConnection());
		//testing.tryConnection();
		System.out.println(testing.getData());
	}
}
