package groupware.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ScheduleDao {
	public static final String USERNAME = "kh75";
	public static final String PASSWORD ="kh75";
	
	//스케줄등록
	public void insert(ScheduleDto scheduleDto) throws Exception {
		Connection con =jdbcUtils.con(USERNAME, PASSWORD);
		String sql ="insert into schedule values (schedule_seq.nextval,?,?,?,sysdate,?,'진행')";
		PreparedStatement ps =con.prepareStatement(sql);
		
		
		ps.setString(1, scheduleDto.getEmpNo());
		ps.setString(2, scheduleDto.getSc_name());
		ps.setString(3, scheduleDto.getSc_content());
		ps.setString(4, scheduleDto.getSc_deadline());
		
		ps.execute();
		
		
	}
	//시퀀스 뽑기//시퀀스 번호 생성기능 
		public int getSequence() throws Exception {
		Connection con = jdbcUtils.con(USERNAME, PASSWORD);
		
		String sql ="select schedule_seq.nextval from dual"; //듀얼에 시퀀스 다음값 뽑아와라
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next(); //한 줄 읽어라
		
		int no =rs.getInt(1); //첫번째 줄을 담아라
		
		con.close();
		return no;
	}
	
	
	//스케줄 디테일
	public ScheduleDto detail(int sc_no) throws Exception {
		Connection con=jdbcUtils.con(USERNAME, PASSWORD);
		String sql="select*from schedule where sc_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, sc_no); 
		ResultSet rs= ps.executeQuery();
		
		rs.next();
		
		ScheduleDto schduleDto = new ScheduleDto();
		schduleDto.setEmpNo(rs.getString("sc_no"));
		schduleDto.setSc_name(rs.getString("sc_name"));
		schduleDto.setSc_content(rs.getString("sc_content"));
		schduleDto.setSc_made(rs.getString("sc_made"));
		schduleDto.setSc_deadline(rs.getString("deadline"));
		
		con.close();
		return schduleDto;
		
		
	}
	
	//삭제 : 필요한 정보 - 스케줄번호, 작성자번호(사원번호)
	public boolean delete(ScheduleDto scheduleDto) throws Exception {
		Connection con=jdbcUtils.con(USERNAME, PASSWORD);
		String sql="delete schedule where sc_no=? and EMP_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, scheduleDto.getSc_no());
		ps.setString(2, scheduleDto.getEmpNo());
		
		int count=ps.executeUpdate();
		
		con.close();
		return count>0;
		
		
	}
	//이름과 내용 수정: 필요한 정보 - 스케줄번호, 작성자번호(사원번호)
	//			+ sc_content +sc_name
	public boolean edit(ScheduleDto scheduleDto) throws Exception {
		Connection con=jdbcUtils.con(USERNAME, PASSWORD);
		String sql="update schedule set sc_name=?, sc_content=? where sc_no=? and EMP_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, scheduleDto.getSc_name());
		ps.setString(2, scheduleDto.getSc_content());
		ps.setInt(3, scheduleDto.getSc_no());
		ps.setString(4, scheduleDto.getEmpNo());
		
		int count = ps.executeUpdate();
		con.close();
		return count>0;
	}

	
	//state 완료상태 변경 메소드: 필요한 정보 - 스케줄번호, 작성자번호(사원번호) + sc_state
	public boolean complete(ScheduleDto scheduleDto) throws Exception{
		Connection con=jdbcUtils.con(USERNAME, PASSWORD);
		String sql="update schedule set  sc_state=? where sc_no=? and EMP_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,scheduleDto.getSc_state());
		ps.setInt(2, scheduleDto.getSc_no());
		ps.setString(3, scheduleDto.getEmpNo());
		
		int count= ps.executeUpdate();
		
		con.close();
		return count>0;
		
	}
	
}