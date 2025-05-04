package com.travel.wanderwisefullstack;

import com.travel.wanderwisefullstack.Services.trip.TripService;
import com.travel.wanderwisefullstack.Services.user.AccountService;
import com.travel.wanderwisefullstack.dto.TripRequestDTO;
import com.travel.wanderwisefullstack.models.AppRole;
import com.travel.wanderwisefullstack.models.AppUser;
import com.travel.wanderwisefullstack.models.Trip;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WanderwiseFullStackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WanderwiseFullStackApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner start(AccountService accountService, TripService tripServices)
    {
        return args -> {
            if (!adExist(accountService))
            {
                accountService.addNewRole(new AppRole(null,"ORGANIZER"));
                accountService.addNewRole(new AppRole(null,"ADMIN"));
                AppUser admin  = new AppUser(null,"admin", "1234", new ArrayList<>(),new ArrayList<>());
                accountService.addNewUser(admin);
                accountService.addRoleToUser("admin","ADMIN");

                List<String> cities =  new ArrayList<>();
                cities.add("Belgium");
                cities.add("Canada");
                cities.add("Germany");
                cities.add("India");
                cities.add("United States");
                cities.add("United Kingdom");
                cities.add("Morocco");
                cities.add("Spain");
                cities.add("Pakistan");
                cities.add("Turkish");
                for (int i = 0; i < 10; i++)
                {
                    TripRequestDTO trip = new TripRequestDTO();

                    LocalDateTime startDate = generateRandomFutureDate();
                    LocalDateTime endDate = generateRandomFutureDateAfter(startDate);
                    trip.setStartDate(startDate);
                    trip.setEndDate(endDate);
                    trip.setDestination(cities.get(i));
                    Duration duration = Duration.between(startDate, endDate);
                    trip.setDuration((int) duration.toDays());
                    trip.setDescription("Description of trip to " + cities.get(i));
                    trip.setPrice(Math.random() * 2000);

                trip.setItinerary("");
                    trip.setSpots((int) (Math.random() * 200));

                    tripServices.createTrip(trip);
                }


            }

        };
    }

    boolean adExist(AccountService accountService)
    {
        AppUser adminExist = accountService.loadUserByUsername("admin");
       return adminExist != null;
    }

    public static LocalDateTime generateRandomFutureDate() {
        Random random = new Random();

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Generate random values for future year, month, day, hour, and minute
        int futureYears = random.nextInt(5) + 1; // Random number of years from 1 to 5
        int futureMonths = random.nextInt(12) + 1; // Random month (1 to 12)
        int futureDays = random.nextInt(28) + 1; // Random day (1 to 28)
        int futureHours = random.nextInt(24); // Random hour (0 to 23)
        int futureMinutes = random.nextInt(60); // Random minutes (0 to 59)

        // Add the random future time to the current date and time
        LocalDateTime randomFutureDate = now.plusYears(futureYears)
                .plusMonths(futureMonths)
                .plusDays(futureDays)
                .plusHours(futureHours)
                .plusMinutes(futureMinutes);

        return randomFutureDate;
    }

    public static LocalDateTime generateRandomFutureDateAfter(LocalDateTime startDate) {
        Random random = new Random();

        // Generate a random duration after the start date for the end date
        int durationInDays = random.nextInt(30) + 1; // Random number of days (1 to 30)

        // Add the random duration to the start date to ensure the end date is after the start date
        return startDate.plusDays(durationInDays);
    }
}
