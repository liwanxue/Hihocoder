import java.util.*;
class Node
{
	public char alpha;
	public ArrayList<Node> hm = new ArrayList<Node>(26);
	public Node getNode(char ch)
	{
		int index = ch-'a';
		if(index >= hm.size())
			return null;
		return hm.get(index);
	}
	public void setNode(char ch,Node tar){
		int index = ch-'a';
		for(int i = hm.size(); i <= index; i++)
			hm.add(null);
		hm.set(ch-'a', tar);
	}
	public long number = 0;
}
public class HihoCoder1014 {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		int storenum,findnum;
		storenum = Integer.parseInt(sc.nextLine());
		Node head = new Node();
		while(storenum > 0)
		{
			add(head,sc.nextLine());
			storenum --;
		}
		findnum = Integer.parseInt(sc.nextLine());
		while(findnum > 0)
		{
			System.out.println(find(head,sc.nextLine()));
			findnum --;
		}
		sc.close();
	}
	
	public static void add(Node head, String str)
	{
		Node start = head;
		int str_index = 0;
		while(str_index < str.length())
		{
			start.number ++;
			if(start.getNode(str.charAt(str_index)) == null)
			{
				Node next = new Node();
				next.alpha = str.charAt(str_index);
				start.setNode(str.charAt(str_index), next);
				start = next;
			}
			else
				start = start.getNode(str.charAt(str_index));
			str_index ++;
		}
		start.number ++;
	}
	
	public static long find(Node head, String str)
	{
		Node start = head;
		int str_index = 0;
		while(str_index < str.length())
		{
			if(start.getNode(str.charAt(str_index))!=null)
				start = start.getNode(str.charAt(str_index));
			else return 0;
			str_index ++;
		}
		return start.number;
		
	}
}
