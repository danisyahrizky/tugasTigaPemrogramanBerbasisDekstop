import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pesanan {
    private ArrayList<MenuItem> keranjangBelanja;

    public Pesanan() {
        this.keranjangBelanja = new ArrayList<>();
    }

    public void tambahPesanan(MenuItem item) {
        keranjangBelanja.add(item);
        System.out.println(item.getNama() + " berhasil ditambahkan ke pesanan.");
    }

    public void tampilkanStruk() {
        double totalHarga = 0;
        double totalDiskon = 0;
        StringBuilder strukContent = new StringBuilder();

        System.out.println("\n=== STRUK PESANAN ===");
        strukContent.append("=== STRUK PESANAN ===\n");

        for (MenuItem item : keranjangBelanja) {
            if (item instanceof Diskon) {
                // Jika item adalah voucher diskon
                double potong = ((Diskon) item).getNilaiDiskon();
                totalDiskon += potong;
                String line = item.getNama() + " \t: -Rp " + potong;
                System.out.println(line);
                strukContent.append(line).append("\n");
            } else {
                // Jika Makanan atau Minuman
                totalHarga += item.getHarga();
                String line = item.getNama() + " \t: Rp " + item.getHarga();
                System.out.println(line);
                strukContent.append(line).append("\n");
            }
        }

        double totalBayar = totalHarga - totalDiskon;
        if (totalBayar < 0) totalBayar = 0;

        String separator = "-----------------------------";
        String txtTotal = "Total Bayar \t: Rp " + totalBayar;

        System.out.println(separator);
        System.out.println(txtTotal);
        
        strukContent.append(separator).append("\n").append(txtTotal);

        // Simpan struk ke file
        simpanStruk(strukContent.toString());
    }

    private void simpanStruk(String isiStruk) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("struk_pesanan.txt"))) {
            writer.write(isiStruk);
            System.out.println("\n(Struk telah disimpan ke struk_pesanan.txt)");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk: " + e.getMessage());
        }
    }
}