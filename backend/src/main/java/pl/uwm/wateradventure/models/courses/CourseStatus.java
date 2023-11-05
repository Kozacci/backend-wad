package pl.uwm.wateradventure.models.courses;

public enum CourseStatus {

    NIEROZPOCZETY("Nierozpoczęty"),
    ROZPOCZETY("Rozpoczęty"),
    ZAKONCZONY("Zakończony");

    public final String enumValue;

    CourseStatus(String enumValue) {
        this.enumValue = enumValue;
    }

}
