import java.util.Scanner;


public class HihoCoder1033 {
	static public int k = 0, l = 0, r = 0;
	static public Long M = (long) (Math.pow(10, 9)+7);
	static public Long[] powarr;
	static public String str1,str2,str;
	static public int length,tmplen;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		str1 = sc.next();
		str2 = sc.next();
		powarr = new Long[str2.length()+1];
		powarr[1] = 1L;
		for(int i = 2; i < powarr.length; i++)
			powarr[i] = (10 * powarr[i - 1])%M; 
		k = sc.nextInt();
		System.out.println((getSum(str2) - getSum(String.valueOf(Long.parseLong(str1) - 1))+M)%M);
	}
	
	static Node[][][] nodes = new Node[20][400][2];
	
	public static Node dfs(int d, int target, int flag, boolean end)
	{
		if(d == 0)
		{
			Node tmp = null;
			if(target == 200)
			{
				tmp = new Node();
				tmp.number = 1L;
				tmp.sum = 0L;
				nodes[d][target][flag == -1?0:1] = tmp;
			}
			
			return tmp;
		}
		if(!end && d != tmplen && nodes[d][target][flag == -1?0:1] != null)
			return nodes[d][target][flag == -1?0:1];

		int digit = end?(str.charAt(length - d )-'0'):9;
		int start = (d == tmplen)?1:0;
//		Long sum_temp = 0L;
		Node tmp = new Node();
		tmp.number = 0L;
		tmp.sum = 0L;
		int cflag = -1 * flag;
		for(int j = start; j <= digit; j++)
		{
			Node t = dfs(d-1,target - flag*j,cflag,(end && (j == digit)));
			if(t != null)
			{
				tmp.number += t.number;
				tmp.sum += (t.sum + (((j * t.number) % M) * powarr[d])%M)%M;
//				sum_temp += t.sum%M;
				tmp.sum %= M;
			}
		}
		if( d != tmplen && !end)
			nodes[d][target][flag == -1?0:1] = tmp;
		return tmp;
	}
	public static Long getSum(String strtmp)
	{
		length = strtmp.length();
		str = strtmp;
		Long sum =  0L;
		for(int i = 0; i < str.length(); i ++)
		{
			tmplen = i+1;
			Node t =dfs(i + 1, k + 200, 1, (i == (strtmp.length() - 1)));
			if(t != null)
			{
				sum += t.sum % M;
				sum = sum % M;
			}
			
		}
		return sum;
	}
	static class Node{
		public Long number;
		public Long sum;
	}
	
}


