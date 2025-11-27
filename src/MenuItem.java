// MenuItem.java
public abstract class MenuItem {
    // Encapsulation: Atribut private
    private String nama;
    private double harga; // Menggunakan double sesuai instruksi
    private String kategori;

    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Abstract method (Polymorphism)
    public abstract void tampilMenu();

    // Getter dan Setter
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
}