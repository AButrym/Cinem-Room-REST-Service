package cinema.controller;

import cinema.exceptions.AuthException;
import cinema.exceptions.BusinessLogicException;
import cinema.exceptions.WrongPasswordException;
import cinema.model.*;
import cinema.model.request.TokenRequest;
import cinema.model.response.ErrorResponse;
import cinema.model.response.ReturnedSeat;
import cinema.model.response.SoldSeat;
import cinema.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CinemaRoomController {
    @Autowired
    CinemaRoom cinemaRoom;

    @Value("${cinemaroom.secret}")
    String secret;

    @GetMapping("/seats")
    CinemaRoom getSeats() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    SoldSeat purchase(@RequestBody Seat seat) {
        return cinemaRoom.sell(seat);
    }

    @PostMapping("/return")
    ReturnedSeat returnHandler(@RequestBody TokenRequest tokenRequest) {
        String token = tokenRequest.getToken();
        return new ReturnedSeat(cinemaRoom.returnTicket(token));
    }

    @PostMapping("/stats")
    Stats stats(@RequestParam Optional<String> password) {
        password.filter(secret::equals).orElseThrow(WrongPasswordException::new);
        return cinemaRoom.stats();
    }

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse exceptionHandlerBL(Exception e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorResponse exceptionHandlerAuth(Exception e) {
        return new ErrorResponse(e.getMessage());
    }
}
