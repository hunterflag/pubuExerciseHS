package tw.idv.hunter.tool;

public class HunterDebug {
	//TODO 把開關改寫在 .properties
	/****  追蹤狀態開關  ****/
	private static boolean traceOn = true;	
//	private static boolean traceOn = false;	

	private static int traceNo = 0;		
	private static int pos = 2;
	private static String msg = "none";
	
	public static void showKeyValue(String key, String value) {
		System.out.println("<<<<<<<<---------------------------------------------------------------");
		System.out.printf("key: %s\nvalue:%s\n", key, value);
		System.out.println(">>>>>>>>---------------------------------------------------------------");
	}
	public static void showKeyValue(String key, int value) { showKeyValue(key, ""+value); }
	
	public static void showMessage(String obj, String msg) {
		System.out.println("<<<<<<<<---------------------------------------------------------------");
		System.out.printf("obj: %s\nvalue:%s\n", obj, msg);
		System.out.println(">>>>>>>>---------------------------------------------------------------");
	}
	public static void showMessage(String msg) { showMessage("", msg); }

	/* 是想顯示的上層,index=2
	 * https://blog.csdn.net/zxygww/article/details/45533347
	 */
	public static void traceMessage(int depth) {
		StackTraceElement[] arr = Thread.currentThread().getStackTrace();
		depth = Math.min(depth, arr.length);
		if(!traceOn) return;
		if(depth > 0) {
			traceNo++;
			System.out.printf("<<<<<<<< traceNo: %d, \t Depth: %d -----------------------------------------------------------\n", 
								traceNo, 
								arr.length);
			for(int i=1; i<depth; i++) {
					System.out.println("\ti=" + i + "\t"
							+"method: " + arr[i].getMethodName() + "(),\t"
							+"Class: " + arr[i].getClassName());
			}
			System.out.println(">>>>>>>> -----------------------------------------------------------");
		}
	}
	public static void traceMessage() {
		if(!traceOn) return; 
		traceNo++;
		System.out.printf("<<<<<<<< traceNo: %d, \t Depth: %d -----------------------------------------------------------\n", 
							traceNo, 
							Thread.currentThread().getStackTrace().length);
		System.out.println("msg: " + msg + "\t"
							+"method: " + Thread.currentThread().getStackTrace()[pos].getMethodName() + "(),\t"
							+"Class: " + Thread.currentThread().getStackTrace()[pos].getClassName());
		System.out.println(">>>>>>>> -----------------------------------------------------------");
	}
	/**
	 * 
	 * @param traceSwitch
	 */
	public static void traceMessage(boolean traceSwitch) {
		traceOn = traceSwitch;
	}
	public static void traceMessage(String msg) {
		HunterDebug.msg = msg;
		HunterDebug.pos++;
		traceMessage();
		HunterDebug.pos--;
		HunterDebug.msg="none";
	}
	
}