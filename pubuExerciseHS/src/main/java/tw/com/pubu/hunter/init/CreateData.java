package tw.com.pubu.hunter.init;

import tw.com.pubu.hunter.service.MemberService;
import tw.com.pubu.hunter.service.ProductService;
import tw.com.pubu.hunter.service.ShoppingCartService;
import tw.com.pubu.hunter.service.impl.MemberServiceImpl;
import tw.com.pubu.hunter.service.impl.ProductServiceImpl;
import tw.com.pubu.hunter.service.impl.ShoppingCartServiceImpl;
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
		ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
		for (int i=1; i<=4; i++) {
			for(int j=1; j<=i; j++) {
				int no = shoppingCartService.add(i, j, i);
				HunterDebug.showKeyValue("newID in shoppingCart", no);
			}
		}
		
		
		
	}

}
