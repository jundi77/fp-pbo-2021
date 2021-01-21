# Final Project PBO Kelas G Tahun 2020

Sub-Bagian
- [Abstrak](#a-abstrak)
- [Deskrpsi Aplikasi](#b-deskripsi-aplikasi)
- [Cara Bermain](#c-cara-bermain)
- [Diagram Kelas](#d-diagram-kelas)
- [Referensi](#referensi)

## A. Abstrak

Final project dari mata kuliah Pemrograman Berbasis Objek Kelas G. Final project berupa penugasan pembuatan gim berkelompok.

## B. Deskripsi Aplikasi

- Aplikasi yang dibuat berupa permainan pertarungan bertema RPG dan "turn based" untuk sistem pertarungannya. 
- Dengan mengadopsi fitur berupa voice recognizer yang disediakan oleh `https://cmusphinx.github.io/` sebagai salah satu cara bermain.
- Tujuan akhir permainan adalah mengalahkan semua monster yang ada panda level tersebut.

## C. Cara Bermain

### Getting Started

- Pemain dapat memain kan karakter dengan menggunakan keyboad untuk berjalan mengitari area bermain dengan tombol `A` `S` `W` `D` dan jeda dengan tombol `ESCAPE`.
- Dalam mode jeda diberi dua pilihan berupa `Continue` untuk kembali ke permainan dan `Exit` untuk ke menu utama.
- Untuk dapat melawan musuh, bergeraklah dengan tombol `A` `S` `W` `D` menuju monster yang ada di dekat pemain. Saat mendekat nantinya akan masuk ke mode pertarungan.

### How To Fight

- Dalam mode pertarungan nantinya, pemain hanya bisa berinteraki dengan tombol `↑` dan `↓` untuk memilih cara menyerang dan  tombol `ESCAPE` untuk jeda.
- Terdapan beberapa antarmuka diantaranya :
  - Pilihan serangan.
  - Bar mana.
  - Monster.
- Serangan terdiri dari dua jenis `Serang` dan `Sihir`
  - `Serang` :Tombol ini berarti mengaktifkan serangan biasa dari pemain dengan nilai serangan yang tidak terlalu besar.
  - `Sihir` : Mengartikan pemain menyerang dengan serangan special yang nantinya akan ditampilkan sebuah kata dalam bahasa inggris, 
    untuk mengaktifkan serangan tersebut pemain harus mengucapkan kalimat tersebut. Jika berasil maka animasi serangan akan berjalan dan mana di `Bar Mana` akan berkurang.

### Whats Next ?

- Selesaikan level dengan mengalahkan semua monster yang ada di area tersebut.

## D. Diagram Kelas

### Referensi
- [https://cmusphinx.github.io/wiki/tutorialsphinx4/](https://cmusphinx.github.io/wiki/tutorialsphinx4/)
