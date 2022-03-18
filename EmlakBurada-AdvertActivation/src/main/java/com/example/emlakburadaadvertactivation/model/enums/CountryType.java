package com.example.emlakburadaadvertactivation.model.enums;

public enum CountryType {
    ADANA("Adana"),
    ADIYAMAN("Adıyaman"),
    AFYONKARAHISAR("Afyonkarahisar"),
    AGRI("Ağrı"),
    AMASYA("Amasya"),
    ANKARA("Ankara"),
    ANTALYA("Antalya"),
    ARTVIN("Artvin"),
    AYDIN("Aydın"),
    BALIKESIR("Balıkesir"),
    BILECIK("Bilecik"),
    BINGOL("Bingöl"),
    BITLIS("Bitlis"),
    BOLU("Bolu"),
    BURDUR("Burdur"),
    BURSA("Bursa"),
    CANAKKALE("Çanakkale"),
    CANKIRI("Çankırı"),
    CORUM("Çorum"),
    DENIZLI("Denizli"),
    DIYARBAKIR("Diyarbakır"),
    EDIRNE("Edirne"),
    ELAZIG("Elazığ"),
    ERZINCAN("Erzincan"),
    ERZURUM("Erzurum"),
    ESKISEHIR("Eskişehir"),
    GAZIANTEP("Gaziantep"),
    GIRESUN("Giresun"),
    GUMUSHANE("Gümüşhane"),
    HAKKARI("Hakkari"),
    HATAY("Hatay"),
    ISPARTA("Isparta"),
    MERSIN("Mersin"),
    ISTANBUL("İstanbul"),
    IZMIR("İzmir"),
    KARS("Kars"),
    KASTAMONU("Kastamonu"),
    KAYSERI("Kayseri"),
    KIRKLARELI("Kırklareli"),
    KIRSEHIR("Kırşehir"),
    KOCAELI("Kocaeli"),
    KONYA("Konya"),
    KUTAHYA("Kütahya"),
    MALATYA("Malatya"),
    MANISA("Manisa"),
    KAHRAMANMARAS("Kahramanmaraş"),
    MARDIN("Mardin"),
    MUGLA("Muğla"),
    MUS("Muş"),
    NEVSEHIR("Nevşehir"),
    NIGDE("Niğde"),
    ORDU("Ordu"),
    RIZE("Rize"),
    SAKARYA("Sakarya"),
    SAMSUN("Samsun"),
    SIIRT("Siirt"),
    SINOP("Sinop"),
    SIVAS("Sivas"),
    TEKIRDAG("Tekirdağ"),
    TOKAT("Tokat"),
    TRABZON("Trabzon"),
    TUNCELI("Tunceli"),
    SANLIURFA("Şanlıurfa"),
    USAK("Uşak"),
    VAN("Van"),
    YOZGAT("Yozgat"),
    ZONGULDAK("Zonguldak"),
    AKSARAY("Aksaray"),
    BAYBURT("Bayburt"),
    KARAMAN("Karaman"),
    KIRIKKALE("Kırıkkale"),
    BATMAN("Batman"),
    SIRNAK("Şırnak"),
    BARTIN("Bartın"),
    ARDAHAN("Ardahan"),
    IGDIR("Iğdır"),
    YALOVA("Yalova"),
    KARABUK("Karabük"),
    KILIS("Kilis"),
    OSMANIYE("Osmaniye"),
    DUZCE("Düzce");

    private String displayName;

    CountryType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
