package pl.uwm.wateradventure.controllers.events;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.services.events.EventsFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
class EventsController {

    private final EventsFacade eventsFacade;

}
