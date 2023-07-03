package sp_example.repositories;

import java.util.ArrayList;
import java.util.List;

public interface EventsRepository extends CrudRepository<Event> {
    void save(sp_example.models.Event model);

    sp_example.models.Event findByName(String nameEvent);

    List<Event> findAllByMembersContains(User user);

    void save(Event model);

    void saveUserToEvent(User user, Event event);

    void saveUserToEvent(sp_example.models.User user, sp_example.models.Event event);
}

class EventService {
    private EventsRepository eventsRepository;
    private UserRepository userRepository;

    public EventService(EventsRepository eventsRepository, UserRepository userRepository) {
        this.eventsRepository = eventsRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getAllEventsByUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return eventsRepository.findAllByMembersContains(user);
        } else {
            System.out.println("User not found.");
            return new ArrayList<>(); // Return an empty list if user not found
        }
    }
}

class UserRepository {
    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findByEmail(String emailUser) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(emailUser)) {
                return user;
            }
        }
        return null;
    }
}

abstract class EventsRepositoryFileImpl implements EventsRepository {
    private List<Event> events;

    public EventsRepositoryFileImpl() {
        this.events = new ArrayList<>();
    }

    @Override
    public sp_example.models.Event findByName(String nameEvent) {
        for (Event event : events) {
            if (event.getName().equalsIgnoreCase(nameEvent)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public List<Event> findAllByMembersContains(User user) {
        List<Event> userEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getMembers().contains(user)) {
                userEvents.add(event);
            }
        }
        return userEvents;
    }

    @Override
    public void save(Event model) {
        events.add(model);
    }

    @Override
    public void saveUserToEvent(User user, Event event) {
        event.getMembers().add(user);
    }
}

class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        User user1 = new User("1", "Maryam", "Maryam@example.com");
        User user2 = new User("2", "Farida", "Farida@example.com");
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        EventsRepository eventsRepository = new EventsRepositoryFileImpl() {
            @Override
            public void save(sp_example.models.Event model) {

            }

            @Override
            public void saveUserToEvent(sp_example.models.User user, sp_example.models.Event event) {

            }
        };

        EventService eventService = new EventService(eventsRepository, userRepository);

        String email = "Maryam@example.com";
        List<Event> userEvents = eventService.getAllEventsByUser(email);

        if (!userEvents.isEmpty()) {
            System.out.println("Events participated by user with email " + email + ":");
            for (Event event : userEvents) {
                System.out.println("- " + event.getName());
            }
        } else {
            System.out.println("No events found for user with email " + email);
        }
    }
}
