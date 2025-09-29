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
public class BookingDto {
    private Long id;

    @NotNull(message = "Дата начала бронирования обязательна!")
    private LocalDateTime start;

    @NotNull(message = "Дата окончания бронирования обязательна!")
    private LocalDateTime end;

    @NotNull(message = "ID предмета обязательный!")
    private Long itemId;

    private Long bookerId;

    private BookingStatus status;
}