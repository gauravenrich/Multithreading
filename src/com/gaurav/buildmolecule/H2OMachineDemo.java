package com.gaurav.buildmolecule;

import java.util.Arrays;
import java.util.Collections;

/**
 *   @author gsinha
 *   25 jan 2022
 *  Multithreaded H20 Molecule program
 */
class H2OMachine{
    String[] molecules;
    Object object;
    int count=0;
    public H2OMachine(){
        molecules=new String[3];
        object=new Object();
    }
    public void hydrogenAtom(){
        synchronized (object){
            while (Collections.frequency(Arrays.asList(molecules),"H")==2){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            molecules[count]="H";
            count++;
            if(count==3){
                for (String str:molecules){
                    System.out.println(str);
                }
                count=0;
                Arrays.fill(molecules,null);
            }
            object.notifyAll();

        }

    }
    public void oxygenAtom(){
        synchronized (object){
            while (Collections.frequency(Arrays.asList(molecules),"O")==1){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            molecules[count]="O";
            count++;
            if(count==3){
                Arrays.stream(molecules).forEach(t-> System.out.println(t));
                Arrays.fill(molecules,null);
                count=0;
            }
        }
    }

}

class H2OMachineThread extends Thread{
    String molecule;
    H2OMachine h2OMachine;
    public H2OMachineThread(String molecule,H2OMachine h2OMachine){
        this.molecule=molecule;
        this.h2OMachine=h2OMachine;
    }
    @Override
    public void run(){
        if("H".equals(molecule)){
            h2OMachine.hydrogenAtom();
        }
        if("O".equals(molecule)){
            h2OMachine.oxygenAtom();
        }
    }
}
public class H2OMachineDemo {
    public static void main(String[] args) {
        H2OMachine h2OMachine=new H2OMachine();
        H2OMachineThread t1=new H2OMachineThread("H",h2OMachine);
        H2OMachineThread t2=new H2OMachineThread("O",h2OMachine);
        H2OMachineThread t3=new H2OMachineThread("H",h2OMachine);
        H2OMachineThread t4=new H2OMachineThread("O",h2OMachine);
        H2OMachineThread t5=new H2OMachineThread("H",h2OMachine);
        H2OMachineThread t6=new H2OMachineThread("H",h2OMachine);

        t1.start();
        t3.start();
        t2.start();
        t5.start();
        t4.start();
        t6.start();
    }
}
