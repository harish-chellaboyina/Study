package Numbers;

public class ConvertAllZerosToFivesInGivenNumber {
	
	public int convertZerosToFivesInNumber(int number, int digitTobeChanged, int finalDigit) {
		
		int divisor = 1, negativeIdentity = number/Math.abs(number);
		
		number = negativeIdentity * number;
		
		if (number == digitTobeChanged)
			number = finalDigit;
		
		else {
			while (divisor <= number) {
				int digit  = (number/divisor) % 10;
				if (digit == digitTobeChanged)
					number += (finalDigit * divisor);
				divisor *= 10;
			}
		}
		return number * negativeIdentity;
	}
	
	public static void main(String args[]) {
		ConvertAllZerosToFivesInGivenNumber obj = new ConvertAllZerosToFivesInGivenNumber();
		System.out.println(obj.convertZerosToFivesInNumber(1520340, 0, 5));
	}

}
