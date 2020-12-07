package com.shift.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
		// view단에서 보낸 파라미터 값 받기
		int n1 = Integer.parseInt(request.getParameter("n1"));
		int n2 = Integer.parseInt(request.getParameter("n2"));
		int n3 = Integer.parseInt(request.getParameter("n3"));
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		System.out.println(start + "                 " + end);
		// 생일 파라미터 받기(어떻게 받아야할지, 처리해야할지 모르겠음)
		// String birthday = request.getParameter("birthday");

		// 근무표 시작일, 종료일
		Date startdate = null;
		Date enddate = null;
		Date valdate = null;
		//
		int date = 1;
		int holicount;
		int counter = 0;
		boolean res;
		String duty;
		Date mydate;

		try {
			startdate = dateFormat.parse(start);
			enddate = dateFormat.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 총원
		int k = n1 + n2 + n3;
		
		// 멤버 배열
		ArrayList<NurseVO> list = new ArrayList<NurseVO>();
		
		// 수간호사와 교대간호사 구분
		for (int i = 0; i < n1; i++) {
			NurseVO headnurse = new NurseVO();
			headnurse.setIdx1(i);
			headnurse.setType("n1");
			list.add(headnurse);
		}
		for (int i = 0; i < (k - n1); i++) {
			NurseVO othernurse = new NurseVO();
			othernurse.setIdx2(i);
			list.add(othernurse);
		}

		// 근무표 생성
		for (NurseVO m : list) {
			/* 수간호사 case */
			if (m.getType() == "n1") {

			} 
			/* 다른간호사들 case */ 
			else if (m.getType() == "n2") {
				switch (m.getIdx2() % 6) {
				case 0:
					holicount = 0;
					counter = 0;
					valdate = startdate;
					Calendar cal = Calendar.getInstance();
					a: while (true) {
						switch (counter % 3) {
						case 0:
							for (date = valdate.getDate(); date < valdate.getDate() + 7; date++) {
								cal.set(valdate.getYear(), valdate.getMonth(), date);
								mydate = new Date(cal.getTimeInMillis());
								if (date == enddate.getDate())
									break a;
								else {
									// 생일인지?
									// 휴무일 지정 메소드 호출
									res = holidayShift(mydate, holicount);
									if (res == false) {
										duty = "N";
										// db에 저장
									} else {
										duty = "O";
										// db에 저장
									}
								}
							}
							holicount++;
							counter++;
							cal.setTime(valdate);
							cal.add(Calendar.DATE, 7);
							valdate.setTime(cal.getTimeInMillis());
							break;
						case 1:
							for (date = valdate.getDate(); date < startdate.getDate() + 7; date++) {
								cal.set(valdate.getYear(), valdate.getMonth(), date);
								mydate = new Date(cal.getTimeInMillis());
								if (date == enddate.getDate())
									break a;
								else {
									// 생일인지?
									// 휴무일 지정 메소드 호출
									res = holidayShift(mydate, holicount);
									if (res == false) {
										duty = "N";
										// db에 저장
									} else {
										duty = "O";
										// db에 저장
									}
								}
							}
							holicount++;
							counter++;
							cal.setTime(valdate);
							cal.add(Calendar.DATE, 7);
							valdate.setTime(cal.getTimeInMillis());
							break;
						case 2:
							for (date = valdate.getDate(); date < valdate.getDate() + 7; date++) {
								cal.set(valdate.getYear(), valdate.getMonth(), date);
								mydate = new Date(cal.getTimeInMillis());
								if (date == enddate.getDate())
									break a;
								else {
									// 생일인지?
									// 휴무일 지정 메소드 호출
									res = holidayShift(mydate, holicount);
									if (res == false) {
										duty = "N";
										// db에 저장
									} else {
										duty = "O";
										// db에 저장
									}
								}
							}
							holicount++;
							counter++;
							cal.setTime(valdate);
							cal.add(Calendar.DATE, 7);
							valdate.setTime(cal.getTimeInMillis());
							break;
						}
					}

				case 1:
					holicount = 0;

					break;
				case 2:
					holicount = 0;

					break;
				case 3:
					holicount = 1;

					break;
				case 4:
					holicount = 1;

					break;
				case 5:
					holicount = 1;

					break;

				default:
					break;
				}

			}
		}

		return mv;
	}

	// 휴무일 지정
	public boolean holidayShift(Date date, int holicount) {
		boolean res = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		switch (holicount % 7) {
		case 0:
			if (dayOfWeek == 6) {
				return true;
			} else if (dayOfWeek == 5) {
				return true;
			} else {
				return false;
			}
		case 1:
			if (dayOfWeek == 4) {
				return true;
			} else if (dayOfWeek == 3) {
				return true;
			} else {
				return false;
			}
		case 2:
			if (dayOfWeek == 2) {
				return true;
			} else if (dayOfWeek == 1) {
				return true;
			} else {
				return false;
			}
		case 3:
			if (dayOfWeek == 0) {
				return true;
			} else if (dayOfWeek == 6) {
				return true;
			} else {
				return false;
			}
		case 4:
			if (dayOfWeek == 5) {
				return true;
			} else if (dayOfWeek == 4) {
				return true;
			} else {
				return false;
			}
		case 5:
			if (dayOfWeek == 3) {
				return true;
			} else if (dayOfWeek == 2) {
				return true;
			} else {
				return false;
			}
		case 6:
			if (dayOfWeek == 1) {
				return true;
			} else if (dayOfWeek == 0) {
				return true;
			} else {
				return false;
			}
		default:
			break;
		}
		return res;
	}

}
