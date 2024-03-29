package ru.practicum.shareit.item.dto;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.shareit.booking.dto.ShortBookingDtoOut;
import ru.practicum.shareit.item.ItemBaseTest;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class FullItemDtoOutTest extends ItemBaseTest {

    @Autowired
    private JacksonTester<FullItemDtoOut> jacksonTester;

    @Test
    void toItemDto() throws IOException {
        JsonContent<FullItemDtoOut> result = jacksonTester.write(fullItemDtoOut);
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(fullItemDtoOut.getId());
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo(fullItemDtoOut.getName());
        assertThat(result).extractingJsonPathStringValue("$.description").isEqualTo(fullItemDtoOut.getDescription());
        assertThat(result).extractingJsonPathBooleanValue("$.available").isEqualTo(fullItemDtoOut.getAvailable());
        assertThat(result).extractingJsonPathNumberValue("$.requestId").isEqualTo(fullItemDtoOut.getRequestId());
        assertThat(result).extractingJsonPathArrayValue("$.comments").extracting("id")
                .contains(fullItemDtoOut.getComments().get(0).getId());
        assertThat(result).extractingJsonPathArrayValue("$.comments").extracting("text")
                .contains(fullItemDtoOut.getComments().get(0).getText());
        assertThat(result).extractingJsonPathArrayValue("$.comments").extracting("authorName")
                .contains(fullItemDtoOut.getComments().get(0).getAuthorName());
        assertThat(result).extractingJsonPathArrayValue("$.comments").extracting("created")
                .contains(fullItemDtoOut.getComments().get(0).getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        assertThat(result).extractingJsonPathNumberValue("$.nextBooking.id")
                .isEqualTo(fullItemDtoOut.getNextBooking().getId());
        assertThat(result).extractingJsonPathNumberValue("$.nextBooking.bookerId")
                .isEqualTo(fullItemDtoOut.getNextBooking().getBookerId());
        assertThat(result).extractingJsonPathStringValue("$.nextBooking.start")
                .isEqualTo(fullItemDtoOut.getNextBooking().getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        assertThat(result).extractingJsonPathStringValue("$.nextBooking.end")
                .isEqualTo(fullItemDtoOut.getNextBooking().getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        assertThat(result).extractingJsonPathNumberValue("$.lastBooking.id")
                .isEqualTo(fullItemDtoOut.getLastBooking().getId());
        assertThat(result).extractingJsonPathNumberValue("$.lastBooking.bookerId")
                .isEqualTo(fullItemDtoOut.getLastBooking().getBookerId());
        assertThat(result).extractingJsonPathStringValue("$.lastBooking.start")
                .isEqualTo(fullItemDtoOut.getLastBooking().getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        assertThat(result).extractingJsonPathStringValue("$.lastBooking.end")
                .isEqualTo(fullItemDtoOut.getLastBooking().getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
    }


    @Test
    void equalsAndHashCodeTest() {
        FullItemDtoOut x = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        FullItemDtoOut y = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        assertThat(x.equals(y) && y.equals(x)).isTrue();
        assertThat(x.hashCode() == y.hashCode()).isTrue();
    }

    @Test
    void equals_shouldReturnFalseWhenIdsAreNotTheSame() {
        FullItemDtoOut x = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        FullItemDtoOut y = FullItemDtoOut.builder()
                .id(-1)
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        assertThat(x.equals(y)).isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenNamesAreNotTheSame() {
        FullItemDtoOut x = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        FullItemDtoOut y = FullItemDtoOut.builder()
                .id(item.getId())
                .name("other name")
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        assertThat(x.equals(y)).isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenDescriptionsAreNotTheSame() {
        FullItemDtoOut x = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        FullItemDtoOut y = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description("other description")
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        assertThat(x.equals(y)).isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenAvailabilitiesAreNotTheSame() {
        FullItemDtoOut x = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        FullItemDtoOut y = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(false)
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        assertThat(x.equals(y)).isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenRequestIdsAreNotTheSame() {
        FullItemDtoOut x = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        FullItemDtoOut y = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(-1)
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        assertThat(x.equals(y)).isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenNextBookingsAreNotTheSame() {
        FullItemDtoOut x = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        FullItemDtoOut y = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(ShortBookingDtoOut.builder()
                        .id(2)
                        .bookerId(booker.getId())
                        .startDate(now.plusDays(1))
                        .endDate(now.plusDays(2))
                        .build())
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        assertThat(x.equals(y)).isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenLastBookingsAreNotTheSame() {
        FullItemDtoOut x = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        FullItemDtoOut y = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(ShortBookingDtoOut.builder()
                        .id(2)
                        .bookerId(booker.getId())
                        .startDate(now.plusDays(1))
                        .endDate(now.plusDays(2))
                        .build())
                .comments(List.of(commentDtoOut))
                .build();

        assertThat(x.equals(y)).isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenCommentsAreNotTheSame() {
        FullItemDtoOut x = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(List.of(commentDtoOut))
                .build();

        FullItemDtoOut y = FullItemDtoOut.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(itemRequest.getId())
                .nextBooking(shortBookingDtoOut)
                .lastBooking(shortBookingDtoOut)
                .comments(Collections.emptyList())
                .build();

        assertThat(x.equals(y)).isFalse();
    }

    @Test
    void equalsTest() {
        assertThat(fullItemDtoOut.equals(fullItemDtoOut)).isTrue();
        assertThat(fullItemDtoOut.equals(null)).isFalse();
        assertThat(fullItemDtoOut.equals(new Object())).isFalse();
    }

    @Test
    void noArgsConstructorTest() {
        FullItemDtoOut itemDtoOut = new FullItemDtoOut();
        itemDtoOut.setId(item.getId());
        itemDtoOut.setName(item.getName());
        itemDtoOut.setDescription(item.getDescription());
        itemDtoOut.setAvailable(item.getAvailable());
        itemDtoOut.setRequestId(item.getRequest().getId());
        itemDtoOut.setNextBooking(shortBookingDtoOut);
        itemDtoOut.setLastBooking(shortBookingDtoOut);
        itemDtoOut.setComments(List.of(commentDtoOut));

        assertThat(itemDtoOut.getId()).isEqualTo(item.getId());
        assertThat(itemDtoOut.getName()).isEqualTo(item.getName());
        assertThat(itemDtoOut.getDescription()).isEqualTo(item.getDescription());
        assertThat(itemDtoOut.getAvailable()).isEqualTo(item.getAvailable());
        assertThat(itemDtoOut.getRequestId()).isEqualTo(item.getRequest().getId());
        assertThat(itemDtoOut.getNextBooking()).isEqualTo(shortBookingDtoOut);
        assertThat(itemDtoOut.getLastBooking()).isEqualTo(shortBookingDtoOut);
        assertThat(itemDtoOut.getComments()).isEqualTo(List.of(commentDtoOut));
    }

    @Test
    void allArgsConstructorTest() {
        FullItemDtoOut itemDtoOut = new FullItemDtoOut(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getRequest().getId(),
                shortBookingDtoOut,
                shortBookingDtoOut,
                List.of(commentDtoOut));

        assertThat(itemDtoOut.getId()).isEqualTo(item.getId());
        assertThat(itemDtoOut.getName()).isEqualTo(item.getName());
        assertThat(itemDtoOut.getDescription()).isEqualTo(item.getDescription());
        assertThat(itemDtoOut.getAvailable()).isEqualTo(item.getAvailable());
        assertThat(itemDtoOut.getRequestId()).isEqualTo(item.getRequest().getId());
        assertThat(itemDtoOut.getNextBooking()).isEqualTo(shortBookingDtoOut);
        assertThat(itemDtoOut.getLastBooking()).isEqualTo(shortBookingDtoOut);
        assertThat(itemDtoOut.getComments()).isEqualTo(List.of(commentDtoOut));
    }


    @Test
    void toStringTest() {
        assertThat(fullItemDtoOut.toString()).startsWith(fullItemDtoOut.getClass().getSimpleName());
    }
}