package and.test;

public class Person {
	
	private int age = 10;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("初始化年龄：" + age);
	}
	public int getAge(int age) {
		this.age = age;
		return age;
	}
	
}
