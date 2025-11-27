// Diskon.java
public class Diskon extends MenuItem {
    private double nilaiDiskon;

    public Diskon(String nama, double nilaiDiskon) {
        // Harga diset 0 karena ini bukan barang yg dibeli, tapi pengurang
        super(nama, 0, "Diskon"); 
        this.nilaiDiskon = nilaiDiskon;
    }

    @Override
    public void tampilMenu() {
        System.out.println("[Promo]   " + getNama());
        System.out.println("          Potongan: Rp " + nilaiDiskon);
    }

    public double getNilaiDiskon() { return nilaiDiskon; }
}