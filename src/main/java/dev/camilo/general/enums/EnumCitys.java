package dev.camilo.general.enums;

public enum EnumCitys {
    BOGOTA("Bogota"),
    ZIPAQUIRA("Zipaquira"),
    MEDELLIN("Medellin"),
    BUCARAMANGA("Bucaramana");

    private String name;

    EnumCitys(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
