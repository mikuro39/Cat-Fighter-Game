package ui;

import model.Event;
import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

//Overrides the windowClosing method of WindowAdapter to print the log whenever a frame is closed
public class Window extends WindowAdapter {
    //EFFECTS: prints the log whenever the window is closed
    @Override
    public void windowClosing(WindowEvent e) {
        log();
    }

    //EFFECTS: prints out a list of all the events in the EventLog
    private void log() {
        List<Event> l = new ArrayList<Event>();
        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
            System.out.println(next.toString());
        }
    }
}