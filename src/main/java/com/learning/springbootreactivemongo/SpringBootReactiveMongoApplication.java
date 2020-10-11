package com.learning.springbootreactivemongo;

import com.learning.springbootreactivemongo.entity.User;
import com.learning.springbootreactivemongo.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootReactiveMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactiveMongoApplication.class, args);
	}

    @Bean
    ApplicationRunner init(UserRepository repository) {

        Object[][] data = {
                { 1l, "Sachin", "Tendulkar", "sachin.tendulkar@gmail.com"},
                { 2l, "Saurav", "Ganguly", "saurav.ganguly@gmail.com"},
                { 3l, "Rahul", "Dravid", "rahul.dravid@gmail.com"}
        };

        return args -> {
            repository
                    .deleteAll()
                    .thenMany(
                            Flux
                                    .just(data)
                                    .map(array -> {
                                        return new User((Long) array[0], (String) array[1], (String) array[2], (String) array[3]);
                                    })
                                    .flatMap(repository::save)
                    )
                    .thenMany(repository.findAll())
                    .subscribe(user -> System.out.println("saving " + user.toString()));
        };
    }
}
