package com.webproject.api.repository;

import com.webproject.api.user.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldFindUserByEmail() {
        // Given
        String email = "pramodmadushan@gmail.com";

        // When
        UserModel user = underTest.findByEmail(email);
        // Then
        assertThat(user).isNull();
    }

    @Test
    void itShouldNotSaveUserWhenNameIsNull() {
        // Given
        UserModel user = new UserModel();
        user.setId(1L);
        user.setUserId("12311");
        user.setUserName(null);
        user.setEmail("johndoe@example.com");
        user.setEncryptedPassword("password123");
        user.setFirstName("John");
        // When

        // Then
        assertThatThrownBy(() -> underTest.save(user))
                .hasMessageContaining("not-null property references a null or transient value : " +
                        "com.webproject.api.user.UserModel.userName; nested exception is org.hibernate.PropertyValueException: not-null " +
                        "property references a null or transient value : com.webproject.api.user.UserModel.userName")
                .isInstanceOf(DataIntegrityViolationException.class);

    }

}
