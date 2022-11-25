public class PaperTechnician implements Runnable{

    private String name;
    private ThreadGroup group;
    private ServicePrinter printer;

    public PaperTechnician(String name, ThreadGroup group, ServicePrinter printer) {
        super();
        this.name = name;
        this.group = group;
        this.printer = printer;
    }

    @Override
    public void run() {
        //code attempt 1
//        for (int i=0; i< 3; i++){
//            printer.refillPaper();
//            int noOfSecs = ((int)(Math.random() * 100 +1));
//            try {
//                Thread.sleep(noOfSecs);
//            } catch (InterruptedException e) {
//               e.printStackTrace();
//            }
//        }

        int count = 0 ;
        for (int i=0; i< 3; i++){
            printer.refillPaper();
            if (((LaserPrinter)printer).isPaperRefilled()){
                count ++;
            }
            int noOfSecs = ((int)(Math.random() * 100 +1));
            try {
                Thread.sleep(noOfSecs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Paper Technician Finished, packs of paper used: "+count);
    }
}
