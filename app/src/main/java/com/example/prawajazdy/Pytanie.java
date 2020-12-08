package com.example.prawajazdy;

public class Pytanie {
    private String media;
    private String numer_pytania;
    private String odpowiedz_A;
    private String odpowiedz_B;
    private String odpowiedz_C;
    private String poprawna_odp;
    private String pytanie;

    public Pytanie() {

    }

    public Pytanie(String media, String numer_pytania, String odpowiedz_A, String odpowiedz_B,
                   String odpowiedz_C, String poprawna_odp, String pytanie) {
        this.media = media;
        this.numer_pytania = numer_pytania;
        this.odpowiedz_A = odpowiedz_A;
        this.odpowiedz_B = odpowiedz_B;
        this.odpowiedz_C = odpowiedz_C;
        this.poprawna_odp = poprawna_odp;
        this.pytanie = pytanie;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getNumer_pytania() {
        return numer_pytania;
    }

    public void setNumer_pytania(String numer_pytania) {
        this.numer_pytania = numer_pytania;
    }

    public String getOdpowiedz_A() {
        return odpowiedz_A;
    }

    public void setOdpowiedz_A(String odpowiedz_A) {
        this.odpowiedz_A = odpowiedz_A;
    }

    public String getOdpowiedz_B() {
        return odpowiedz_B;
    }

    public void setOdpowiedz_B(String odpowiedz_B) {
        this.odpowiedz_B = odpowiedz_B;
    }

    public String getOdpowiedz_C() {
        return odpowiedz_C;
    }

    public void setOdpowiedz_C(String odpowiedz_C) {
        this.odpowiedz_C = odpowiedz_C;
    }

    public String getPoprawna_odp() {
        return poprawna_odp;
    }

    public void setPoprawna_odp(String poprawna_odp) {
        this.poprawna_odp = poprawna_odp;
    }

    public String getPytanie() {
        return pytanie;
    }

    public void setPytanie(String pytanie) {
        this.pytanie = pytanie;
    }


}
