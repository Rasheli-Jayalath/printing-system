public class PrintingSystem {


    public static void main(String[] args) {
        ServicePrinter laserPrinter = new LaserPrinter("laser Printer",1,200,200,0);
        ThreadGroup studentGroup = new ThreadGroup("Students");
        ThreadGroup technicianGroup = new ThreadGroup("Technicians");


        Runnable student1 = new Student("St1",studentGroup,laserPrinter);
        Runnable student2 = new Student("St2",studentGroup,laserPrinter);
        Runnable student3 = new Student("St3",studentGroup,laserPrinter);
        Runnable student4 = new Student("St4",studentGroup,laserPrinter);

        Thread studentThread1 = new Thread(studentGroup,student1,"St1");
        Thread studentThread2 = new Thread(studentGroup,student2,"St2");
        Thread studentThread3 = new Thread(studentGroup,student3,"St3");
        Thread studentThread4 = new Thread(studentGroup,student4,"St4");

        Runnable paperTechnician = new PaperTechnician("PaperTec",technicianGroup,laserPrinter);
        Runnable tonerTechnician = new TonerTechnician("TonerTec",technicianGroup,laserPrinter);

        Thread paperTechThread = new Thread(technicianGroup,paperTechnician,"PaperTec");
        Thread tonerTechThread = new Thread(technicianGroup,tonerTechnician,"TonerTec");

        studentThread1.start();
        studentThread2.start();
        studentThread3.start();
        studentThread4.start();

        paperTechThread.start();
        tonerTechThread.start();


    }
}