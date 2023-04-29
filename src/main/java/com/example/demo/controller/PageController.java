package com.example.demo.controller;

import com.example.demo.entity.Page;
import com.example.demo.entity.Push;
import com.example.demo.service.AdminService;
import com.example.demo.service.PageService;
import com.example.demo.service.PushService;
import com.example.demo.util.ListPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class PageController {
	@Autowired
	AdminService adminService;
	@Autowired
	PageService pageService;
	@Autowired
	PushService pushService;

	@RequestMapping("/crawlerpage")
	public ModelAndView crawlerPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String power = adminService.getAdminByID((int) session.getAttribute("adminId")).getPower();
		ModelAndView mov;
		if (power.equals("最高管理员") || power.equals("爬虫管理员")) {
			mov = new ModelAndView("crawler");
		} else {
			mov = new ModelAndView("warn");

		}
		return mov;
	}

	@RequestMapping("/webdatapage")
	public ModelAndView webdataPage(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String power = adminService.getAdminByID((int) session.getAttribute("adminId")).getPower();ModelAndView mov;
		if (power.equals("最高管理员") || power.equals("数据管理员")) {
			int start = 0;
			int count = 10;
			try {
				start = Integer.parseInt(request.getParameter("start"));
				if (start < 0) {
					start = 0;
				}
			} catch (Exception e) {
			}

			ListPage LP = new ListPage(start, count);
			PageHelper.offsetPage(LP.getStart(), LP.getCount());
			ArrayList<Page> pages = pageService.allPage();
			System.out.println(pages.size());
			int total = (int) new PageInfo<>(pages).getTotal();
			LP.setTotal(total);
			LP.caculateLast(total);
			if (start > total) {
				start = start - count;
				LP = new ListPage(start, count);
				PageHelper.offsetPage(LP.getStart(), LP.getCount());
				pages = pageService.allPage();
				total = (int) new PageInfo<>(pages).getTotal();
				LP.setTotal(total);
				LP.caculateLast(total);
			}
			mov = new ModelAndView("webdata");
			mov.addObject("pages", pages);
			mov.addObject("LP", LP);
		} else {
			mov = new ModelAndView("warn");
		}
		return mov;
	}

	@RequestMapping("/deletepage")
	public ModelAndView deletPage(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("ID"));
		pageService.deleteByID(id);
		return new ModelAndView("redirect:/webdatapage");
	}

	@RequestMapping("/searchpage")
	public ModelAndView searchPage(HttpServletRequest request, HttpServletResponse response) {
		int start = 0;
		int count = 10;

		try {
			start = Integer.parseInt(request.getParameter("start"));
			if (start < 0) {
				start = 0;
			}
		} catch (Exception e) {
		}
		String keyword = request.getParameter("keyword");
		ListPage LP = new ListPage(start, count);
		PageHelper.offsetPage(LP.getStart(), LP.getCount());
		ArrayList<Page> pages = pageService.search("%" + keyword + "%");
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
		ModelAndView mov = new ModelAndView("webdata");
		mov.addObject("pages", pages);
		mov.addObject("keyword", keyword);
		mov.addObject("LP", LP);
		return mov;
	}

	@RequestMapping("/usersearchpage")
	public ModelAndView usersearchpage(HttpServletRequest request, HttpServletResponse response) {
		int start = 0;
		int count = 10;

		try {
			start = Integer.parseInt(request.getParameter("start"));
			if (start < 0) {
				start = 0;
			}
		} catch (Exception e) {
		}
		String keyword = request.getParameter("keyword");
		ListPage LP = new ListPage(start, count);
		PageHelper.offsetPage(LP.getStart(), LP.getCount());
		ArrayList<Page> pages = pageService.search("%" + keyword + "%");
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
		ModelAndView mov = new ModelAndView("searchdata");
		mov.addObject("pages", pages);
		mov.addObject("keyword", keyword);
		mov.addObject("LP", LP);
		System.out.println("LP: " + LP);
		return mov;
	}

	@RequestMapping("/getLinks")
	public ModelAndView getLink(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
        * link: 链接地址
        * source: 来源学校
        * linkcondition: 选取链接条件
        * ctxtcondition: 选取正文条件
        */
		String link = request.getParameter("link");
		String source = request.getParameter("source");
		String linkcondition = request.getParameter("linkcondition");
		String ctxtcondition = request.getParameter("ctxtcondition");
		ModelAndView mov = new ModelAndView("crawler");
		String html = link;

		Document doc = Jsoup.connect(html).get();
		System.out.println(doc);
		Element linkscontent = doc.select(linkcondition).first();

		Elements links = linkscontent.getElementsByTag("a");
		ArrayList<Page> pages = new ArrayList<Page>();
		//将获取的文章传入到前端页面显示
		for (Element link1 : links) {
			try{
				Page page = new Page();
				page.setTitle(link1.text());
				page.setLink(link1.attr("abs:href"));
				page.setSource(source);
				page.setDate(new Date());
				page.setStar(0);
				page.setView(0);

				Document doc1 = Jsoup.connect(page.getLink()).get();
				Elements element1 = doc1.getElementsByTag("a");
				for(Element el:element1){
					el.attr("href",el.attr("abs:href"));
				}
				Elements elements = doc1.select("img[src]");
				for(Element el:elements){
					String imgUrl = el.attr("src");
					if (imgUrl.trim().startsWith("/")) {
						el.attr("src", el.absUrl("src"));
					}
				}
				Element content = doc1.select(ctxtcondition).first();
				page.setContent(content.html());
				if (pageService.existTitle(page.getTitle())) {

				} else {
					pageService.addPage(page);
				}

				pages.add(page);
			}catch (Exception e) {
				continue;
			}
		}
		mov.addObject("pages", pages);
		return mov;
	}

	@RequestMapping("/addexperience")
	public ModelAndView addexperience(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String url;
		if (session.getAttribute("useraccount") != null || session.getAttribute("adminaccount") != null)
			url = "addpage";
		else url = "redirect:/signinpage";
		System.out.println("addexperience: " + session.getAttribute("power"));
		ModelAndView mov = new ModelAndView(url);
		return mov;
	}

	@RequestMapping("/addpage")
	public ModelAndView addpage(HttpServletRequest request, HttpServletResponse response) {
		String link = "";
		String title = request.getParameter("title");
		String source = (String) request.getSession().getAttribute("usernickname");
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		int uid;
		if (request.getSession().getAttribute("power").equals("文章管理员") || request.getSession().getAttribute("power").equals("最高管理员")) {
			uid = 000;
			source = "管理员";
		}else {
			uid = (int) request.getSession().getAttribute("userId");
		}
		Page page = new Page();
		page.setLink(link);
		page.setTitle(title);
		page.setSource(source);
		page.setContent(content);
		page.setStar(0);
		page.setView(0);
		page.setDate(new Date());
		page.setUid(uid);
		pageService.addPage(page);
		Push push = new Push();
		int pid = 0;
		Page page1 = pageService.selectByTitle(page.getTitle());
//		System.out.println("page.date: " + page.getDate().getTime());
//		System.out.println("page1.date: " + page1.getDate().getTime());
		long time = page1.getDate().getTime() - page.getDate().getTime();
		if (page1.getUid() == uid && time < 1000) {
			pid = page1.getID();
//			System.out.println(pid);
		}
		push.setPID(pid);
		push.setType(type);
		pushService.addPush(push);
		ModelAndView mov ;
		if (request.getSession().getAttribute("power").equals("user")) {
			mov = new ModelAndView("redirect:/usersearchpage");
			mov.addObject("keyword", type);
		}else {
			mov = new ModelAndView("redirect:/indexpushpage");
		}
		return mov;
	}

	@RequestMapping("/help")
	public ModelAndView getHelp(HttpServletRequest request, HttpServletResponse response){

		ModelAndView modelAndView = new ModelAndView("help");
		return  modelAndView;
	}

}
