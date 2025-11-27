// Minuman.java
public class Minuman extends MenuItem {
    private String jenisMinuman; // Atribut tambahan khusus Minuman

    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }

    @Override
    public void tampilMenu() {
        System.out.println("[Minuman] " + getNama() + " (" + jenisMinuman + ")");
        System.out.println("          Harga: Rp " + getHarga());
    }

    // Getter Setter khusus
    public String getJenisMinuman() { return jenisMinuman; }
    public void setJenisMinuman(String jenisMinuman) { this.jenisMinuman = jenisMinuman; }
}