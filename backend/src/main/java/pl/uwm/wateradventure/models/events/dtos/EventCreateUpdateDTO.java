package pl.uwm.wateradventure.models.events.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import pl.uwm.wateradventure.models.validators.EventCity;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record EventCreateUpdateDTO(String type,
                                   @NotNull(message = "'Cost' field must be filled.")
                                   @Positive(message = "'Cost' must be higher than zero.")
                                   Double cost,
                                   @NotNull(message = "'Date' field must be filled.")
                                   @Future(message = "'Date' must be future date.")
                                   LocalDateTime date,
                                   @NotNull(message = "'City' field must be filled.")
                                   @EventCity
                                   String city,
                                   @NotNull(message = "'Duration' must be filled.")
                                   LocalTime duration,
                                   @NotNull(message = "'Maximum participants number' must be filled.")
                                   @Positive(message = "'Maximum participants number' must be higher than zero.")
                                   Integer maxParticipantsNumber) {

    @Builder
    public EventCreateUpdateDTO {}
}
