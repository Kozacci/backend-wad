package pl.uwm.wateradventure.models.courses;

public enum CourseType {

    STERNIK_MOTOROWODNY("Sternik motorowodny"),
    MOTOROWODNY_STERNIK_MORSKI("Motorowodny sternik morski"),
    HOLOWANIE_NARCIARZA_I_OBIEKTOW_NAWODNYCH("Holowanie narciarza i obiektow nawodnych"),
    ZEGLARZ_JACHTOWY("Żeglarz jachtowy"),
    JACHTOWY_STERNIK_MORSKI("Jachtowy sternik morski");

    public final String enumValue;

    CourseType(String enumValue) {
        this.enumValue = enumValue;
    }

}
