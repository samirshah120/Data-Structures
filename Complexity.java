public class Complexity {
	//method1 time complexity O(n^2).
	public void method1(int n)
	{
		int counter = 1;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				System.out.println("Operation1 "+counter);
				counter++;
			}
			
		}
	}
	//method2 time complexity O(n^3).
	public void method2(int n)
	{
		int counter = 1;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				for(int k=0;k<n;k++)
				{
					System.out.println("Operation2 "+counter);
					counter++;
				}
			}
			
		}
	}
	//method3 time complexity O(log n).
	public void method3(int n)
	{
		int counter = 1;
		for(int i=1;i<n;i = i*2)
		{
			System.out.println("Operation3 "+counter);
			counter++;
		}
	}
	//method4 time complexity O(n log n).
	public void method4(int n)
	{
		int counter = 1;
		for(int i=0;i<n;i++)
		{
			for(int j=1;j<n;j=j*2)
			{
				System.out.println("Operation4 "+counter);
				counter++;
			}
			
		}
	}
	//method2 time complexity O(log(log n)).
	public void method5(int n) 
	{
        int counter = 1;
        for (int i=2; i<n; i =i * i) 
        {
            System.out.println("Operation5 " + counter);
            counter++;
        }
    }
	//method 6 is used to calculate the value of nth fibonacci number which has complexity O(2^n)
	public static int method6(int n)
	{
		if(n <= 1)
		return n;
		else
		return method6(n-1) + method6(n-2);
	}
	
	
	public static void main(String[] args) {
		Complexity complexityObj = new Complexity();
		complexityObj.method1(4);
		complexityObj.method2(4);
		complexityObj.method3(8);
		complexityObj.method4(8);
		complexityObj.method5(16);
		int n = 10;
		System.out.println(n+"th fibonacci number is " + method6(n));
		
	}

}
