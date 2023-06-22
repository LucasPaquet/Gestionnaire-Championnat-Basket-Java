package BasketBeans;

import java.io.Serializable;
import java.util.*;
public class WriteEvent extends EventObject implements Serializable {
    private String log;

    public WriteEvent(Object source) {
        super(source);
        log = "";
    }

    public WriteEvent(Object source, String mc) {
        super(source);
        log = mc;
    }

    public String getLog() {
        return log;
    }
}
