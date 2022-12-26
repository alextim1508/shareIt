package ru.practicum.shareit.item.dto;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.shareit.item.ItemBaseTest;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class CommentDtoInTest extends ItemBaseTest {

    @Autowired
    private JacksonTester<CommentDtoIn> jacksonTester;

    @Test
    void toItemDto() throws IOException {
        JsonContent<CommentDtoIn> result = jacksonTester.write(commentDtoIn);
        assertThat(result).extractingJsonPathStringValue("$.text").isEqualTo(commentDtoIn.getText());
    }

    @Test
    void equalsAndHashCodeTest() {
        CommentDtoIn x = new CommentDtoIn(comment.getText());

        CommentDtoIn y = new CommentDtoIn(comment.getText());

        assertThat(x.equals(y) && y.equals(x)).isTrue();
        assertThat(x.hashCode() == y.hashCode()).isTrue();
    }

    @Test
    void equalsTest() {
        CommentDtoIn x = new CommentDtoIn(comment.getText());

        assertThat(x.equals(x)).isTrue();
        assertThat(x.equals(null)).isFalse();
        assertThat(x.equals(new Object())).isFalse();
    }

    @Test
    void noArgsConstructorTest() {
        CommentDtoIn commentDto = new CommentDtoIn();
        commentDto.setText(comment.getText());
        assertThat(commentDto.getText()).isEqualTo(comment.getText());
    }

    @Test
    void toStringTest() {
        assertThat(commentDtoIn.toString()).startsWith(commentDtoIn.getClass().getSimpleName());
    }
}