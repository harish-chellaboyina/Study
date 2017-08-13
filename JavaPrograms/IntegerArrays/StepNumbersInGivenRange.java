package IntegerArrays;

public class StepNumbersInGivenRange {
	
	public int getStepNumber(int firstPositionNumber, int numOfDigits) {
		int num = firstPositionNumber;
		int i = 1;
		while (i < numOfDigits) {
			num = num * 10;
			num += firstPositionNumber;
		}
		return 0;
	}
	
	public int findStepNumbersInGivenRange(int n1, int n2) {
		
		return 0;
	}
}