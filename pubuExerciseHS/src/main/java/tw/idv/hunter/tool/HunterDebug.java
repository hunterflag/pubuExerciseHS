package tw.idv.hunter.tool;

public class HunterDebug {
	private static int traceNo = 0;
	private static boolean traceOn = true;
	static {
		traceOn = false;
	}
	
	public static void showKeyValue(String key, String value) {
		System.out.println("<<<<<<<<---------------------------------------------------------------");
		System.out.println(key + " : " + value);
		System.out.println(">>>>>>>>---------------------------------------------------------------");
	}
	
	public static void showKeyValue(String key, int value) { showKeyValue(key, ""+value); }
	
	public static void showMessage(String target, String msg) {
		System.out.println("<<<<<<<<---------------------------------------------------------------");
		System.out.println("target: " + target +",\t msg: " + msg);
		System.out.println(">>>>>>>>---------------------------------------------------------------");
	}
	
	public static void showMessage(String msg) { showMessage("", msg); }

	/* 是想顯示的上層,index=2
	 * https://blog.csdn.net/zxygww/article/details/45533347
	 */
	public static void traceMessage() {
		if(traceOn) {
			traceNo++;
			System.out.println("<<<<<<<< -----------------------------------------------------------");
			System.out.println("traceNo=" + traceNo + ",\t" 
					+"method: " + Thread.currentThread().getStackTrace()[2].getMethodName() + "(),\t"
					+"Class: " + Thread.currentThread().getStackTrace()[2].getClassName() + ",\t"
					);
			System.out.println(">>>>>>>> -----------------------------------------------------------");
		}
	}
}
