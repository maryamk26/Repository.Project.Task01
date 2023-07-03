package sp_example.repositories.impl;

import sp_example.models.User;
import sp_example.repositories.UsersRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class UsersRepositoryFileImpl implements UsersRepository {

    private final String fileName;

    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(model.getId() + "|" + model.getEmail() + "|" + model.getPassword());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User findByEmail(String emailUser) {
        // TODO: необходимо реализовать
        if (emailUser.equals("marsel@gmail.com")) {
            return User.builder()
                    .id("ef6e1fba-50ed-48be-84bd-263f9ceaa971")
                    .email("marsel@gmail.com")
                    .password("qwerty008")
                    .build();
        } return null;
    }
}
