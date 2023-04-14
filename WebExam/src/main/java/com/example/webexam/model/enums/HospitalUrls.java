package com.example.webexam.model.enums;

public enum HospitalUrls {

    HOPE_URL("https://upload.wikimedia.org/wikipedia/commons/e/eb/Hope_Building%2C_Salford_Royal_Hospital_-_geograph.org.uk_-_2586383.jpg"),
    ISUL_URL("https://trud.bg/public/images/articles/2020-11/%D1%83%D0%BC%D0%B1%D0%B0%D0%BB_%D1%86%D0%B0%D1%80%D0%B8%D1%86%D0%B0_%D0%B9%D0%BE%D0%B0%D0%BD%D0%B0_-_%D0%B8%D1%81%D1%83%D0%BB_3556714952462417095_big.jpg"),

    TOKUDA_URL("https://acibademcityclinic.bg/images/librariesprovider2/tokuda-outsde/tokuda_outside-(1)ca9abea4acd54194b7831efd4f457ff1.jpeg?sfvrsn=784fffc1_9"),
    NADEZHDA_URL("https://pbs.twimg.com/media/EOYyqskW4AActyz.jpg"), //OR https://nadezhdahospital.com/wp-content/uploads/2021/09/Screenshot-2021-09-16-at-19.35.23-1024x692.png
    SOFIAMED_URL("https://hospitalsofiamed.bg/images/theme/sofmed.jpg"),
    ALEKSANDROVSKA_URL("https://glasnews.bg/news/2021/07/07/aleksandrovska-bolnitsa-veche-e-novo-926.jpg"),
    SERDIKA_URL("https://serdika.com/wp-content/uploads/2019/06/%D0%A1%D0%B3%D1%80%D0%B0%D0%B4%D0%B0_.jpg"),
    MAYO_URL("https://images.unsplash.com/photo-1596541223130-5d31a73fb6c6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bWF5byUyMGNsaW5pY3xlbnwwfHwwfHw%3D&w=1000&q=80");


    public final String hospitalUrl;

    HospitalUrls(String hospitalUrl) {
        this.hospitalUrl = hospitalUrl;
    }

    public String getHospitalUrl() {
        return hospitalUrl;
    }
}
