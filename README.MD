# Final Project PBO Kelas G Tahun 2020

Mempersembahkan: **Witch Who Speaks**!
![Gambar Main Menu](https://cdn.discordapp.com/attachments/801843033846186034/801843334469124096/unknown.png "Main Menu")

----
Sub-Bagian

- [Abstrak](#abstrak)
- [Deskrpsi Aplikasi](#deskripsi-aplikasi)
- [Cara Bermain](#cara-bermain)
  
  - [Getting started](#getting-started)
  - [How To Fight](#how-to-fight)
  - [Target?](#target-?)

- [Diagram Kelas](#diagram-kelas)
- [Referensi](#referensi)

----

## Abstrak

Final project dari mata kuliah Pemrograman Berbasis Objek Kelas G. Final project berupa penugasan pembuatan gim berkelompok.

## Deskripsi Aplikasi

- Aplikasi yang dibuat berupa permainan pertarungan bertema RPG dan "turn based" untuk sistem pertarungannya. Pemain memainkan karakter penyihir dengan musuh adalah karakter monster.
- Dengan mengadopsi fitur berupa voice recognizer yang disediakan oleh `https://cmusphinx.github.io/` sebagai salah satu cara bermain.
- Tujuan akhir permainan adalah mengalahkan semua monster yang ada pada level tersebut.

## Cara Bermain

### Getting Started

[Demo Bermain](https://s2.gifyu.com/images/2021-01-21-23-37-46.gif)

- Pemain dapat memainkan karakter dengan menggunakan keyboad untuk berjalan mengitari area bermain dengan tombol `A` `S` `W` `D` dan jeda dengan tombol `ESCAPE`.
- Dalam mode jeda diberi dua pilihan berupa `Continue` untuk kembali ke permainan dan `Exit` untuk ke menu utama.![Diagram kelas]

- Untuk dapat melawan musuh, bergeraklah dengan tombol `A` `S` `W` `D` menuju monster yang ada di dekat pemain. Saat mendekat nantinya akan masuk ke mode pertarungan.

### How To Fight

![Gambar Pertarungan](https://cdn.discordapp.com/attachments/801843033846186034/801854097598054410/unknown.png "Gambar Pertarungan")

- Dalam mode pertarungan nantinya, pemain bisa berinteraki dengan tombol `↑` dan `↓` untuk memilih cara menyerang.
- Terdapan beberapa antarmuka diantaranya :
  - Pilihan serangan. (gambar pilihan)
  - Bar mana. (bar mana)
  - Monster.(monster)

- Serangan terdiri dari dua jenis `Serang` dan `Sihir`
  - `Serang` :Tombol ini berarti mengaktifkan serangan biasa dari pemain dengan nilai serangan yang tidak terlalu besar.
  - `Sihir` : Mengartikan pemain menyerang dengan serangan special yang nantinya akan ditampilkan sebuah kata dalam bahasa inggris, untuk mengaktifkan serangan tersebut pemain harus mengucapkan kalimat tersebut. Jika berhasil maka animasi serangan akan berjalan dan mana di `Bar Mana` akan berkurang.![Sihir](https://cdn.discordapp.com/attachments/801843033846186034/801854264183226378/unknown.png "Sihir")

### Target ?

- Selesaikan level dengan mengalahkan semua monster yang ada.

## Diagram Kelas

![Diagram kelas](https://cdn.discordapp.com/attachments/794871248173662218/801803751328972800/WWSClass.png "Diagram Kelas di Game Witch Who Speaks")

### Referensi

- [https://cmusphinx.github.io/wiki/tutorialsphinx4/](https://cmusphinx.github.io/wiki/tutorialsphinx4/)
