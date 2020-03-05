package edu.wctc;

import java.io.IOException;

public class CheckInSystem extends Garage {
    private TicketBuilder ticketBuilder;

    private CheckInSystem() throws ClassNotFoundException{
        super();

        try {
            this.ticketBuilder = TicketBuilder.loadTicketBuilder();
        } catch (IOException e) {
            this.ticketBuilder = new TicketBuilder();
        }
    }

    @Override
    protected void close() throws IOException {
        this.ticketBuilder.close();
        this.ticketMap.close();
    }

    private void run() {
        System.out.println("Best Value Parking Garage ========================= ");
        System.out.println(" 1 - check in");
        System.out.println(" 2 - close out");

        try {
            int choice = Integer.parseInt(promptInput(""));
            switch (choice) {
                case 1:
                    System.out.println("Best Value Parking Garage ========================= ");
                    Ticket ticket = this.ticketBuilder.createTicket(TimeGenerator.getCheckInTime());
                    System.out.println("ticket ID: " + ticket.getTicketId() +
                            ", vehicle checked in at " + ticket.getCheckedInTime() + ":00");
                    this.ticketMap.addTicket(ticket);
                    break;
                case 2:
                    System.out.println("Best Value Parking Garage ========================= ");
                    System.out.println("Activity to Date");
                    System.out.println(this.ticketMap.profitFromCheckedTickets());
                    System.out.println(this.ticketMap.profitFromLostTickets());
                    System.out.println(this.ticketMap.profitTotal());

                    break;
                default:
                    System.out.println("input was invalid");
            }

        } catch (NumberFormatException e) {
            System.out.println("input was invalid");
        }
    }

    public static void main(String[] args) {
        try {
            CheckInSystem cis = new CheckInSystem();
            cis.run();
            cis.close();
        } catch (ClassNotFoundException e) {
            System.out.println("failed to load data from file");
        } catch (IOException e) {
            System.out.println("failed to save data to file");
        }
    }
}
