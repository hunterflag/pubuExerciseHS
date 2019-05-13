package unUsed;

import tw.com.pubu.hunter.service.ShoppingCartsService;
import tw.com.pubu.hunter.service.impl.ShoppingCartsServiceImpl;
import tw.idv.hunter.tool.HunterDebug;

public class CreateData {

	public static void main(String[] args) {
		//insert new Member
		MemberService memberService = new MemberServiceImpl();
		for (int i=1; i<=4; i++) {
			int no = memberService.add("tester"+i, "1234");
			HunterDebug.showKeyValue("newID in member", no);
		}

		//insert new Product
		ProductService productService = new ProductServiceImpl();
		for (int i=1; i<=4; i++) {
			int no = productService.add("Book"+i, Double.valueOf(i*100+i*10+i));
			HunterDebug.showKeyValue("newID in product", no);
		}
		
		//insert new ShoppingCart
		ShoppingCartsService shoppingCartService = new ShoppingCartsServiceImpl();
		for (int i=1; i<=4; i++) {
			for(int j=1; j<=i; j++) {
//				int no = shoppingCartService.add(i, j, i);
//				HunterDebug.showKeyValue("newID in shoppingCart", no);
			}
		}
		
		
		
	}

}
