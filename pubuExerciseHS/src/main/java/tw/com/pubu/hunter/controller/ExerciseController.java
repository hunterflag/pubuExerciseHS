package tw.com.pubu.hunter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.pubu.hunter.bean.MemberBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.enums.LoginResult;
import tw.com.pubu.hunter.service.CustomersService;
import tw.com.pubu.hunter.service.ProductsService;
import tw.com.pubu.hunter.service.impl.CustomersServiceImpl;
import tw.com.pubu.hunter.service.impl.ProductsServiceImpl;
import tw.idv.hunter.tool.HunterDebug;

@Controller
public class ExerciseController {
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value="/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@RequestMapping(value="/loginCheck", method=RequestMethod.POST)
	public String loginCheck(Model model, HttpServletRequest request, HttpServletResponse response, 
						@RequestParam(value="loginAcc", defaultValue="") String account,
						@RequestParam(value="loginPwd", defaultValue="") String password
						) {
		
		//登入檢查 (由 Hibernate 取資料庫)
		LoginResult result = LoginResult.Error;
		CustomersService service = new CustomersServiceImpl();
		result = service.login(account, password);
		
		if(result == LoginResult.OK) {
			HttpSession session = request.getSession();
			session.setAttribute("loginName", account);
			session.setAttribute("loginId", service.getIdByAccount(account));
		}

		//回傳結果
		model.addAttribute("loginResult", result);
		return "loginResult";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
		String logoutResult = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("loginName") == null ) {
			logoutResult="未登入, 不須登出! ";
		}else {
			logoutResult="已登出! ";
			session.invalidate();
		}
		model.addAttribute("logoutResult", logoutResult);
		return "logout";
	}
	
	@RequestMapping(value="/showProductList", method=RequestMethod.GET)
	public String prepareProductList(Model model, HttpServletRequest request, HttpServletResponse response) {
		ProductsService service = new ProductsServiceImpl();
		List<ProductsBean> list = service.getAlls();
		
		model.addAttribute("pdts", list);
		HunterDebug.traceMessage();
		return "showProductList";
	}
	
	@RequestMapping(value="/addToShoppingCart", method=RequestMethod.GET)
	public String addToShoppingCart(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="ctm_id", defaultValue="0") int ctm_id,
			@RequestParam(value="pd_id", defaultValue="0") int pd_id,
			@RequestParam(value="pd_price", defaultValue="0") int pd_price
			) {
		HunterDebug.showKeyValue("ctm_id", ctm_id);
		
		ProductsService service = new ProductsServiceImpl();
		List<ProductsBean> list = service.getAlls();
		
		model.addAttribute("pdts", list);
		return "showShoppingCart";
	}
	
	
	

}
