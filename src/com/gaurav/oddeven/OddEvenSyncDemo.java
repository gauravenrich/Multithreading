package com.gaurav.oddeven;

class OddEvenSync {
    int n;
    int counter = 15;

    public OddEvenSync(int n) {
        this.n = n;
    }

    public void displayOdd() {
        while ((counter < n)) {
            synchronized (this) {
                if (counter % 2 != 0) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Odd " + counter);
                counter++;
                this.notifyAll();
            }
        }
    }

    public void displayEven() {
        while ((counter < n)) {
            synchronized (this) {
                if (counter % 2 == 0) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("even " + counter);
                counter++;
                this.notifyAll();
            }
        }
    }
}

class OddEvenSyncThread extends Thread {
    String choice;
    OddEvenSync oddEvenSync;

    public OddEvenSyncThread(String choice, OddEvenSync oddEvenSync) {
        this.choice = choice;
        this.oddEvenSync = oddEvenSync;
    }

    @Override
    public void run() {
        if ("odd".equals(choice)) {
            oddEvenSync.displayOdd();
        } else if ("even".equals(choice)) {
            oddEvenSync.displayEven();
        }
    }
}

public class OddEvenSyncDemo {
    public static void main(String[] args) {
        OddEvenSync oddEvenSync = new OddEvenSync(500);
        Thread t1 = new OddEvenSyncThread("odd", oddEvenSync);
        Thread t2 = new OddEvenSyncThread("even", oddEvenSync);
        t2.start();
        t1.start();
    }
}
