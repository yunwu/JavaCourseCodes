package java0.conc0301.op;

public class Join2 {
    
    public static void main(String[] args) {
        Object oo = new Object();
    
        MyThread2 thread1 = new MyThread2("thread1 -- ");
        //oo = thread1;
        thread1.setOo(oo);
        thread1.start();

        synchronized (oo) {  // 这里用oo或thread1/this
            for (int i = 0; i < 100; i++) {
                try {
                    if (i % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " -- " + i);
                        //thread1.join();
                    } else {
                        oo.wait(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                oo.notifyAll();
            }
        }




    }
    
}

class MyThread2 extends Thread {
    
    private String name;
    private Object oo;
    
    public void setOo(Object oo) {
        this.oo = oo;
    }
    
    public MyThread2(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        try{
            synchronized (oo) { // 这里用oo或this，效果不同
                for (int i = 0; i < 100; i++) {
                    if (i%2 == 0){
                        System.out.println(name + i);
                    }else {
                        oo.wait(1);
                    }
                }
            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

    }
    
}