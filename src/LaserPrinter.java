public class LaserPrinter implements ServicePrinter {

    private String name;
    private int id;
    private int currentPaperLevel;
    private int currentTonerLevel;
    private int documentPrinted;
    private boolean paperRefilled = false; //to keep track of call to refill paper() is successful or not
    private boolean toneReplaced = false; //to keep track of call to toner replaced()

    public LaserPrinter(String name, int id, int currentPaperLevel, int currentTonerLevel, int documentPrinted) {
        super();
        this.name = name;
        this.id = id;
        this.currentPaperLevel = currentPaperLevel;
        this.currentTonerLevel = currentTonerLevel;
        this.documentPrinted = documentPrinted;
    }

    public boolean isPaperRefilled() {
        return paperRefilled;
    }

    public boolean isToneReplaced() {
        return toneReplaced;
    }

    @Override
    public synchronized void printDocument(Document document) {
        while (!(document.getNumberOfPages()
                <= currentPaperLevel && document.getNumberOfPages() <= currentTonerLevel)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Finished Printing:" + documentPrinted + "Documents ,"+currentPaperLevel + "pages");
        this.currentPaperLevel -= document.getNumberOfPages();
        this.currentTonerLevel -= document.getNumberOfPages();
        documentPrinted++;
        notifyAll();
    }

    @Override
    public synchronized void replaceTonerCartridge() {
        // code attempt no :1
//        while (currentTonerLevel >= (Minimum_Toner_Level)) {
//            try {
//                wait(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        this.currentTonerLevel = PagesPerTonerCartridge;

        //code attempt no :2
//        int count = 0;
//        while (count < 2){
//            // x <= 200 =>
//            if (this.currentTonerLevel <= (Minimum_Toner_Level)){
//                this.currentTonerLevel = PagesPerTonerCartridge;
//            }else{
//                if (count ==1){
//                    break;
//                }
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            count++;
//        }

        //code attempt 3
        boolean tried = false;
//        boolean toneReplaced = false;
        while (this.currentTonerLevel > (Minimum_Toner_Level -1)){
            if (tried){
                break;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.currentTonerLevel < (Minimum_Toner_Level)){
            this.currentTonerLevel = PagesPerTonerCartridge;
            this.toneReplaced = true;
        }
        notifyAll();
    }

    @Override
    public synchronized void refillPaper() {
        boolean tried = false;
        this.paperRefilled = false;
        int count = 0;
        //code attempt 1

//        while (currentPaperLevel > (Full_Paper_Tray - SheetsPerPack) && count < 2) {
//            try {
//                wait(5000);
//                count += 1;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        while (!(this.currentPaperLevel <= (Full_Paper_Tray - 50) && count < 1)) {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            count++;
//        }
//        if (this.currentPaperLevel <= (Full_Paper_Tray - 50)) {
//            this.currentPaperLevel += 50;
//        }

        //code attempt 2
        // situation 1 : current paper level is x and count = 0
        // 0< 2 = true
//        while (count < 2){
//            // x <= 200 =>
//            if (this.currentPaperLevel <= (Full_Paper_Tray - 50)){
//                this.currentPaperLevel += 50;
//            }else{
//                if (count ==1){
//                    break;
//                }
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            count++;
//        }

        //code attempt 3
        while (!(this.currentPaperLevel <= (Full_Paper_Tray - 50))) {
            if (tried) {
                break;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tried = true;
        }

        if (this.currentPaperLevel <= (Full_Paper_Tray - 50)) {
            this.currentPaperLevel += 50;
            this.paperRefilled = true;
        }
        notifyAll();
    }

    @Override
    public String toString() {
        return "LaserPrinter{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", currentPaperLevel=" + currentPaperLevel +
                ", currentTonerLevel=" + currentTonerLevel +
                ", documentPrinted=" + documentPrinted +
                '}';
    }
}
