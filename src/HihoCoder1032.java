import java.util.Scanner;

public class HihoCoder1032 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.nextLine();
		while(num > 0)
		{
			int[] result = getPalindromic(getPreString(sc.nextLine()));
			int max = 0;
			for(int i : result)
			{
				if(i - 1 > max) max = i - 1;
			}
			System.out.println(max);
			num --;
		}
	}

	public static String getPreString(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append('$');
		for (char c : str.toCharArray()) {
			sb.append('#');
			sb.append(c);
		}
		sb.append('#');
		// sb.append('\0');
		return sb.toString();
	}

	public static int[] getPalindromic(String str) {
		int[] p = new int[str.length()];
		int mx = 0, id = 0;
		for (int i = 0; i < p.length ; i++)
			p[i] = 1;
		//p[0] = 0;p[1] = 0;
		for (int i = 1; i < str.length(); i++) {
			if (mx > i)
				p[i] = Math.min(p[2 * id - i], mx - i);
			while(i + p[i] < str.length() && str.charAt(i + p[i]) == str.charAt(i - p[i])) p[i] ++;
			if(i + p[i] > mx){
				mx = i + p[i];
				id = i;
			}
		}
		return p;
	}
}
