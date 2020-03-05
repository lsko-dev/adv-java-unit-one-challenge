package edu.wctc;

import java.io.Serializable;

/**
 * an individual parking ticket in the system
 */
public class Ticket implements Serializable {
    private int checkedInTime;
    private int ticketId;
    private boolean isLost;
    private boolean isCheckedOut;
    private int ticketCost;
    private int checkedOutTime;
    private int hoursParked;

    public Ticket(int checkedInTime, int ticketId) {
        this.checkedInTime = checkedInTime;
        this.ticketId = ticketId;
        this.isLost = false;
        this.isCheckedOut = false;
    }

    public void checkOut(int checkedOutTime, boolean isLost) {
        this.checkedOutTime = checkedOutTime;
        int ticketCost;
        if (isLost) {
            this.isLost = true;
            ticketCost = 25;
        } else {
            int timeDiff = checkedOutTime - this.getCheckedInTime();
            this.hoursParked = timeDiff;

            if (timeDiff < 3) {
                ticketCost = 5;
            } else {
                ticketCost = 5 + timeDiff;
                ticketCost =  Math.min(ticketCost, 15);
            }
        }

        this.ticketCost = ticketCost;
        this.isCheckedOut = true;
    }


    public int getTicketId() { return this.ticketId; }
    public int getCheckedInTime() { return this.checkedInTime; }
    public int getTicketCost() { return this.ticketCost; }
    public boolean isLost() { return this.isLost; }
    public boolean isCheckedOut() { return this.isCheckedOut; }
    public int getCheckedOutTime() { return this.checkedOutTime; }
    public int getHoursParked() { return this.hoursParked; }
}
