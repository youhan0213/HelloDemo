package and.test;

public class Test {
	public static void main(String[] args) {
		Thread t1 = new Thread1();
		Thread t2 = new Thread(new MyRunnable1());
		t2.start();
		t1.start();
		
		
	}
}
