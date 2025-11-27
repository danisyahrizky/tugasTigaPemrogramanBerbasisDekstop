import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Menu menuRestoran = new Menu();

    public static void main(String[] args) {
        System.out.println("Selamat Datang di Sistem Manajemen Restoran");
        
        boolean isRunning = true;
        while (isRunning) {
            tampilkanMenuUtama();
            int pilihan = inputAngka("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    menuRestoran.tampilkanMenuRestoran();
                    break;
                case 2:
                    tambahMenuBaru();
                    break;
                case 3:
                    prosesPesanan();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("1. Tampilkan Daftar Menu");
        System.out.println("2. Tambah Item Menu (Admin)");
        System.out.println("3. Buat Pesanan (Pelanggan)");
        System.out.println("0. Keluar");
    }

    // --- FITUR ADMIN: MENAMBAH MENU ---
    private static void tambahMenuBaru() {
        System.out.println("\n--- Tambah Item Baru ---");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Diskon/Voucher");
        int tipe = inputAngka("Pilih tipe item: ");

        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();

        double harga = 0;
        if (tipe != 3) {
            harga = inputDouble("Masukkan Harga: ");
        } else {
            harga = inputDouble("Masukkan Nilai Potongan (Rp): ");
        }

        // Penerapan Polymorphism: Menambahkan objek berbeda ke list yang sama
        if (tipe == 1) {
            System.out.print("Jenis Makanan (cth: Kuah/Goreng): ");
            String jenis = scanner.nextLine();
            menuRestoran.tambahItem(new Makanan(nama, harga, jenis));
        } else if (tipe == 2) {
            System.out.print("Jenis Minuman (cth: Dingin/Panas): ");
            String jenis = scanner.nextLine();
            menuRestoran.tambahItem(new Minuman(nama, harga, jenis));
        } else if (tipe == 3) {
            menuRestoran.tambahItem(new Diskon(nama, harga));
        } else {
            System.out.println("Tipe tidak valid, pembatalan.");
        }
    }

    // --- FITUR PELANGGAN: MEMPROSES PESANAN ---
    private static void prosesPesanan() {
        System.out.println("\n--- Pesanan Baru ---");
        Pesanan pesananSaatIni = new Pesanan(); // Object baru untuk sesi ini
        menuRestoran.tampilkanMenuRestoran();

        if (menuRestoran.getDaftarMenu().isEmpty()) return;

        boolean memesan = true;
        while (memesan) {
            System.out.print("\nMasukkan nomor menu untuk dipesan (0 untuk selesai): ");
            int nomor = inputAngka("");

            if (nomor == 0) {
                memesan = false;
            } else {
                // Validasi index array
                // User input 1, tapi index array mulai dari 0. Jadi dikurang 1.
                int index = nomor - 1;
                
                if (index >= 0 && index < menuRestoran.getDaftarMenu().size()) {
                    // Mengambil objek dari daftar menu dan menambahkannya ke keranjang
                    MenuItem itemDipilih = menuRestoran.getDaftarMenu().get(index);
                    pesananSaatIni.tambahPesanan(itemDipilih);
                } else {
                    System.out.println("Nomor menu tidak ditemukan!");
                }
            }
        }

        // Cetak struk dan simpan ke file
        pesananSaatIni.tampilkanStruk();
    }

    // --- HELPER METHOD: ERROR HANDLING INPUT ---
    // Method ini menangani Exception jika user memasukkan huruf alih-alih angka
    private static int inputAngka(String pesan) {
        System.out.print(pesan);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Input salah. Masukkan angka bulat: ");
            }
        }
    }

    private static double inputDouble(String pesan) {
        System.out.print(pesan);
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Input salah. Masukkan angka (bisa desimal): ");
            }
        }
    }
}