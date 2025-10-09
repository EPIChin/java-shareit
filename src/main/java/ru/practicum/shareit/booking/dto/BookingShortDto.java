package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingShortDto {
    private Long id;

    @NotNull(message = "ID пользователя обязательный!")
    private Long bookerId;

    @NotNull(message = "Дата начала бронирования обязательна!")
    private LocalDateTime start;

    @NotNull(message = "Дата окончания бронирования обязательна!")
    private LocalDateTime end;
}