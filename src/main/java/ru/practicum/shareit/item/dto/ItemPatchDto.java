package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPatchDto {
    private String name;
    private String description;
    private Boolean available;

    @AssertTrue(message = "Хотя бы одно поле должно быть указано для обновления")
    public boolean hasAtLeastOneValue() {
        return name != null || description != null || available != null;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public Optional<Boolean> getAvailable() {
        return Optional.ofNullable(available);
    }
}