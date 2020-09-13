package javathread;


public class Pilot{
    private double totalTime;
    private String name;

    public Pilot(String name){
        this.name = name;
        this.totalTime = 0;
    }

    public double getTotalTime(){
        return totalTime;
    }

    public void setTotalTime(double val){
        totalTime = val;
    }

    public String getName(){
        return name;
    }

    public void setName(String newname){
        name = newname;
    }

    public void incrTotalTime(double val){
        totalTime += val;
    }
}