package tw.com.pubu.hunter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.enums.LoginResult;
import tw.com.pubu.hunter.service.CustomersService;
import tw.com.pubu.hunter.service.OrderDetailsService;
import tw.com.pubu.hunter.service.OrdersService;
import tw.com.pubu.hunter.service.ProductsService;
import tw.com.pubu.hunter.service.ShoppingCartsService;

@Controller
public class ExerciseController {
	@Autowired
	private CustomersService customersService;
	@Autowired
	private ProductsService productsService;
	@Autowired
	private ShoppingCartsService shoppingCartsService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderDetailsService orderDetailsService;

	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(Model model, HttpServletRequest request,
			@RequestParam(value = "loginAcc", defaultValue = "") String account,
			@RequestParam(value = "loginPwd", defaultValue = "") String password) {
		// 登入檢查 (由 Hibernate 取資料庫)
		LoginResult result = LoginResult.Error;
		result = customersService.login(account, password);

		if (result == LoginResult.OK) {
			HttpSession session = request.getSession();
			session.setAttribute("loginName", account);
			session.setAttribute("loginId", customersService.getIdByAccount(account));
		}

		// 回傳結果
		model.addAttribute("loginResult", result);
		return "loginResult";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		String logoutResult = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("loginName") == null) {
			logoutResult = "未登入, 不須登出! ";
		} else {
			logoutResult = "已登出! ";
			session.invalidate();
		}
		model.addAttribute("logoutResult", logoutResult);
		return "logout";
	}

	@RequestMapping(value = "/showProductList", method = RequestMethod.GET)
	public String showProductList(Model model, HttpServletRequest request) {
		List<ProductsBean> list = productsService.getAlls();

		model.addAttribute("pdts", list);
		return "showProductList";
	}

	@RequestMapping(value = "/addToShoppingCart", method = RequestMethod.GET)
	public String addToShoppingCart(Model model, @RequestParam(value = "ctm_id", defaultValue = "0") int ctm_id,
			@RequestParam(value = "pd_id", defaultValue = "0") int pd_id) {
		shoppingCartsService.add(ctm_id, pd_id);

		List<ShoppingCartsBean> list = shoppingCartsService.getItemsByCustomer(ctm_id);
		model.addAttribute("scs", list);
		return "showShoppingCart";
	}

	@RequestMapping(value = { "/getDatasForShowShoppingCart" }, method = RequestMethod.GET)
	public String getDatasForShowShoppingCart(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loginId") == null)
			return "showShoppingCart";
		int ctm_id = (int) request.getSession().getAttribute("loginId");

		List<ShoppingCartsBean> list = shoppingCartsService.getItemsByCustomer(ctm_id);
		model.addAttribute("scs", list);
		return "showShoppingCart";
	}

	@RequestMapping(value = "/removeItemFromShoppingCart")
	public String removeItemFromShoppingCart(@RequestParam(name = "sc_id", defaultValue = "0") int sc_id) {
		shoppingCartsService.removeById(sc_id);

		return "showShoppingCart";
	}

	@RequestMapping(value = "/clearShoppingCartByCustomer")
	public String clearShoppingCartByCustomer(@RequestParam(name = "ctm_id", defaultValue = "0") int ctm_id) {
		shoppingCartsService.clearByCustomer(ctm_id);

		return "showShoppingCart";
	}

	@RequestMapping(value = "/updateShoppingCartItem", method = RequestMethod.POST)
	public String updateShoppingCartItem(@RequestParam(name = "sc_id", defaultValue = "0") int sc_id,
			@RequestParam(name = "sc_number", defaultValue = "0") int sc_number) {
		shoppingCartsService.updateNumberOfItem(sc_id, sc_number);

		return "showShoppingCart";
	}

	@RequestMapping(value = "/shoppingCartConfirmOrder", method = RequestMethod.GET)
	public String shoppingCartConfirmOrder(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loginId") == null)
			return "showShoppingCart";

		int ctm_id = (int) request.getSession().getAttribute("loginId");
		shoppingCartsService.confirmToOrder(ctm_id);

		return "showShoppingCart";
	}

	@RequestMapping(value = "/showOrderList", method = RequestMethod.GET)
	public String showOrderList(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("loginId") == null)
			return "showOrderList";

		int ctm_id = (int) request.getSession().getAttribute("loginId");
		List<OrdersBean> list = ordersService.getAllsByCustomer(ctm_id);
		model.addAttribute("ods", list);

		return "showOrderList";
	}

	@RequestMapping(value = "/getOrderDetailsById", method = RequestMethod.GET)
	public String getOrderDetailsById(Model model, @RequestParam(name = "od_id", defaultValue = "0") int od_id) {
		List<OrderDetailsBean> list = orderDetailsService.getAllsById(od_id);
		model.addAttribute("oddts", list);

		return "showOrderDetailsList";
	}

}
