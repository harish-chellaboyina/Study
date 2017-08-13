package DesignQuestions.ProxyPattern;

public class ProxyClass implements ProxyInterface{
	
	BaseClass obj;
	ProxyClass(){
		obj = new BaseClass();
	}

	public void readContent() {
		obj.readContent();
	}

	public void writeContent() {
		obj.writeContent();
	}

}
