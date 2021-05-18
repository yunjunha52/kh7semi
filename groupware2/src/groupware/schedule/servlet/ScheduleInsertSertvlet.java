package groupware.schedule.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupware.beans.ScheduleDao;
import groupware.beans.ScheduleDto;

@WebServlet(urlPatterns = "/schedule/scheduleInsert.kh")
public class ScheduleInsertSertvlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			//준비: sc_no, sc_name, sc_content, sc_made, sc_deadline
			ScheduleDto scheduleDto =new ScheduleDto();
			scheduleDto.setSc_no(Integer.parseInt(req.getParameter("sc_no")));
			scheduleDto.setSc_name(req.getParameter("sc_name"));
			scheduleDto.setSc_content(req.getParameter("sc_content"));
			scheduleDto.setSc_deadline(req.getParameter("sc_deadline"));
			
			ScheduleDao scheduleDao = new ScheduleDao();
			scheduleDao.insert(scheduleDto);
			
			resp.sendRedirect("scheduleDetail.jsp?sc_no="+scheduleDto.getSc_no());
			
		}catch(Exception e) {
			
			e.printStackTrace();
			resp.sendError(500);
		}
		
		
		
	}
}
