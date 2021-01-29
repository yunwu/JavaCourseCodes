package java0.conc0301.op;

public class WaitAndNotifyTest {

    public static void main(String[] args){
        WaitAndNotifyProduct andNotifyProduct = new WaitAndNotifyProduct();

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        andNotifyProduct.add();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t1");
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        andNotifyProduct.sub();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t2");
            thread1.start();
            t2.start();



    }


    static class WaitAndNotifyProduct{

        int count = 1;

        public synchronized void add() throws InterruptedException {
            while (true){
                if (count < 10){
                    count++;
                    System.out.println(Thread.currentThread().getName() + "::::" + count);
                }else {
                    wait();
                    System.out.println(Thread.currentThread().getName() + "等待仓库空间。。。。");
                }
                notifyAll();
            }

        }

        public synchronized void sub() throws InterruptedException {
            while (true){
                if (count > 0){
                    count--;
                    System.out.println(Thread.currentThread().getName() + "::::" + count);
                }else {
                    wait();
                    System.out.println(Thread.currentThread().getName() + "等待工厂生产商品。。。");
                }
                notifyAll();
            }

        }
    }
}
