package com.example.demo.controller;

import com.example.demo.entity.Favorite;
import com.example.demo.entity.Page;
import com.example.demo.service.*;
import com.example.demo.util.ListPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:8081") // 允许跨域请求的源
public class MainController {
	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;
	@Autowired
	FavoriteService favoriteService;
	@Autowired
	PageService pageService;
	@Autowired
	ReviewsService reviewsService;
	@Autowired
	PushService pushService;

	@RequestMapping("/index")
	public ArrayList<Page> index(HttpServletRequest request) {
		ArrayList<Page> TJMS = pushService.selectByType("学长经验"); // 推荐免试

		ArrayList<Page> KYJZ = pushService.selectByType("专硕巡展");

		ArrayList<Page> KYZZ = pushService.selectByType("研招访谈");

		ArrayList<Page> GJZZ = pushService.selectByType("推荐免试");

		ArrayList<Page> XWZX = pushService.selectByType("考研资讯");

		ArrayList<Page> KYZL = pushService.selectByType("调剂记录");

		ArrayList<Page> BKZN = pushService.selectByType("招生简章");

		ArrayList<Page> KYRC = pushService.selectByType("考研日程");

		ArrayList<Page> KYRD = pushService.selectByType("院校政策");

		ArrayList<Page> KYDD = pushService.selectByType("国家政策");

		ArrayList<Page> KYGJ= pushService.selectByType("考研工具");


		String str = request.getParameter("type");
		ArrayList<Page> List;
		if (Objects.equals(str, "XXXZ"))
			List = pushService.selectByType("学校选择");
		else if (Objects.equals(str, "KYGJ")) {
			List = pushService.selectByType("考研工具");
		}else {
			List = null;
		}
		return List;
	}

	@RequestMapping("/signinpage")
	public ModelAndView signinpage() {
		return new ModelAndView("signin");
	}

	@RequestMapping("/signuppage")
	public ModelAndView signuppage() {
		return new ModelAndView("signup");
	}

	@RequestMapping("/adminloginpage")
	public ModelAndView adminLogInPage() {
		return new ModelAndView("adminlogin");
	}

	@RequestMapping("/addadminpage")
	public ModelAndView addAdminPage() {
		return new ModelAndView("addadmin");
	}

	@RequestMapping("/adduserage")
	public ModelAndView addUserPage() {
		return new ModelAndView("addadmin");
	}

	@RequestMapping("/search")
	public ModelAndView searchPage(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		int start = 0;
		int count = 10;
		try {
			start = Integer.parseInt(request.getParameter("start"));
			if (start < 0) {
				start = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
		ListPage LP = new ListPage(start, count);
		PageHelper.offsetPage(LP.getStart(), LP.getCount());
		ArrayList<Page> pages = pageService.search("%" + keyword + "%");
		System.out.println(pages.size());
		int total = (int) new PageInfo<>(pages).getTotal();
		LP.setTotal(total);
		LP.caculateLast(total);
		if (start > total) {
			start = start - count;
			LP = new ListPage(start, count);
			PageHelper.offsetPage(LP.getStart(), LP.getCount());
			pages = pageService.search("%" + keyword + "%");
			total = (int) new PageInfo<>(pages).getTotal();
			LP.setTotal(total);
			LP.caculateLast(total);
		}
		ModelAndView mov = new ModelAndView("resultset");
		mov.addObject("pages", pages);
		mov.addObject("keyword", keyword);
		mov.addObject("LP", LP);
		return mov;
	}

	@RequestMapping("/resultpage")
	public ModelAndView resultpage(HttpServletRequest request, HttpServletResponse response) {
		int ID = Integer.parseInt(request.getParameter("ID"));
		HttpSession session = request.getSession();
		int userId = 0;
		int isfavorite = 0;
		if (session.getAttribute("userId") != null) {
			userId = (int) session.getAttribute("userId");
			Favorite favorite = new Favorite();
			favorite.setPID(ID);
			favorite.setUID(userId);
			if (favoriteService.existFavorite(favorite)) {
				isfavorite=1;
			}
		}
		Page page = pageService.selectByID(ID);
		page.addView();
		pageService.updatePageView(page);
		ModelAndView mov = new ModelAndView("result");
		mov.addObject("isfavorite",isfavorite);
		mov.addObject("page", page);
		return mov;
	}
}
