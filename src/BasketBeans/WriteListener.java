package BasketBeans;
import java.io.Serializable;
import java.util.*;

public interface WriteListener extends EventListener, Serializable
{
    public void WriteDetected(WriteEvent e);
}

