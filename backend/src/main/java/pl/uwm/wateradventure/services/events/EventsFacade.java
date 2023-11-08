package pl.uwm.wateradventure.services.events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.services.events.crud.EventsCRUDService;

@Component
@RequiredArgsConstructor
public class EventsFacade {

    private final EventsCRUDService eventsCRUDService;


}
