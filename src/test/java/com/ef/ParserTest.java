package  com.ef;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ParserTest{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private String accessLogPath;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        ClassLoader loader = ParserTest.class.getClassLoader();
        accessLogPath= ParserTest.class.getResource("access.log").getFile();
        accessLogPath=accessLogPath.indexOf(":")!=-1?accessLogPath.substring(1):accessLogPath;
    }

    @Test
    public void testParserWithAllParametersHappyScenario() {
        //"java -cp \"parser.jar\" com.ef.Parser --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100"
        //java -cp target/parser.jar com.ef.Parser --accesslog=C:\Users\tgxt3353\Desktop\tmp\walethub\access.log --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100
         Parser.main(new String[]{" --accesslog="+accessLogPath,"--startDate=2017-01-01.23:59:41","--duration=hourly ","--threshold=3"});
         assertEquals("192.168.122.135",  outContent.toString().trim());
    }
}