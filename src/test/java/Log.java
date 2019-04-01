import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Log {

    private static final Logger log = LogManager.getLogger(Log.class);

    public static Logger returnLogger(){
       return log;
    }



}