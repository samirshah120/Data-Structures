/** Student Name : Samir Jesal Shah
 			CWID : 10445681**/


public class BinaryNumber {
	private int[] data;
	private boolean overflow;
	
	//Initializing binary number consisting of 0's
	public BinaryNumber(int length)
	{
		data = new int[length];
		overflow = false;
	}
	
	//Converting String Binary number into array of integers
	public BinaryNumber(String str)
	{
		this.data = new int[str.length()];
		this.overflow = false;
		for(int i=0;i<str.length();i++)
		{
			data[i] = Character.getNumericValue(str.charAt(i));
		}
	}
	
	//Returns length of binary number
	public int getLength()
	{
		return data.length;
	}
	
	//Returns the digit of binary number on that particular index
	public int getDigit(int index)
	{
		if(index >= 0 && index < data.length)
		{
			return data[index];
		}
		else
		{
			System.out.println("Index out of bounds");
			return -1;
		}
		
	}
		
	//Converts binary number into decimal
	public int toDecimal()
	{
		int decimal = 0;
		for(int i=0;i<data.length;i++)
		{
			 decimal =  decimal + (int) (data[i] * Math.pow(2.0, i));
		}
		return decimal;
	}
	
	//Shifts the binary number by amount
	public void shiftR(int amount)
	{
		String str = "";
		for(int i=0;i<data.length;i++)
		{
			str = str + data[i];
		}
		for(int i=0;i<amount;i++)
		{
			str = "0" + str; 
		}
		System.out.println(str);
	}
	
	//Adds two binary numbers
	public void add(BinaryNumber aBinaryNumber)
	{
		int sum = 0;
		int carry = 0;
		if(aBinaryNumber.getLength() != data.length)
		{
			System.out.println("length of binary numbers are not equal");
		}
		else
		{
			for(int i=0;i<data.length;i++)
			{
				sum = data[i] + aBinaryNumber.getDigit(i) + carry; 
				data[i] = sum % 2;
				carry = sum / 2;
			}
			
			if(carry == 1)
			{
				overflow = true;
				System.out.print("Result of Addition in binary = ");
				for(int i=0;i<data.length;i++)
				{
					System.out.print(data[i]);
				}
				System.out.println(carry);
//				int result = toDecimal();
//				System.out.print("Result of Addition in decimal = ");
//				System.out.println(result + (int)Math.pow(2.0, data.length));
			}
			else
			{
				overflow = false;
				System.out.print("Result of Addition in binary = ");
				for(int i=0;i<data.length;i++)
				{
					System.out.print(data[i]);
				}
				System.out.println();
//				int result = toDecimal();
//				System.out.print("Result of Addition in decimal = ");
//				System.out.println(result);
			}
			 
			
		}
	}
	//Prints String value of binary number
	public String toString()
	{
		String binaryNumber = "";
		for(int i=0;i<data.length;i++)
		{
			binaryNumber = binaryNumber + data[i];
		}
		if(overflow == true)
			return "overflow";
		else
			return binaryNumber;
	}
	
	//resets overflow flag
	public void clearOverflow()
	{
		overflow = false;
		System.out.println("overflow flag reset to false");
	}
	
	public static void main(String[] args) {
		BinaryNumber binarynumberObj = new BinaryNumber(5);
		BinaryNumber binarynumberObj1 = new BinaryNumber("10110");
		System.out.println("Length of Binary number = "+binarynumberObj1.getLength());
		System.out.println("Digit at index is " +binarynumberObj1.getDigit(4));
		System.out.println("Decimal value of Binary number = " +binarynumberObj1.toDecimal());
		System.out.print("Result of Shift Right = ");
		binarynumberObj1.shiftR(4);
		BinaryNumber aBinaryNumber = new BinaryNumber("11100");
		binarynumberObj1.add(aBinaryNumber);
		System.out.println("To string method result " +binarynumberObj1.toString());
		binarynumberObj1.clearOverflow();
		}

}
