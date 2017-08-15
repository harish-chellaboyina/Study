package DesignQuestions.FactoryPattern;

public class FactoryPattern {
	
	public static ClassA createClassAObj() {
		return new ClassA();
	}
	
	public static ClassB createClassBObj() {
		return new ClassB();
	}
}
