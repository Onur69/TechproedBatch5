package techproedbatch5;

import org.codehaus.jackson.annotate.JsonProperty;

public class BookingDates {
    @JsonProperty("checkin")
    private String checkin;
    @JsonProperty("checkout")
    private String checkout;

    @JsonProperty("checkin")
    public String getCheckin() {
        return checkin;
    }

    @JsonProperty("checkin")
    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    @JsonProperty("checkout")
    public String getCheckout() {
        return checkout;
    }

    @JsonProperty("checkout")
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
//alttaki 3. madde gerceklesti
    public BookingDates() {
    }
//alttaki 4. madde gerceklesti
    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
//alttaki 5. madde gerceklestirildi
    @Override
    public String toString() {
        return "BookingDates{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
/*Pojoda olmasi gerekenler
1-Her variable icin mutlaka getter ve setter olmali
2-Json da key olanlar icin variable olusturun ve bu variablelerin access modifierleri private olmali
3-Parametresiz bir constructor olmali
4-Olusturdugumuz variableler icin parametreli constructorlari olusturalim
5-toString methodu olusturacagiz
 */