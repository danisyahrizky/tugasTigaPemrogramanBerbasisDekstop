// Makanan.java
public class Makanan extends MenuItem {
    private String jenisMakanan; // Atribut tambahan khusus Makanan

    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }

    @Override
    public void tampilMenu() {
        System.out.println("[Makanan] " + getNama() + " (" + jenisMakanan + ")");
        System.out.println("          Harga: Rp " + getHarga());
    }
    
    // Getter Setter khusus
    public String getJenisMakanan() { return jenisMakanan; }
    public void setJenisMakanan(String jenisMakanan) { this.jenisMakanan = jenisMakanan; }
}