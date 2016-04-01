import java.util.Scanner;

public class HihoCoder1015 {

	public static void main(String[] args) {
		// System.out.println(strStr("DFDGGFF", "FsF"));
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		while (n > 0) {
			String str = sc.nextLine();
			System.out.println(strStr(sc.nextLine(), str));
			// String[] strs = sc.nextLine().split(" ");
			// int result = Integer.parseInt(strs[0]) +
			// Integer.parseInt(strs[1]);
			// System.out.println(result);
			n--;
		}
		sc.close();

	}

	public static int strStr(String haystack, String needle) {
		int i = 0,result = 0;
		int hlength = haystack.length();
		int nlength = needle.length();
		if (nlength == 0) {
			return result;
		} else {
			int[] form = KMPform(needle);
			if (i <= hlength - nlength) {
				int j = 0;
				while (j < nlength && i <= hlength - nlength) {//i为源字符串的从第i个字符开始匹配。 j为匹配到模式串的前j位都是相等的。
					if (haystack.charAt(i + j) != needle.charAt(j)) {
						if (j == 0) {
							i++;
							j = -1;
						} else {
							i = i + j - form[j - 1] - 1;// form[j-1]表示前一个相等元素后移
														// j-1是最后一个匹配字符
							j = form[j - 1];
						}
					} else if (j == nlength - 1) {
						result++;
						i = i + j - form[j];
						if (i > hlength - nlength)
							return result;
						j = form[j];
					}
					j++;
				}
			}
			return result;
		}
	}

	public static int[] KMPform(String str) {
		/*知道前k个的form值， 对于i->n的所有元素，找相等的字符，记录下form[i]的值 */
		int[] form = new int[str.length()];
		form[0] = -1;
		for (int i = 1; i < form.length; i++) {
			int index = form[i - 1];// 上一个元素的前一个相等串
			while (index >= 0 && str.charAt(i) != str.charAt(index + 1))// 找到相等的字符
			{
				index = form[index];
			}
			if (str.charAt(i) == str.charAt(index + 1)) {
				form[i] = index + 1;
			} else// 没有找到相等字符，或index<0
			{
				form[i] = -1;
			}
		}
		return form;
	}

}
