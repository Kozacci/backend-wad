package pl.uwm.wateradventure.models.events.dtos;

import lombok.Builder;

import java.util.Date;

public record EventEntityDTO(String type,
                             Boolean isPaid,
                             Double cost,
                             Date date,
                             String address,
                             String ordererEmail,
                             String ordererFirstName,
                             String ordererLastName,
                             String ordererPhoneNumber) {

    @Builder
    public EventEntityDTO {}

}
