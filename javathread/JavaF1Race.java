package javathread;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;

public class JavaF1Race {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            if (args.length == 0) { // file attached to system.in
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);

                if (!br.ready()) {// file was not set via argument nor system.in
                    System.out.println("File must be informed via argument or set via system.in");
                    System.out.println("java JavaF1Race filename.extension\njava JavaF1Race < filename.extension");
                    return;
                }

                ArrayList<Pilot> pilots = new ArrayList<Pilot>();

                br.lines().forEach(pl -> pilots.add(new Pilot(pl)));

                start(pilots);

                br.close();
                isr.close();

            } else {// sent via args
                FileReader fr = new FileReader(args[0]);
                BufferedReader br = new BufferedReader(fr);

                ArrayList<Pilot> pilots = new ArrayList<Pilot>();

                br.lines().forEach(pl -> pilots.add(new Pilot(pl)));

                start(pilots);

                br.close();
                fr.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void start(List<Pilot> pilots) {
        List<Thread> tl = new ArrayList<Thread>();
        for (Pilot p : pilots) {
            tl.add(new Thread(new Racer(p)));
        }

        for(Thread t : tl){
            t.start();
        }

        try {
            for(var t:tl){
                t.join();
            }
        } catch (InterruptedException e) {

        }
        System.out.println("Race over!\nFinal times:");

        for(var p:pilots){
            System.out.println(String.format("Pilot %s, total time: %.2f", p.getName(), p.getTotalTime()));
        }

        System.out.println(String.format("Winner: %s", pilots
                                            .stream()
                                            .min(Comparator.comparing(Pilot::getTotalTime))
                                            .orElseThrow(NoSuchElementException::new)
                                            .getName()));
    }
}
