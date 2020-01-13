import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @author samir
 *
 */
public class Anagrams {

	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
	67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	/**
	 * 
	 */
	public Anagrams ()
	{
		letterTable = new HashMap<Character,Integer>();
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	/**
	 * 
	 */
	private void buildLetterTable()
	{
		Character[] ch = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		int i=0;
		while(i<ch.length)
		{
			letterTable.put(ch[i], primes[i]);
			i++;
		}
		
	}
	/**
	 * 
	 * @param s
	 */
	private void addWord(String s)
	{
		if (anagramTable.containsKey(myHashCode(s))) 
		{
			ArrayList<String> temp = anagramTable.get(myHashCode(s));
			temp.add(s);
			anagramTable.replace(myHashCode(s), temp);
		} 
		else 
		{
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(s);
			anagramTable.put(myHashCode(s), temp);
		}
	}
	/**
	 * 
	 * @param s
	 * @return
	 */
	private Long myHashCode(String s)
	{
		int i = 0;
		long key = 1;
		s = s.toLowerCase();
		while (i < s.length()) {
			Character a = s.charAt(i);
			key = key * letterTable.get(a);
			i++;
		}
		return key;
		
	}
	/**
	 * 
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException 
	{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); String strLine;
		while ((strLine = br.readLine()) != null) { 
			this.addWord(strLine);
		} 
		br.close();
	}
	/**
	 * 
	 * @return
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries()
	{
		ArrayList<Map.Entry<Long,ArrayList<String>>> temp = new ArrayList<Map.Entry<Long,ArrayList<String>>>(); 
		Iterator<Entry<Long, ArrayList<String>>> itr = anagramTable.entrySet().iterator();
		int maxsize = 0;
		while(itr.hasNext())
		{
			Entry<Long, ArrayList<String>> tmp1 = itr.next();
			int size = tmp1.getValue().size();
			if(size > maxsize)
			{
				temp.clear();
				temp.add(tmp1);
				maxsize = size;
			}
			else if(size == maxsize)
			{
				temp.add(tmp1);
			}
		}
		return temp;
	}
	public static void main(String[] args) {
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime(); 
		try {
			a.processFile("words_alpha.txt"); 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		int length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000); 
		long key = maxEntries.get(0).getKey();
		System.out.println("Time: "+ seconds);
		System.out.println("Key of max anagrams: " + key);
		System.out.println("List of max anagrams: "+ maxEntries.get(0).getValue()); 
		System.out.println("Length of list of max anagrams: "+ length);
		}

}
