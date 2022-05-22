package cinema.model.response;

import cinema.model.Seat;

public class ReturnedSeat {
    Seat returnedTicket;

    public ReturnedSeat(Seat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }

    public Seat getReturnedTicket() {
        return returnedTicket;
    }
}
