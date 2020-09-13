package javathread;

public class Racer implements Runnable{
    private Pilot pilot;

    public Racer(Pilot pilot){
        this.pilot = pilot;
    }

    public void run(){

        double time = 0;
        int lap = 0;

        System.out.println(String.format("Start pilot %s", pilot.getName()));

        while(lap < 10){

            time =  Math.random()*5 + 1;

            System.out.println(String.format("Pilot %s, Lap %d, time %.2f, total %.2f\n", 
                                            pilot.getName(), 
                                            lap, time, 
                                            pilot.getTotalTime()));

            try{
                Thread.sleep((long)time);
            }
            catch(InterruptedException e){
            }
            
            pilot.incrTotalTime(time);

            System.out.println(String.format("Pilot %s, Lap %d, time %.2f, total %.2f\n", 
                                            pilot.getName(), 
                                            lap, time, 
                                            pilot.getTotalTime()));
            lap ++;
        }
        System.out.println(String.format("End pilot %s", pilot.getName()));
    }
}