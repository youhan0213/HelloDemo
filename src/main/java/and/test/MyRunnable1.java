package and.test;

public class MyRunnable1 implements Runnable {

	@Override
	public void run() {
		for(int i = 0;i < 10;i++) {
			System.out.println("线程2第" + i + "次执行");
			Thread.yield();
		}
	}

}
