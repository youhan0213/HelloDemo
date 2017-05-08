package com.springmvc.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentAnnotationTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ComponentAnnotationTest.class);
		context.refresh();
		InjectClass injectClass = context.getBean(InjectClass.class);
		injectClass.print();
		
	}
	@MyComponent
	public static class InjectClass{
		
		public void print(){
			System.out.println("Hello World !");
		}
	}
}
