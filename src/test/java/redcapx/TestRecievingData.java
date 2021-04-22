package redcapx;
import org.junit.Test;
public class TestRecievingData {

	@Test
	public void TestReceivingData() {
		RecievingData testing = new RecievingData();
		System.out.println(testing.tryConnection());
		//testing.tryConnection();
		System.out.println(testing.getData());
	}
}
