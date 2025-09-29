package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.practicum.shareit.booking.BookingStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDto {
    private Long id;

    @NotNull(message = "Дата начала бронирования обязательна!")
    private LocalDateTime start;

    @NotNull(message = "Дата окончания бронирования обязательна!")
    private LocalDateTime end;

    private BookingStatus status;

    private BookerShortDto booker;

    private ItemShortDto item;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookerShortDto {
        private Long id;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ItemShortDto {
        private Long id;
        private String name;
    }
}