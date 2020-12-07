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
		// string -> date ���� ���� ����
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// view�ܿ��� ���� �Ķ���� �� �ޱ�
		int n1 = Integer.parseInt(request.getParameter("n1"));
		int n2 = Integer.parseInt(request.getParameter("n2"));
		int n3 = Integer.parseInt(request.getParameter("n3"));
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		System.out.println(start + "                 " + end);
		// ���� �Ķ���� �ޱ�(��� �޾ƾ�����, ó���ؾ����� �𸣰���)
		// String birthday = request.getParameter("birthday");

		// �ٹ�ǥ ������, ������
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

		// �ѿ�
		int k = n1 + n2 + n3;
		
		// ��� �迭
		ArrayList<NurseVO> list = new ArrayList<NurseVO>();
		
		// ����ȣ��� ���밣ȣ�� ����
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

		// �ٹ�ǥ ����
		for (NurseVO m : list) {
			/* ����ȣ�� case */
			if (m.getType() == "n1") {

			} 
			/* �ٸ���ȣ��� case */ 
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
									// ��������?
									// �޹��� ���� �޼ҵ� ȣ��
									res = holidayShift(mydate, holicount);
									if (res == false) {
										duty = "N";
										// db�� ����
									} else {
										duty = "O";
										// db�� ����
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
									// ��������?
									// �޹��� ���� �޼ҵ� ȣ��
									res = holidayShift(mydate, holicount);
									if (res == false) {
										duty = "N";
										// db�� ����
									} else {
										duty = "O";
										// db�� ����
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
									// ��������?
									// �޹��� ���� �޼ҵ� ȣ��
									res = holidayShift(mydate, holicount);
									if (res == false) {
										duty = "N";
										// db�� ����
									} else {
										duty = "O";
										// db�� ����
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

	// �޹��� ����
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
