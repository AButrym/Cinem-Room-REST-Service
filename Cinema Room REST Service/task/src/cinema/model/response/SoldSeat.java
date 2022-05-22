package cinema.model.response;

import cinema.model.Seat;

import java.util.UUID;

public class SoldSeat {
    String token;
    Seat ticket;

    public SoldSeat(Seat ticket) {
        this.ticket = ticket;
        token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
