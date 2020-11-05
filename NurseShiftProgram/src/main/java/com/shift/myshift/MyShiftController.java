package com.shift.myshift;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyShiftController {

	@RequestMapping(value = "schedule.do", method = RequestMethod.POST)
	public ModelAndView showresult(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("result");
		// string -> date 위한 형식 생성
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// view단에서  보낸 파라미터 값 받기
		int n1 = Integer.parseInt(request.getParameter("n1"));
		int n2 = Integer.parseInt(request.getParameter("n2"));
		int n3 = Integer.parseInt(request.getParameter("n3"));
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		// 생일 파라미터 받기(어떻게 받아야할지, 처리해야할지 아직 모르겠음)
		//String birthday = request.getParameter("birthday");
		
		// 근무표 시작일, 종료일
		Date startdate,enddate; 
		try {
			startdate = dateFormat.parse(start);
			enddate = dateFormat.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 총원
		int k = n1+n2+n3;
		// 멤버 배열
		ArrayList<NurseVO> list = new ArrayList<NurseVO>();
		// 수간호사와 교대간호사 구분
		for (int i = 0; i < n1; i++) {
			NurseVO headnurse = new NurseVO();
			headnurse.setIdx1(i);
			headnurse.setType("n1");
			list.add(headnurse);
		}
		for (int i = 0; i < (k-n1); i++) {
			NurseVO othernurse = new NurseVO();
			othernurse.setIdx2(i);
			list.add(othernurse);
		}
		
		// 근무표 생성
		for (NurseVO m : list) {
			if (m.getType()=="n1") {
				
			}else {
				
			}
		}
		

		
		return mv;
	}
}
