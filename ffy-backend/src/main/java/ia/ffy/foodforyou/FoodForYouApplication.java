package ia.ffy.foodforyou;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class FoodForYouApplication {

    public static void main(final String[] args) {
        SpringApplication.run(FoodForYouApplication.class, args);
    }

}
