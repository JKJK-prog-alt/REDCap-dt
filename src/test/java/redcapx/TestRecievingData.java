package redcapx;
import org.junit.Test;
public class TestRecievingData {

	@Test
	public void TestReceivingData() {
		RecievingData testing = new RecievingData();
		//testing.tryConnection();
		System.out.println(testing.getData());
	}
}
