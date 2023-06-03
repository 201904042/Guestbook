package ch10;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


@WebServlet("/guestControl")
public class GuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int aid = 0;
    private String state;
	private GuestDAO dao;
	private ServletContext ctx;
	
	private final String START_PAGE = "ch10/guestList.jsp";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new GuestDAO();
		ctx = getServletContext();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println("servicestart");
		String view = null;
		if(action==null) {
			System.out.println("service1");
			getServletContext().getRequestDispatcher("/guestControl?action=listGuest").forward(request, response);
		}
		else {
			System.out.println("service2");
			System.out.println("action = "+ action);
			switch(action) {
			case "listGuest" : view = listGuest(request); break;
			case "addGuest" : view = addGuest(request); break;
			case "getGuest" : view = getGuest(request); break;
			case "deleteGuest" : view = deleteGuest(request); break;
			case "insert" : state="insert"; view = insertGuest(request); break;
			case "menu" : view = menuGuest(request);break;
			case "change" : state = "change"; view = changeGuest(request); break;
			}
			System.out.println("service3");
			getServletContext().getRequestDispatcher("/"+view).forward(request, response);
		}
	}
	
	public String changeGuest(HttpServletRequest request) {
		System.out.println("changestart");
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Guest g = dao.getGuest(id);
			BeanUtils.populate(g, request.getParameterMap());
			dao.addGuest(g);
			
		}catch (Exception e) {
			e.printStackTrace();
			ctx.log("수정과정에서 문제발생");
			request.setAttribute("error", "정상적으로 등록되지 않음");
			//이 아래의 리턴을 이전화면으로 가게끔 해야함
			String undo = request.getHeader("Referer");
			undo = undo.substring(28);
			System.out.println(undo);
			return undo;
		}
		try {
			dao.delGuest(id);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("수정과정에서 문제발생");
			request.setAttribute("error", "정상적으로 등록되지 않음");
			return "ch10/guestEdit.jsp";
		}
		return listGuest(request);
	}
	
	public String menuGuest(HttpServletRequest request) {
		return "ch10/guestList.jsp";
	}
	public String insertGuest(HttpServletRequest request) {
		return "ch10/Guestinsert.jsp";
	}
	
	public String addGuest(HttpServletRequest request) {
		Guest g = new Guest();
		try {
			BeanUtils.populate(g, request.getParameterMap());
			dao.addGuest(g);
		}catch(Exception e) {
			e.printStackTrace();
			ctx.log("추가 과정에서 문제 발생");
			request.setAttribute("error", "정상적으로 등록되지 않음");
			System.out.println(state);
			return "ch10/Guestinsert.jsp";
			
		}
		
		return listGuest(request);
	}
	
	
	
	public String listGuest(HttpServletRequest request) {
		List<Guest> list;
		try {
			list = dao.getAll();
			request.setAttribute("guestList", list);
		}catch(Exception e) {
			e.printStackTrace();
			ctx.log("목록 생성 문제발생");
			request.setAttribute("error", "목록이 정상적으로 처리되지 않음");
		}
		return "ch10/guestList.jsp";
	}
	
	public String getGuest(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Guest g = dao.getGuest(id);
			request.setAttribute("guestList", g);
			
			System.out.println(id);
		}catch(SQLException e) {
			e.printStackTrace();
			ctx.log("가져오는 과정에서 문제발생");
			request.setAttribute("error", "정상적으로 가져오지 못함");
		}
		return "ch10/guestEdit.jsp";
	}
	
	public String deleteGuest(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			dao.delGuest(id);
		}catch (SQLException e) {
			e.printStackTrace();
			ctx.log("삭제과정에서 문제발생");
			request.setAttribute("error", "정상적으로 삭제되지 않음");
			return listGuest(request);
		}
		return listGuest(request);
	}
}
