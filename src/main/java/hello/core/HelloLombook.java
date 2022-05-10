package hello.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombook {
	private String name;
	private int age;

	public static void main(String[] args) {
		HelloLombook helloLombook = new HelloLombook();
		helloLombook.setName("abc");

//		String name = helloLombook.getName();
//		System.out.println("name = " + name);
		System.out.println("helloLombook = " + helloLombook);
	}

}
