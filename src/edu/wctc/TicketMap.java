package edu.wctc;

import java.io.*;
import java.util.HashMap;

public class TicketMap {
    private HashMap<Integer, Ticket> tickets;

    public TicketMap() {
        this.tickets = new HashMap<>();
    }

    private TicketMap(HashMap<Integer, Ticket> ticketsMap) {
        this.tickets = ticketsMap;
    }

    public static TicketMap loadTicketMap() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("ticketMap");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object obj;
        HashMap<Integer, Ticket> tickets = new HashMap<>();

        try {
            while((obj = ois.readObject()) != null) {
                Ticket ticket = (Ticket)obj;
                tickets.put(ticket.getTicketId(), ticket);
            }
        } catch (EOFException ignored) {}

        return new TicketMap(tickets);
    }

    public void addTicket(Ticket ticket) {
        this.tickets.put(ticket.getTicketId(), ticket);
    }

    public Ticket findTicket(int ticketId) {
        return this.tickets.get(ticketId);
    }

    public void close() throws IOException {
        FileOutputStream fos = new FileOutputStream("ticketMap");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Ticket ticket : this.tickets.values()) {
            oos.writeObject(ticket);
        }

        oos.close();
    }

    public String profitFromCheckedTickets() {
        int total = 0;
        int counter = 0;
        for (Ticket ticket : this.tickets.values()) {
            if (ticket.isCheckedOut() && !ticket.isLost()) {
                total += ticket.getTicketCost();
                counter += 1;
            }
        }
        return "$" + total + " was collected from " + counter + " checked tickets";
    }

    public String profitFromLostTickets() {
        int total = 0;
        int counter = 0;
        for (Ticket ticket : this.tickets.values()) {
            if (ticket.isLost()) {
                total += ticket.getTicketCost();
                counter += 1;
            }
        }
        return "$" + total + " was collected from " + counter + " lost tickets";
    }

    public String profitTotal() {
        int total = 0;
        for (Ticket ticket: this.tickets.values()) {
            if (ticket.isCheckedOut()) {
                total += ticket.getTicketCost();
            }
        }
        return "$" + total + " was collected";
    }
}
