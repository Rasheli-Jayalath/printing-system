public class TonerTechnician implements Runnable {

    private String name;
    private ThreadGroup group;
    private ServicePrinter printer;

    public TonerTechnician(String name, ThreadGroup group, ServicePrinter printer) {
        this.name = name;
        this.group = group;
        this.printer = printer;
    }

    @Override
    public void run() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            printer.replaceTonerCartridge();
            if (((LaserPrinter) printer).isToneReplaced()) {
                count++;
            }
            int noOfSecs = ((int) (Math.random() * 100 + 1));
            try {
                Thread.sleep(noOfSecs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Toner Technician Finished, cartridges replaced: " + count);
    }
}
