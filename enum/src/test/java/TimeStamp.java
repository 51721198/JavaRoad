import org.junit.Test;

/**
 * Created by soapsnake on 2017/5/22.
 */
public class TimeStamp {

    @Test
    public void testTimestamp() {
        long timestamp = System.currentTimeMillis();
        System.out.println("timestamp:" + timestamp);
    }
}
