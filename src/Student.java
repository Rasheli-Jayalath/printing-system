public class Student implements Runnable{

    private String name;
    private ThreadGroup threadGroup;
    private Printer printer;

    public Student(String name, ThreadGroup threadGroup, Printer printer) {
        super();
        this.name = name;
        this.threadGroup = threadGroup;
        this.printer = printer;
    }

    @Override
    public void run() {
        Document document[] = new Document[5];
        document[0] = new Document("Doc001","6SENG006C_CWK",2);
        document[1] = new Document("Doc002","6COSC023C_CWK01_PP",3);
        document[2] = new Document("Doc003","6COSC023C_CWK02_PSPD",7);
        document[3] = new Document("Doc004","6FORM041C_CWK",5);
        document[4] = new Document("Doc005","Final_Project_Draft",6);
        int noOfPages = 0;

        for (Document doc: document ){
            printer.printDocument(doc);
            noOfPages += doc.getNumberOfPages();
            int randSec = ((int)(Math.random() * 100 +1));
            try {
                Thread.sleep(randSec);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName()+"Finished Printing : 5 Documents, "+noOfPages+" pages");

    }
}
