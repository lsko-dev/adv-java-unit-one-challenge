package edu.wctc;

import java.io.IOException;
import java.util.Scanner;

public class Garage {
    private Scanner scanner;
    protected TicketMap ticketMap;

    public Garage() throws ClassNotFoundException {
        this.scanner = new Scanner(System.in);

        try {
            this.ticketMap = TicketMap.loadTicketMap();
        } catch (IOException e) {
            this.ticketMap = new TicketMap();
        }
    }

    /**
     * prompts user for input with helper text
     * i.e. this.promptInput("enter id") would be "enter id: "
     * @param helperText text to be printed to aid in gathering input
     * @return user input as a string
     */
    public String promptInput(String helperText) throws IllegalArgumentException {
        System.out.print(helperText + " => ");
        String input =  this.scanner.nextLine();
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("no input provided");
        } else {
            return input;
        }
    }

    protected void close() throws IOException {
        this.ticketMap.close();
    }
}
