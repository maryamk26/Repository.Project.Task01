package sp_example.services;

import sp_example.models.Event;
import sp_example.models.User;
import sp_example.repositories.EventsRepository;
import sp_example.repositories.UsersRepository;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class AppService {

    private final UsersRepository usersRepository;
    private final EventsRepository eventsRepository;

    public AppService(UsersRepository usersRepository, EventsRepository eventsRepository) {
        this.usersRepository = usersRepository;
        this.eventsRepository = eventsRepository;
    }

    public void signUp(String email, String password) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(email)
                .password(password)
                .build();

        usersRepository.save(user);
    }

    public void addEvent(String name, LocalDate date) {
        Event event = Event.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .date(date)
                .build();

        eventsRepository.save(event);
    }

    public void addUserToEvent(String emailUser, String nameEvent) {
        User user = usersRepository.findByEmail(emailUser);

        if (user == null) {
            throw new IllegalArgumentException("Пользователь не найден");
        }

        Event event = eventsRepository.findByName(nameEvent);

        if (event == null) {
            throw new IllegalArgumentException("Событие не найдено");
        }

        eventsRepository.saveUserToEvent(user, event);
    }
}
