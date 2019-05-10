package tw.com.pubu.hunter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tw.com.pubu.hunter.bean.MemberBean;
import tw.com.pubu.hunter.enums.LoginResult;
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
	public String loginCheck(Model model,
						HttpServletRequest request, HttpServletResponse response, 
						@RequestParam(value="loginAcc", defaultValue="") String account,
						@RequestParam(value="loginPwd", defaultValue="") String password
						) {
		LoginResult result = LoginResult.Error;
		
		//取得傳入值
		HunterDebug.showMessage("account", account);
		HunterDebug.showMessage("password", password);
		
		if (account.equals("")) result = LoginResult.AccountIsEmpty;
		else if (password.equals("")) result = LoginResult.AccountIsEmpty;

		//取得資料庫相同帳號
		
		/*
		//JDBC
		
			if (rs.next()) {//有此帳號
				if (loginPwd.equals(rs.getString("ctm_password"))) {
					result = LoginResult.OK;
					HttpSession session = request.getSession();
					session.setAttribute("loginName", account);
					session.setAttribute("loginId", rs.getInt("ctm_id"));
				}else result = LoginResult.AccountNotExist;
			} else result = LoginResult.AccountNotExist;
				
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		*/
		//回傳結果
		System.out.println("loginResult: " + result);
		model.addAttribute("loginResult", result);
//		request.setAttribute("result", result); //
		return "loginResult";
	}
	
	

}
