package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

import static pl.uwm.wateradventure.models.learning.category.Category.*;

public class InvalidQuestionCategoryException extends RuntimeException {

    public static final String MESSAGE =
            String.format("Question category must be one of '%s', '%s', '%s', '%s', " +
                            "'%s', '%s', '%s', '%s', '%s', '%s'.",
                    PRZEPISY.enumValue, PODSTAWY_LOCJI.enumValue,
                    WIADOMOSCI_Z_METEOROLOGII.enumValue,
                    PODSTAWY_BUDOWY_JACHTOW.enumValue, SILNIKI_I_UKLADY_NAPEDOWE.enumValue,
                    WIADOMOSCI_Z_RATOWNICTWA_WODNEGO.enumValue,
                    POMOCE_NAWIGACYJNE.enumValue, OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM.enumValue,
                    PODSTAWOWE_PRZEPISY_PRAWA.enumValue, TEORIA_ZEGLOWANIA.enumValue);

    public InvalidQuestionCategoryException() {
        super(String.format(MESSAGE));
    }
}
