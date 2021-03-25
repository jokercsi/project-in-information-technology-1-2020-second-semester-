//15817028 kim jibin

package p3;

public class ShowMyCodes {

	public static void main(String[] args) {  // static method
//		String name = "kim jibin";
//		for(int i = 0; i < name.length(); i++)
//		System.out.printf("[%c]=%d\n", name.charAt(i), (int) name.charAt(i));
//		}
	
		StringBuffer name = new StringBuffer("kim jibin");
		for(int i = 0; i < name.length(); i++)
		System.out.printf("[%c]=%d\n", name.charAt(i), (int) name.charAt(i));
		}
	
}
