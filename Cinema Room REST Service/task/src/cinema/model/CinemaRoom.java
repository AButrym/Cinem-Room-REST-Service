package cinema.model;

import cinema.exceptions.AlreadyPurchasedException;
import cinema.exceptions.RowColOutOfBoundsException;
import cinema.exceptions.WrongTokenException;
import cinema.model.response.SoldSeat;

import java.util.*;


public class CinemaRoom {
    int totalRows;
    int totalColumns;
    Collection<Seat> availableSeats;
    Map<String, SoldSeat> soldSeats = new HashMap<>();

    public CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        availableSeats = new LinkedHashSet<>(totalRows * totalColumns);
        for (int row = 1; row <= totalRows; row++) {
            for (int col = 1; col <= totalColumns; col++) {
                availableSeats.add(new Seat(row, col));
            }
        }
    }

    public SoldSeat sell(Seat seat) {
        if (seat.getColumn() < 1 || totalColumns < seat.getColumn() ||
                seat.getRow() < 1 || totalRows < seat.getRow()) {
            throw new RowColOutOfBoundsException();
        }
        synchronized (this) {
            if (!availableSeats.remove(seat)) {
                throw new AlreadyPurchasedException();
            }
            var soldSeat = new SoldSeat(seat);
            soldSeats.put(soldSeat.getToken(), soldSeat);
            return soldSeat;
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public Collection<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public Seat returnTicket(String token) {
        synchronized (this) {
            if (!soldSeats.containsKey(token)) {
                throw new WrongTokenException();
            }
            var ticket = soldSeats.remove(token).getTicket();
            availableSeats.add(ticket);
            return ticket;
        }
    }

    public Stats stats() {
        int income = soldSeats.values().stream()
                .map(SoldSeat::getTicket)
                .mapToInt(Seat::getPrice)
                .sum();
        return new Stats(income, availableSeats.size(), soldSeats.size());
    }
}
