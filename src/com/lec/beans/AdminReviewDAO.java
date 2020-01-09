package com.lec.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import common.D;

public class AdminReviewDAO {

	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	
	
	public static Connection getConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/testDB");
		return ds.getConnection();
	}
	
	
	// DB 자원 반납 메소드
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	}
	
	
	
	
	
	// ResultSet --> DTO 배열로 변환 리턴
	public ReviewDTO [] createReviewArray(ResultSet rs) throws SQLException {
		
		ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
		
		while(rs.next()){

			int review_brd_uid = rs.getInt("review_brd_uid");
			String mb_id = rs.getString("mb_id");
			String mb_name = rs.getString("mb_name");
			String ins_name = rs.getString("ins_name");
			String review_brd_regdate = rs.getString("review_brd_regdate");
			String review_brd_title = rs.getString("review_brd_title");
			int review_brd_viewcnt = rs.getInt("review_brd_viewcnt");
			
			ReviewDTO dto = new ReviewDTO(review_brd_uid, mb_id, mb_name, ins_name, review_brd_regdate, review_brd_title, review_brd_viewcnt);
			reviewList.add(dto);			
		}
		
		int size = reviewList.size();
		ReviewDTO [] arr = new ReviewDTO[size];
		
		reviewList.toArray(arr);
		
		return arr;
	}
	
	
	
	
	
	// 리뷰 목록 
	public ReviewDTO[] selectReviewList(int option_review, String keyword) throws SQLException, NamingException {
		
	
		ReviewDTO [] arr = null;
		String selectReview = D.SQL_SELECT_REVIEW;
		
		// 리뷰 검색 조건 (1)회원ID  (2)리뷰제목   (3)리뷰내용
		
		switch(option_review) {
			case 1: 
				selectReview += D.SQL_SELECT_REVIEW_BRD_WHERE_USER_ID;
				break;
			case 2:
				selectReview += D.SQL_SELECT_REVIEW_BRD_WHERE_REVIEW_TITLE;
				break;
			case 3:
				selectReview += D.SQL_SELECT_REVIEW_BRD_WHERE_REVIEW_CONTENT;
				break;
			default:
				break;
		}
		
		// 정렬
		selectReview += D.SQL_ORDER_REVIEW;
		
		
		try {
			// keyword가 있을 경우 쿼리문에 키워드 넘겨주기
			if(keyword != null && !keyword.equals("")) {
				conn = getConnection();
				pstmt = conn.prepareStatement(selectReview);
				pstmt.setString(1, keyword);
			}else { 
				conn = getConnection();
				pstmt = conn.prepareStatement(selectReview);
			}

			rs = pstmt.executeQuery();
			arr = createReviewArray(rs);
		} finally {
			close();
		}		
		
		return arr;
	}
	
	
	
	
	
	// 리뷰 삭제
	public int deleteReview(int review_uid) throws SQLException, NamingException {
		
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SQL_DELETE_REVIEW_BY_UID);
			pstmt.setInt(1, review_uid);
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	
	
	
}
