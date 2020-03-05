package edu.wctc;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TicketBuilder {
    private ArrayList<Integer> usedIds;

    public TicketBuilder() {
        this.usedIds = new ArrayList<>();
    }

    public static TicketBuilder loadTicketBuilder() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("usedIds");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object obj;
        ArrayList<Integer> usedIds = new ArrayList<>();

        try {
            while((obj = ois.readObject()) != null) {
                Integer id = (Integer)obj;
                usedIds.add(id);
            }
        } catch (EOFException ignored) {}

        TicketBuilder tb = new TicketBuilder();
        tb.usedIds = usedIds;
        return tb;
    }

    /**
     * close the ticket builder out
     * @throws IOException from fos/oos
     */
    public void close() throws IOException {
        FileOutputStream fos = new FileOutputStream("usedIds");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Integer id : this.usedIds) {
            oos.writeObject(id);
        }

        oos.close();
    }

    public Ticket createTicket(int checkedInTime) {
        return new Ticket(checkedInTime, this.generateUniqueId());
    }

    /**
     * generates a random unique id for the ticket
     * @return random unique integer
     */
    private int generateUniqueId() {
        while (true) {
            int newId = ThreadLocalRandom.current().nextInt(100_000,1_000_000);
            if (!this.usedIds.contains(newId)) {
                this.usedIds.add(newId);
                return newId;
            }
        }
    }
}
