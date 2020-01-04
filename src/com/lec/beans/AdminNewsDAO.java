package com.lec.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.D;

public class AdminNewsDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection 도 생성된다!
	public AdminNewsDAO() {
		
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("DAO 객체 생성, 데이터베이스 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DB 자원 반납 메소드
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	}
	
	// 관리자페이지 뉴스정보검색
	// 1.
	public NewsDTO[] createNewsArr(ResultSet rs) throws SQLException {	// 데이터 우리가 쓸 수 있는 값으로 바꿔오기
		ArrayList<NewsDTO> list = new ArrayList<NewsDTO>();

		while(rs.next()){
			int news_brd_uid = rs.getInt("news_brd_uid");
			String news_brd_title = rs.getString("news_brd_title");
			int news_brd_viewcnt = rs.getInt("news_brd_viewcnt");
			
			NewsDTO dto = new NewsDTO(news_brd_uid, news_brd_title, news_brd_viewcnt);
			list.add(dto);
		}
		
		int size = list.size();
		NewsDTO [] arr = new NewsDTO[size];
		list.toArray(arr);
		return arr;
	}
	
	public NewsDTO[] selectNews(int option_news_1, int option_news_2, String option_news_3) {
		// option_news_1 은 검색조건
		// option_news_2 는 검색키워드
		NewsDTO[] arr = null;
		String SELECT_NEWS_BRD = D.SQL_SELECT_NEWS_BRD;
		
		switch(option_news_2) {
		case 1:
			SELECT_NEWS_BRD += D.SQL_SELECT_NEWS_BRD_WHERE_UID;
			break;
		case 2:
			SELECT_NEWS_BRD += D.SQL_SELECT_NEWS_BRD_WHERE_TITLE;
			break;
		case 3:
			SELECT_NEWS_BRD += D.SQL_SELECT_NEWS_BRD_WHERE_CONTENT;
			break;
		}
		
		SELECT_NEWS_BRD += D.SQL_ORDER_BY_NEWS_BRD;
		
		try {
			pstmt = conn.prepareStatement(SELECT_NEWS_BRD);
			pstmt.setString(1, option_news_3);
			switch(option_news_1) {
			case 1: // 최근순
				pstmt.setString(2, "news_brd_uid");				
				pstmt.setString(3, "DESC");					
				break;
			case 2: // 오래된 순
				pstmt.setString(2, "news_brd_uid");					
				pstmt.setString(3, "ASC");					
				break;
			case 3: // 조회수 순
				pstmt.setString(2, "news_brd_viewcnt");					
				pstmt.setString(3, "DESC");					
				break;
			}
			rs = pstmt.executeQuery();
			arr = createNewsArr(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arr; 
	}
	
	// 관리자페이지 뉴스수정
	public int updateMbByUid(int news_brd_uid, String news_brd_title, String mb_name, String mb_email, int mb_level, int mb_zip, String mb_add1, String mb_add2,
		String mb_img) throws SQLException{
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_UPDATE_USER);
			pstmt.setInt(1, mb_uid);
			pstmt.setString(2, mb_pw);
			pstmt.setInt(3, mb_zip);
			pstmt.setString(4, mb_email);
			pstmt.setInt(5, mb_level);
			pstmt.setInt(6, mb_zip);
			pstmt.setString(7, mb_add1);
			pstmt.setString(8, mb_add2);
			pstmt.setString(9, mb_img);
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	
	public int deleteByUid(int mb_uid) throws SQLException{
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_DELETE_MB_BY_UID);
			pstmt.setInt(1, mb_uid);
			cnt = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
}