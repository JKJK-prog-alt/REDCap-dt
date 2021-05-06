package redcapx;
import org.junit.Test;


public class TestRedCapServerUnirest {
    @Test
    public void TestRedCapServerUnirest(){
        RedCapServerUnirest testing = new RedCapServerUnirest();
        //testing.getData();

        System.out.println(testing.getData());
        System.out.println(testing.tryConnection());

    }

}
