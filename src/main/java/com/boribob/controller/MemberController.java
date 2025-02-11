package com.boribob.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.boribob.dao.MemberDAO;
import com.boribob.dto.MemberDTO;
import com.boribob.mail.SendMail;
import com.boribob.utils.EncryptionUtils;
import com.google.gson.Gson;

@WebServlet("*.mem") 
public class MemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 고객이 요청한 uri
		String uri = request.getRequestURI();
		System.out.println("요청 uri : " + uri);
		
		if(uri.equals("/member.mem")) { //회원가입 페이지 요청
			response.sendRedirect("/member/member.jsp");
		}else if(uri.equals("/idCheckPopup.mem")) { //이메일 중복확인 뜨는 팝업 페이지 요청
			request.setAttribute("idx", "idcheck");
			System.out.print("idcheck");
			request.getRequestDispatcher("/member/popup.jsp").forward(request, response);
		}else if(uri.equals("/checkId.mem")) { // 이메일 중복확인 요청
			String id = request.getParameter("id");
			System.out.println("id : " + id);
			
			MemberDAO dao = new MemberDAO();
			try {
				MemberDTO rs = dao.selectById(id);
				System.out.println("rs : " + rs);
				
				if(rs==null) { //사용가능 이메일
					request.setAttribute("rs", "ok");
				}else { //중복된 이메일
					request.setAttribute("rs", "no");
				}
				request.setAttribute("id", id);
				request.getRequestDispatcher("/member/popup.jsp").forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/certificationEmail.mem")) { // 이메일 인증버튼 누르면~
			String email = request.getParameter("email");
			System.out.println(email);
			
			System.out.println("certificationEmail");
			SendMail sm = new SendMail();
			int randomNumber = sm.compare(email);
			System.out.println("randomNumber : " + randomNumber);
			
			
			response.getWriter().append(String.valueOf(randomNumber));
			
		}else if(uri.equals("/postCodePopup.mem")) { // 우편번호 찾기 누르면 뜨는 팝업 페이지
			request.setAttribute("idx", "post");
			System.out.print("post");
		
			
			request.getRequestDispatcher("/member/popup.jsp").forward(request, response);
			
			
		}else if(uri.equals("/signup.mem")) { // 회원가입 요청
			
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String post = request.getParameter("post");
			String roadAddress = request.getParameter("roadAddress");			
			String detailAddress = request.getParameter("detailAddress");
			String phone = request.getParameter("phone");
			
			System.out.println(id+" : "+password+" : "+name+" : "+post
					+" : "+roadAddress+" : "+detailAddress+" : "+phone);
			
			MemberDAO dao = new MemberDAO();
			try {
				System.out.println("하기전 : " +password);
				password = EncryptionUtils.getSHA512(password);
				
				System.out.println("하고 난 후 : " +password);
				int rs = dao.insert(new MemberDTO(id, password, name, post, roadAddress, detailAddress, phone));
				if(rs > 0) {
					response.sendRedirect("/login/login.jsp");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
		}else if(uri.equals("/login.mem")) { //로그인 버튼 눌렀을때
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			System.out.println(id + " : " + password);
			
		MemberDAO dao = new MemberDAO();
		
		  try {
			  password = EncryptionUtils.getSHA512(password);
		         System.out.println(password);
		         MemberDTO dto = dao.isLoginOk(id, password);
		         if(dto !=null) {
		            System.out.println("로그인성공");
		            request.setAttribute("rs", true);
		            HttpSession session = request.getSession();
		            session.setAttribute("loginSession", dto);
		            request.getRequestDispatcher("/home").forward(request, response);
		         }else {
		            System.out.println("로그인실패");
		            request.setAttribute("rs", false);
		            response.sendRedirect("/login/login.jsp");

		         }
		         
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/logout.mem")) { // 로그아웃
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("/home");
			
		}else if(uri.equals("/searchId.mem")) { // 이메일 찾기
			String phone1 = request.getParameter("phone");
			System.out.println("phone : " + phone1);
			MemberDAO dao = new MemberDAO();
			try {
				MemberDTO phone = dao.selectByPhone(phone1);
				System.out.println("ph : " + phone);
				if(phone!=null) { // 회원이라면 
				Gson gson = new Gson();
				String rs = gson.toJson(phone);
				response.getWriter().append(rs);
				}
				else { 				// 회원이 아니라면 
					response.getWriter().append(null);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}			
			
		}else if(uri.equals("/searchPassword.mem")) { // 비밀번호 찾기
			String password = request.getParameter("id");
			System.out.println("id : " + password);
			MemberDAO dao = new MemberDAO();
			try {
				MemberDTO id = dao.selectByPhone(password);
				System.out.println("id : " + id);
				if(id!=null) { // 회원이라면 
				Gson gson = new Gson();
				String ps = gson.toJson(id);
				response.getWriter().append(ps);
				}
				else { 				// 회원이 아니라면 
					response.getWriter().append(null);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/idSearch.mem")) {
			response.sendRedirect("/search/searchId.jsp");
		}else if(uri.equals("/passwordSearch.mem")) {
			response.sendRedirect("/search/searchPassword.jsp");
		}
		
		
		
		
		
		
	}
}
   