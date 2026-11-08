
public class BankQueueSimulation {

    // Menyimpan informasi setiap nasabah
    static class Nasabah {
        private String nomorTiket;
        private String jenisLayanan;

        public Nasabah(String nomorTiket, String jenisLayanan) {
            this.nomorTiket = nomorTiket;
            this.jenisLayanan = jenisLayanan;
        }

        public String getNomorTiket() {
            return nomorTiket;
        }

        public String getJenisLayanan() {
            return jenisLayanan;
        }

        @Override
        public String toString() {
            return nomorTiket + " (" + jenisLayanan + ")";
        }
    }

    // Circular Queue menggunakan array biasa
    static class CircularNasabahQueue {
        private Nasabah[] data;
        private int front;
        private int rear;
        private int size;

        public CircularNasabahQueue(int kapasitas) {
            data = new Nasabah[kapasitas];
            front = 0;
            rear = -1;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == data.length;
        }

        public void enqueue(Nasabah nasabah) {
            if (isFull()) {
                System.out.println("Antrean penuh. " 
                    + nasabah.getNomorTiket() + " tidak dapat ditambahkan.");
                return;
            }

            rear = (rear + 1) % data.length;
            data[rear] = nasabah;
            size++;
        }

        public Nasabah dequeue() {
            if (isEmpty()) {
                return null;
            }

            Nasabah nasabah = data[front];
            data[front] = null;
            front = (front + 1) % data.length;
            size--;

            return nasabah;
        }

        public void display() {
            if (isEmpty()) {
                System.out.println("Kosong");
                return;
            }

            for (int i = 0; i < size; i++) {
                int index = (front + i) % data.length;
                System.out.print(data[index]);

                if (i < size - 1) {
                    System.out.print(" -> ");
                }
            }

            System.out.println();
        }

        public int getSize() {
            return size;
        }
    }

    // Mengatur antrean VIP dan Reguler
    static class BankQueueSystem {
        private CircularNasabahQueue vipQueue;
        private CircularNasabahQueue regularQueue;

        private int vipServed;
        private final int VIP_LIMIT = 2;

        private int nomorVIP = 1;
        private int nomorReguler = 1;

        public BankQueueSystem(int kapasitas) {
            vipQueue = new CircularNasabahQueue(kapasitas);
            regularQueue = new CircularNasabahQueue(kapasitas);
            vipServed = 0;
        }

        public void enqueueVIP() {
            String tiket = String.format("VIP-%02d", nomorVIP);
            Nasabah nasabah = new Nasabah(tiket, "VIP");

            int sebelum = vipQueue.getSize();
            vipQueue.enqueue(nasabah);

            if (vipQueue.getSize() > sebelum) {
                nomorVIP++;
                System.out.println(tiket + " masuk antrean VIP.");
            }
        }

        public void enqueueReguler() {
            String tiket = String.format("REG-%02d", nomorReguler);
            Nasabah nasabah = new Nasabah(tiket, "Reguler");

            int sebelum = regularQueue.getSize();
            regularQueue.enqueue(nasabah);

            if (regularQueue.getSize() > sebelum) {
                nomorReguler++;
                System.out.println(tiket + " masuk antrean reguler.");
            }
        }

        public Nasabah dequeue() {
            Nasabah dilayani;

            // Nasabah reguler mendapat giliran setelah
            // maksimal dua nasabah VIP jika reguler sedang menunggu.
            if (!regularQueue.isEmpty()
                    && (vipServed >= VIP_LIMIT || vipQueue.isEmpty())) {

                dilayani = regularQueue.dequeue();
                vipServed = 0;

            } else if (!vipQueue.isEmpty()) {

                dilayani = vipQueue.dequeue();
                vipServed++;

            } else if (!regularQueue.isEmpty()) {

                dilayani = regularQueue.dequeue();
                vipServed = 0;

            } else {
                System.out.println("Tidak ada nasabah dalam antrean.");
                return null;
            }

            System.out.println("Dilayani: " + dilayani);
            return dilayani;
        }

        public void displayQueue() {
            System.out.println("\n===== STATUS ANTREAN BANK =====");

            System.out.print("Antrean VIP     : ");
            vipQueue.display();

            System.out.print("Antrean Reguler : ");
            regularQueue.display();

            System.out.println("VIP dilayani berturut-turut: "
                + vipServed + "/" + VIP_LIMIT);

            System.out.println("================================");
        }
    }

public static void main(String[] args) {

    BankQueueSystem bank = new BankQueueSystem(10);

    System.out.println("========================================");
    System.out.println("       SISTEM ANTREAN BANK");
    System.out.println("========================================");
    System.out.println("Nama  : RISMANTO");
    System.out.println("NIM   : 2902811940");
    System.out.println("========================================\n");

    System.out.println("=== PENDAFTARAN NASABAH ===");

    bank.enqueueVIP();
    bank.enqueueReguler();
    bank.enqueueVIP();
    bank.enqueueVIP();
    bank.enqueueReguler();
    bank.enqueueVIP();
    bank.enqueueReguler();
    
        bank.displayQueue();

        System.out.println("\n=== PROSES PELAYANAN ===");

        while (true) {
            Nasabah hasil = bank.dequeue();

            if (hasil == null) {
                break;
            }
        }

        System.out.println("\n=== ANTREAN SELESAI ===");
        bank.displayQueue();
    }
}


