package edu.wctc;

import java.io.IOException;

public class CheckOutSystem extends Garage {

    private CheckOutSystem() throws ClassNotFoundException {
        super();
    }

    @Override
    protected void close() throws IOException {
        this.ticketMap.close();
    }

    private void run() {
        System.out.println("Best Value Parking Garage ========================= ");
        System.out.println(" 1 - check out");
        System.out.println(" 2 - lost ticket");

        try {
            int choice = Integer.parseInt(promptInput(""));
            switch (choice) {
                case 1: {
                    int ticketId = Integer.parseInt(this.promptInput("enter ticket ID"));
                    Ticket ticket = this.ticketMap.findTicket(ticketId);
                    ticket.checkOut(TimeGenerator.getCheckOutTime(), false);

                    System.out.println("Best Value Parking Garage ========================= ");
                    System.out.println("Receipt for vehicle ID " + ticket.getTicketId() + " " +
                            ticket.getHoursParked() + " hours parked, " + ticket.getCheckedInTime() + ":00-" +
                            ticket.getCheckedOutTime() + ":00 $" + ticket.getTicketCost() + ".00");
                    break;
                }

                case 2: {
                    int ticketId = Integer.parseInt(this.promptInput("enter ticket ID"));
                    Ticket ticket = this.ticketMap.findTicket(ticketId);
                    ticket.checkOut(TimeGenerator.getCheckOutTime(), true);
                    System.out.println("Best Value Parking Garage ========================= ");
                    System.out.println("Lost ticket $" + ticket.getTicketCost() + ".00");
                    break;
                }
                default:
                    System.out.println("input was invalid");
            }

        } catch (NumberFormatException e) {
            System.out.println("input was invalid");
        }
    }

    public static void main(String[] args) {
        try {
            CheckOutSystem cos = new CheckOutSystem();
            cos.run();
            cos.close();
        } catch (ClassNotFoundException e) {
            System.out.println("failed to load data from file");
        } catch (IOException e) {
            System.out.println("failed to save data to file");
        }
    }
}
