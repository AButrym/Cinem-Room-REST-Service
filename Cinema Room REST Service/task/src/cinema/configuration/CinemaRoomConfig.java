package cinema.configuration;

import cinema.model.CinemaRoom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CinemaRoomConfig {
    @Bean
    public CinemaRoom cinemaRoom(
            @Value("${cinemaroom.rows}") int rows,
            @Value("${cinemaroom.cols}") int cols
    ) {
        return new CinemaRoom(rows, cols);
    }
}
