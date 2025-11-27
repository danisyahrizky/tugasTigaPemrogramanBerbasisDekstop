import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<MenuItem> daftarMenu;
    private final String namaFile = "menu.txt";

    public Menu() {
        this.daftarMenu = new ArrayList<>();
        muatMenuDariFile(); // Otomatis muat data saat aplikasi mulai
    }

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
        simpanMenuKeFile(); // Auto-save setiap ada penambahan
    }

    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

    public void tampilkanMenuRestoran() {
        System.out.println("\n=== DAFTAR MENU RESTORAN ===");
        if (daftarMenu.isEmpty()) {
            System.out.println("(Menu masih kosong)");
        } else {
            for (int i = 0; i < daftarMenu.size(); i++) {
                System.out.print((i + 1) + ". ");
                daftarMenu.get(i).tampilMenu(); // Polymorphism berjalan disini
            }
        }
    }

    // --- FILE I/O: MENYIMPAN DATA ---
    public void simpanMenuKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for (MenuItem item : daftarMenu) {
                // Format: Tipe;Nama;Harga;AtributKhusus
                String line = "";
                if (item instanceof Makanan) {
                    line = "Makanan;" + item.getNama() + ";" + item.getHarga() + ";" + ((Makanan) item).getJenisMakanan();
                } else if (item instanceof Minuman) {
                    line = "Minuman;" + item.getNama() + ";" + item.getHarga() + ";" + ((Minuman) item).getJenisMinuman();
                } else if (item instanceof Diskon) {
                    line = "Diskon;" + item.getNama() + ";" + ((Diskon) item).getNilaiDiskon() + ";-";
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan menu: " + e.getMessage());
        }
    }

    // --- FILE I/O: MEMUAT DATA ---
    public void muatMenuDariFile() {
        File file = new File(namaFile);
        if (!file.exists()) return; // Jika file belum ada, abaikan

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length >= 3) {
                    String tipe = data[0];
                    String nama = data[1];
                    double harga = Double.parseDouble(data[2]);
                    String info = (data.length > 3) ? data[3] : "-";

                    if (tipe.equals("Makanan")) {
                        daftarMenu.add(new Makanan(nama, harga, info));
                    } else if (tipe.equals("Minuman")) {
                        daftarMenu.add(new Minuman(nama, harga, info));
                    } else if (tipe.equals("Diskon")) {
                        // Khusus diskon, nilai di kolom harga dianggap nilai potongan
                        daftarMenu.add(new Diskon(nama, harga)); 
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Gagal memuat menu: " + e.getMessage());
        }
    }
}