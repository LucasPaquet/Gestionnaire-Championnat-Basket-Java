package BasketBeans;

import java.beans.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class LogWriterBean implements Serializable {
    private String log;
    private Vector<WriteListener> writeListeners;
    private transient PropertyChangeSupport propertyChangeSupport;

    public LogWriterBean() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        writeListeners = new Vector<>();
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        String oldLog = this.log;
        this.log = log;
        propertyChangeSupport.firePropertyChange("log", oldLog, log);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void writeLog(String message) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(new Date())  + " => " + message;

            BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
            writer.write(formattedDate);
            writer.newLine();
            writer.close();

            setLog(formattedDate);
            notifyWriteDetected(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Gestion des listeners
    public void addWriterListener(WriteListener ml)
    {
        if (!writeListeners.contains(ml))
            writeListeners.addElement(ml);
    }
    public void removeWriterListener(WriteListener ml)
    {
        if (writeListeners.contains(ml))
            writeListeners.removeElement(ml);
    }

    // notification de l'évènement
    protected void notifyWriteDetected(String mc){
        WriteEvent e = new WriteEvent(this,mc);
        for (int i=0 ; i<writeListeners.size() ; i++)
        {
            WriteListener mv = writeListeners.elementAt(i);
            mv.WriteDetected(e);
        }
    }
}

