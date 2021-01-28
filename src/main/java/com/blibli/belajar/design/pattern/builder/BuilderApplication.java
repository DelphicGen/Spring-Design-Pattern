package com.blibli.belajar.design.pattern.builder;

import lombok.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BuilderApplication {
    @Data
    @Builder // generate class mahasiswa builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Mahasiswa {
        private String nim;
        private String nama;
        private String alamat;
        private Date tanggalLahir;
        @Singular("hobi")
        private List<String> hobi;
    }

    public static void main(String[] args) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNim("1212");
        mahasiswa.setNama("Eko");
        mahasiswa.setAlamat("Indonesia");
        mahasiswa.setTanggalLahir(new Date());
        mahasiswa.setHobi(Arrays.asList("Game", "Coding"));

        Mahasiswa mahasiswa2 = Mahasiswa.builder()
                .nim("1212")
                .nama("Eko")
                .alamat("Indonesia")
                .tanggalLahir(new Date())
//                .hobi(Arrays.asList("Game", "Coding"))
                .hobi("Game")
                .hobi("Coding")
                .build(); // balikkan object mahasiswa

        System.out.println(mahasiswa);
        System.out.println(mahasiswa2);

    }
}
