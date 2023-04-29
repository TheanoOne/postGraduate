package com.example.demo.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

	public static Date createDate(String model,String time) throws ParseException{
		DateFormat dateFormat1 = new SimpleDateFormat(model);
		Date date = dateFormat1.parse(time);
		return date;
	}

	public static void removeUserSession(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("userId");// 清空session信息
		request.getSession().removeAttribute("useraccount");
		request.getSession().removeAttribute("userImg");
		request.getSession().removeAttribute("usernickname");
		request.getSession().removeAttribute("power");
		System.out.println("user session清除");
		System.out.println(request.getSession().getAttribute("userid") + "    " +
				request.getSession().getAttribute("useraccount") + "    " +
				request.getSession().getAttribute("userImg") + "    " +
				request.getSession().getAttribute("usernickname") + "    " +
				request.getSession().getAttribute("power"));
//		request.getSession().invalidate();// 清除 session 中的所有信息
		// 退出登录的时候清空cookie信息,cookie需要通过HttpServletRequest，HttpServletResponse获取
		Cookie[] cookie = request.getCookies();
		for (Cookie c : cookie) {
			if ("autoLogin".equals(c.getName())) {
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
	}

	public static void removeAdminSession(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("adminId");// 清空session信息
		request.getSession().removeAttribute("adminaccount");
		request.getSession().removeAttribute("power");
		System.out.println("admin session清除");
		System.out.println(request.getSession().getAttribute("adminId") + "    " +
				request.getSession().getAttribute("adminaccount") + "    " +
				request.getSession().getAttribute("power"));
//		request.getSession().invalidate();// 清除 session 中的所有信息
		// 退出登录的时候清空cookie信息,cookie需要通过HttpServletRequest，HttpServletResponse获取
		Cookie[] cookie = request.getCookies();
		for (Cookie c : cookie) {
			if ("autoLogin".equals(c.getName())) {
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
	}
}
