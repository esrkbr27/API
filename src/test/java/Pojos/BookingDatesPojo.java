package Pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)  //Bu annotation aynı levelda bilinmeyen verileri görmezden gelerek
//diğer verilerin bu class tipinde Pojo class'a çevrilmesine yarıyor.
public class BookingDatesPojo {

    //1.Tüm keyler için private variabllar oluşturuyoruz
    private String checkin;
    private String checkout;


//2.Constructor oluştur
public BookingDatesPojo() {


}

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}

