<%@page import="groupware.beans.ScheduleDto"%>
<%@page import="groupware.beans.ScheduleDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//리스트->디테일: sc_no

int sc_no= Integer.parseInt(request.getParameter("sc_no"));
ScheduleDao scheduleDao = new ScheduleDao();
ScheduleDto scheduleDto = scheduleDao.detail(sc_no);
%>


<jsp:include page="/template/header.jsp"></jsp:include>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$(".on").click(function(){	
			
			if($(this).test("진행중")){//완료로 값을 바꾼 뒤 제출한다. 		
				$(".form-complete").val("완료");
				$(".form-complete").sunmit();
			}else{ //아니라면(현재값이 완료라면) 진행중으로 값을 바꾼 뒤 제출한다.
				$(".form-complete").val("진행중");
				$(".form-complete").sunmit();
			}	
			
		});
	});

</script>

<div>
	<h2>스케줄정보</h2>
	<h4><%=scheduleDto.getSc_no()%></h4>
	
	<!-- 완료기능 영역 -->
	<div class="row float-container">
		<a class="on right link-btn"><%=scheduleDto.getSc_state() %></a>
	</div>
	<div>
		<form class="form-complete" action ="scheduleComplete.kh" method="post">
			<input type="hidden" value="<%=scheduleDto.getSc_state()  %>" name="sc_state">
		</form>
	</div>
	
	<!-- 상세보기 테이블 영역 -->
	<div>
		<table>
			<tr>
				<th>일정명</th>
				<td><%=scheduleDto.getSc_name() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=scheduleDto.getEmpNo() %></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><%=scheduleDto.getSc_made() %></td>
			</tr>
			<tr>
				<th>마감일</th>
				<td><%=scheduleDto.getSc_name() %></td>
			</tr>
			<tr>
				<th>세부일정</th>
				<td><%=scheduleDto.getSc_content() %></td>
			</tr>
		</table>
	</div>
	<div>
		<a>수정</a>
		<a>삭제</a>
	</div>
	
	
</div>




<jsp:include page="/template/footer.jsp"></jsp:include>