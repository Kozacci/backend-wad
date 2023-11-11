package pl.uwm.wateradventure.models.learning.category;

import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidQuestionCategoryException;

import java.util.stream.Stream;

public enum Category {

    PRZEPISY("Przepisy"),
    PODSTAWY_LOCJI("Podstawy locji"),
    WIADOMOSCI_Z_METEOROLOGII("Wiadomości z zakresu meteorologii"),
    PODSTAWY_BUDOWY_JACHTOW("Podstawy budowy jachtów motorowodnych"),
    SILNIKI_I_UKLADY_NAPEDOWE("Silniki i układy napędowe"),
    WIADOMOSCI_Z_RATOWNICTWA_WODNEGO("Wiadomości z zakresu ratownictwa wodnego"),
    POMOCE_NAWIGACYJNE("Pomoce nawigacyjne"),
    OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM("Ochrona wód przed zanieczyszczeniem"),
    PODSTAWOWE_PRZEPISY_PRAWA("Podstawowe przepisy prawa drogi na morskich i śródlądowych drogach wodnych"),
    TEORIA_ZEGLOWANIA("Teoria żeglowania");

    public final String enumValue;

    Category(String enumValue) {
        this.enumValue = enumValue;
    }

    public static Category getCategory(String givenValue) {
        return Stream.of(values())
                .filter(category -> category.enumValue.equals(givenValue))
                .findFirst()
                .orElseThrow(InvalidQuestionCategoryException::new);
    }
}
