package ru.practicum.shareit.user.dto;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class UserDtoInTest extends UserDtoOutTest {


    @Autowired
    private JacksonTester<UserDtoIn> jacksonTester;

    @Test
    void toUserDto() throws IOException {
        JsonContent<UserDtoIn> result = jacksonTester.write(userDtoIn);
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo(userDtoIn.getName());
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo(userDtoIn.getEmail());
    }

    @Test
    void equalsAndHashCodeTest() {
        UserDtoIn x = UserDtoIn.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        UserDtoIn y = UserDtoIn.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        assertThat(x.equals(y) && y.equals(x)).isTrue();
        assertThat(x.hashCode() == y.hashCode()).isTrue();
    }

    void equals_shouldReturnFalseWhenNamesAreNotTheSame() {
        UserDtoIn x = UserDtoIn.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        UserDtoIn y = UserDtoIn.builder()
                .name("other name")
                .email(user.getEmail())
                .build();

        assertThat(x.equals(y)).isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenEmailsAreNotTheSame() {
        UserDtoIn x = UserDtoIn.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();

        UserDtoIn y = UserDtoIn.builder()
                .name(user.getName())
                .email("other@mail.com")
                .build();

        assertThat(x.equals(y)).isFalse();
    }

    @Test
    void equalsTest() {
        assertThat(userDtoIn.equals(userDtoIn)).isTrue();
        assertThat(userDtoIn.equals(null)).isFalse();
        assertThat(userDtoIn.equals(new Object())).isFalse();
    }

    @Test
    void noArgsConstructorTest() {
        UserDtoIn userDto = new UserDtoIn();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        assertThat(userDto.getName()).isEqualTo(user.getName());
        assertThat(userDto.getName()).isEqualTo(user.getName());
    }

    @Test
    void allArgsConstructorTest() {
        UserDtoIn userDto = new UserDtoIn(user.getName(), user.getEmail());

        assertThat(userDto.getName()).isEqualTo(user.getName());
        assertThat(userDto.getName()).isEqualTo(user.getName());
    }

    @Test
    void toStringTest() {
        assertThat(userDtoIn.toString()).startsWith(userDtoIn.getClass().getSimpleName());
    }
}
