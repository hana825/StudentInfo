package com.callor.student.service.impl;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;
import com.callor.utils.Line;

public class StudentServiceImpl implements StudentService{
	
	Scanner scan;
	List<StudentVO> stList;
	protected String saveFileName;
	public StudentServiceImpl() {
		super();
		saveFileName = "src/com/callor/student/student.txt";
		scan = new Scanner(System.in);
		stList = new ArrayList<>();
		loadStudent();
	}

	@Override
	public void inputStudent() {
		while(true) {
			System.out.println("학번 입력>> ");
			String stNum = scan.nextLine();
			System.out.println("이름 입력>> ");
			String stName = scan.nextLine();
			System.out.println("학과 입력>> ");
			String stDept = scan.nextLine();
			System.out.println("학년 입력>> ");
			String stGrade = scan.nextLine();
			System.out.println("전화번호 입력>> ");
			String stTel = scan.nextLine();
			
			
			StudentVO stVO = new StudentVO();
			stVO.setStNum(stNum);
			stVO.setStName(stName);
			stVO.setStDept(stDept);
			stVO.setStGrade(stGrade);
			stVO.setStTel(stTel);
			
			stList.add(stVO);
			break;
		}

		FileWriter fileWriter = null;
		PrintWriter print = null;
		try {
			fileWriter = new FileWriter(saveFileName);
			print = new PrintWriter(fileWriter);
			for(StudentVO stVO : stList) {
				print.printf("%s:", stVO.getStNum());
				print.printf("%s:", stVO.getStName());
				print.printf("%s:", stVO.getStDept());
				print.printf("%s:", stVO.getStGrade());
				print.printf("%s\n", stVO.getStTel());
			}
			print.flush();
			print.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void printStudent() {
		System.out.println(Line.dLine(50));
		System.out.println("학생정보");
		System.out.println(Line.sLine(50));
		System.out.println("학번\t이름\t학과\t학년\t전화번호");
		for(StudentVO stVO : stList) {
			System.out.print(stVO.getStNum() + "\t");
			System.out.print(stVO.getStName() + "\t");
			System.out.print(stVO.getStDept() + "\t");
			System.out.print(stVO.getStGrade() + "\t");
			System.out.println(stVO.getStTel());
		}
		
	}

	@Override
	public void loadStudent() {
		InputStream is = null;
		Scanner fileScan = null;
		try {
			is = new FileInputStream(this.saveFileName);
			fileScan = new Scanner(is);
			while(fileScan.hasNext()) {
				String line = fileScan.nextLine();
				String[] sts = line.split(":");
				
				StudentVO stVO = StudentVO.builder()
										.stNum(sts[0])
										.stName(sts[1])
										.stDept(sts[2])
										.stGrade(sts[3])
										.stTel(sts[4])
										.build();
				stList.add(stVO);
			}
		} catch (Exception e) {
		}
	}

	

}
