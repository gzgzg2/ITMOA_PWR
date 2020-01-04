package common;

public interface D {
	
	public static final String DRIVER = "org.mariadb.jdbc.Driver";
	public static final String URL = "jdbc:mariadb://localhost:3306/itmoa";
	public static final String USERID = "myuser";
	public static final String USERPW = "1234";

//  ####################################   1. 회원 계정 관련 SQL문     ####################################
  
//  =============== 사용자용 ==============
	
	// 로그인
	public static final String SQL_SELECT_LOGIN =
			"SELECT mb_uid, mb_id,  mb_pw, mb_level, mb_img FROM mb WHERE id = ? AND pw = ?";
	
	// 회원가입
	public static final String SQL_INSERT_JOIN =
			"INSERT INTO mb(mb_name, mb_id, mb_pw, mb_email, mb_zip, mb_add1, mb_add2) VALUES(?, ?, ?, ?, ?, ?, ?)";

	// 아이디찾기
	public static final String SQL_SELET_FIND_ACCOUNT_ID =
			"SELECT mb_name, mb_email FROM mb WHERE mb_name = ? AND mb_email = ?";

	// 비밀번호 찾기
	public static final String SQL_SELECT_FIND_ACCOUNT_PWD =
			"SELECT mb_id, mb_name, mb_email FROM mb WHERE mb_id = ? AND mb_name = ? AND mb_email = ?";

	// 마이페이지에 회원정보+찜목록 불러오기 
	public static final String SQL_SELECT_MYPAGE =
		"SELECT m.*, z.zzim_uid, i.ins_name,  c.cur_name"
		+ " FROM zzim z, cur c, class cl, ins i, mb m"
		+ " WHERE z.mb_uid = ?"
		+ " AND z.class_uid = cl.class_uid" 
		+ " AND cl.cur_uid = c.cur_uid"
		+ " AND cl.ins_uid = i.ins_uid";
	

	// 마이페이지에서 찜 삭제하기
	public static final String SQL_DELETE_ZZIM = 
		"DELETE FROM zzim where zzim_uid = ?";
	
	// 마이페이지에서 회원정보 수정
	public static final String SQL_UPDATE_MYPAGE=
		"UPDATE mb"
		+ " SET mb_pw = ?, mb_email = ?,  mb_zip = ? , mb_add1 = ?, mb_add2 = ?"
		+ " WHERE mb_uid = ?";
		
	// 프로필 이미지 수정
	public static final String SQL_UPDATE_MYPAGE_IMG =
		"UPDATE mb"
		+ " SET mb_img = ?"
		+ " WHERE mb_uid = ?";
	
	// 프로필 이미지 삭제(기본 이미지로 변경)
		public static final String SQL_DELETE_MYPAGE_IMG =
			"UPDATE mb"
			+ " SET mb_img = ?"
			+ " WHERE mb_uid = ?";
	
	

//  =============== 관리자용 ==============
	
	// 회원 검색 조건) 조건 없을 때      
	public static final String SQL_SELECT_USER =                  
		"SELECT *"
		+ " FROM mb";
	public static final String SQL_SELECT_USER_WHERE_LEVEL =    
		" WHERE mb_level IN(?, ?, ?)";
	// 회원 검색 조건) 회원번호
	public static final String SQL_SELECT_USER_WHERE_UID =
		" AND mb_uid = ?";	
	
	// 회원 검색 조건) 회원ID
	public static final String SQL_SELECT_USER_WHERE_ID =
		" AND mb_id LIKE (%?%)";

	// 회원 검색 조건) 회원이름
	public static final String SQL_SELECT_USER_WHERE_NAME =
		" AND mb_name LIKE (%?%)";

	// 회원 검색 조건) 회원이메일
	public static final String SQL_SELECT_USER_WHERE_EMAIL =
		" AND mb_email LIKE (%?%)";

	// 회원 검색 결과 정렬
	public static final String SQL_USER_ORDER_BY =
		" ORDER BY mb_regdate DESC";

   // 회원 정보 수정 
	public static final String SQL_UPDATE_USER =
		"UPDATE mb"
		+ " SET mb_name, mb_pw, mb_img = ?, mb_level = ?, mb_email = ?,  mb_zip = ? , mb_add1 = ?, mb_add2 = ?"
		+ " WHERE mb_uid = ?";
	
	// 회원 정보 삭제
	public static final String SQL_DELETE_USER_BY_UID = "DELETE FROM mb WHERE mb_uid = ?";
	
	
	
	
	
	
	
	
//  ####################################   2. 학원 + 과정 관련 SQL문     ####################################
	
//  =============== 사용자용 ==============	
	
	// 학원 검색 조건) 전체
		public static final String SQL_SELECT_CLASS = 
	"SELECT i.ins_img, cl.class_zzimcnt, i.ins_name, c.cur_name"
	+ " FROM class cl, cur c, ins i "
	+ " WHERE cl.cur_uid = c.cur_uid "
	+ " AND cl.ins_uid = i.ins_uid";

	// 학원 검색 조건 ) 지역조건
	public static final String SQL_SELECT_CLASS_WHERE_INS_LOCATION = 
	 " AND i.ins_location like ('%?%')"; 
	 
	// 학원 검색 조건 ) 지점조건
	public static final String SQL_SELECT_CLASS_WHERE_INS_BRANCH = 
	 " AND i.ins_branch like ('%?%')";

	// 학원 검색 조건 ) 과정명조건
	public static final String SQL_SELECT_CLASS_WHERE_CUR_NAME = 
	" AND c.cur_name like ('%?%')";

	// 정렬
	public static final String SQL_ORDER_WHERE_CLASS_UID = 
	" ORDER BY cl.class_zzimcnt DESC";

	
	/* 사용가능한 조건 조합 예시 
	  	SQL_SELECT_CLASS + SQL_ORDER_BY_CLASS_UID;
	    
		// 2. 학원 검색 ) 지역 전체 + 지점 조건 + 과정명 조건 
	  	SQL_SELECT_CLASS + SQL_SELECT_CLASS_BY_INS_BRANCH + SQL_SELECT_CLASS_BY_CUR_NAME +SQL_ORDER_BY_CLASS_UID;

	 */

	
	// 학원 상세 페이지
		public static final String SQL_SELECT_INS_BY_UID = 
				"SELECT c.*,  i.ins_name "
				+ " FROM class cl,  ins i, cur c"
				+ " WHERE class_uid = ?"
				+ " AND cl.cur_uid = c.cur_uid"
				+ " AND cl.ins_uid = i.ins_uid";

	// 찜 추가
	public static final String SQL_INSERT_ZZIM = "INSERT INTO zzim (mb_uid, cur_uid) VALUES (?, ?)";

		
	

	//  ================ 관리자용 ===============
	
	// 학원 검색 조건) 전체
	public static final String SQL_SELECT_INS =
		"SELECT * FROM ins";
	
	// 학원 검색 조건) 학원 UID
	public static final String SQL_INS_WHERE_UID =
		" WHERE ins_uid = ?";
	
	// 학원 검색 조건) 학원명
	public static final String SQL_INS_WHERE_NAME =
		" WHERE ins_name LIKE ('%?%')";
	
	// 학원 검색 조건) 과정명
	public static final String SQL_INS_WHERE_CUR_NAME =
		" WHERE ins_uid = ?";
    // 정렬
	public static final String SQL_SELECT_INS_ORDER_BY_UID =
		" ORDER BY ins_uid";
	
	// 학원등록
	public static final String SQL_INSERT_INS =
		"INSERT INTO ins(ins_name, ins_tel, ins_zip, ins_add1, ins_add2, ins_location, ins_branch, ins_img)"
		+ " VALUES( ?, ?, ?, ?, ?, ?)";
	
	// 학원수정
	public static final String SQL_UPDATE_INS=
		"UPDATE ins"
		+ " SET ins_name= ?, ins_tel= ?,  ins_zip = ?, ins_add1 = ?, ins_add2 = ?, ins_location = ?, ins_branch = ?, ins_img = ?"
		+ " WHERE ins_uid = ?";
	
	// 학원 이미지 삭제(기본 이미지로 변경)
	public static final String SQL_DELETE_INS_IMG=
			"UPDATE ins"
			+ " SET ins_img = ?"
			+ " WHERE ins_uid = ?";
	
	// 학원삭제
	public static final String SQL_DELETE_INS =
		"DELETE FROM ins WHERE ins_uid = ?";
	
	

	// 클래스 목록
	public static final String SQL_SELECT_CLASS_BY_INS_UID =
		"SELECT c.cur_name \"과정명\", c.cur_hours \"시수\" "
		+ " FROM class cl, ins i, cur c"
		+ " WHERE cl.ins_uid = ?"
		+ " AND cl.cur_uid = c.cur_uid"
		+ " AND cl.ins_uid = i.ins_uid";

	// 클래스 추가
	public static final String SQL_INSERT_CLASS = "INSERT INTO class(ins_uid, cur_uid) VALUES (?, ?)";
	
	// 클래스 삭제
	public static final String SQL_DELETE_CLASS = "DELETE FROM class WHERE class_uid = ?";


	
	
	
	
	
	
	
	
	
//  ####################################   3. 리뷰 관련 SQL문     ####################################
	
//  =============== 사용자  ==============	
	
	// 리뷰 목록
	public static final String SQL_SELECT_REVIEW =  
		"SELECT r.review_brd_uid, m.mb_id, i.ins_name, r.review_brd_title, r.review_brd_viewcnt"
		+ " FROM review_brd r, mb m, class cl, ins i"
		+ " WHERE m.mb_uid = r.mb_uid"
		+ " AND cl.class_uid = r.class_uid"
		+ " AND cl.ins_uid = i.ins_uid";
	
	
	// 리뷰 검색 조건) 회원ID
	public static final String SQL_SELECT_REVIEW_BRD_WHERE_USER_ID =  
		" AND m.mb_id LIKE ('%?%')";
	
	// 리뷰 검색 조건) 리뷰제목
	public static final String SQL_SELECT_REVIEW_BRD_WHERE_REVIEW_TITLE = 
	  	" AND r.review_title LIKE ('%?%')";
	
	// 리뷰 검색 조건) 리뷰내용
	 public static final String SQL_SELECT_REVIEW_BRD_WHERE_REVIEW_CONTENT = 
		" AND r.review_content LIKE ('%?%')";
	
	// 리뷰 목록 정렬
	public static final String SQL_ORDER_REVIEW =  
		" ORDER BY review_brd_uid DESC";
		

	// 리뷰 내용
	public static final String SQL_SELECT_REVIEW_BY_UID =  
		"SELECT r.review_brd_uid, m.mb_name, m.mb_id, i.ins_name, r.review_brd_title, r.review_content, r.review_brd_viewcnt"
		+ " FROM review_brd r, mb m, class cl, ins i"
		+ " WHERE r.review_brd_uid = ?"
		+ " AND m.mb_uid = r.mb_uid"
		+ " AND cl.class_uid = r.class_uid"
		+ " AND cl.ins_uid = i.ins_uid";
	
		
	// 조회수 처리는?
	public static final String SQL_UPDATE_REVIEW_INC_VIEWCNT = 
		"UPDATE review_brd "
		+ " SET review_brd_viewcnt = review_brd_viewcnt + 1 "
		+ " WHERE review_brd_uid = ?";	
	
	
	public static final String SQL_DELETE_REVIEW_BY_UID =
		"DELETE FROM review_brd WHERE review_brd_uid = ?";		
	
	
	// 리뷰uid에 해당하는 댓글 리스트 불러오기
	public static final String SQL_SELECT_REP_BY_UID = 
		"SELECT m.mb_name, re.rep_uid, re.rep_content"
		+ " FROM review_brd r, rep re, mb m"
		+ " WHERE r.review_brd_uid = ?"
		+ " AND m.mb_uid = re.mb_uid"
		+ " AND r.review_brd_uid = re.review_brd_uid";
	
	// 댓글의 URD는 로그인된 회원과 동일한 UID/ID를 가질때?
	// 댓글 작성
	public static final String SQL_INSERT_REVIEW_BRD = 
		"INSERT INTO rep(rep_content) VALUES (?)";
	
	
	// 댓글 수정
	public static final String SQL_UPDATE_REVIEW_BRD_BY_UID = 
		"UPDATE rep SET rep_content=? WHERE rep_uid = ?";
	
	
	// 댓글 삭제
	public static final String SQL_DELETE_REVIEW_BRD_BY_UID = 
		"DELETE FROM rep WHERE rep_uid= ?";
		

		
	
	
	
	
	
	
//  ####################################   4. IT News 관련 SQL문     ####################################
	
//  =============== 사용자용  ==============			

	// 뉴스 리스트, 등록된 시간 순서로 조회

	public static final String SQL_SELECT_NEWS_BRD = 
		"SELECT * FROM news_brd";
	
	// 뉴스 검색 조건) 뉴스UID
	public static final String SQL_SELECT_NEWS_BRD_WHERE_UID = 
		" WHERE news_brd_uid = ?";

	// 뉴스 검색 조건) 뉴스 제목
	public static final String SQL_SELECT_NEWS_BRD_WHERE_TITLE = 
		" WHERE news_brd_title LIKE ('%?%')";

	// 뉴스 검색 조건) 뉴스 내용
	public static final String SQL_SELECT_NEWS_BRD_WHERE_CONTENT = 
		" WHERE news_brd_content LIKE ('%?%')";
	
	// 뉴스 검색 결과 정렬
	public static final String SQL_ORDER_BY_NEWS_BRD = 
		" ORDER BY ? ?";
	
	// 조회수 처리는?
	public static final String SQL_UPDATE_NEWS_BRD_INC_VIEWCNT = 
		"UPDATE news_brd "
		+ " SET news_brd_viewcnt = news_brd_viewcnt + 1 "
		+ " WHERE news_brd_uid = ?";	
	
	
//  =============== 관리자용  ==============	

	public static final String SQL_INSERT_NEWS_BRD = 
		"INSERT INTO news_brd "
		+ " (news_brd_title, news_brd_img, news_brd_content)"
		+ " VALUES(?,?,?)";
	
	public static final String SQL_UPDATE_NEWS_BRD_BY_UID = 
		"UPDATE news_brd"
		+ " SET news_brd_title = ?, news_brd_content = ?, news_brd_img = ?"
		+ " WHERE news_brd_uid = ?";
		
	public static final String SQL_DELETE_NEWS_BRD_BY_UID = 
		"DELETE "
		+ " FROM news_brd "
		+ " WHERE news_brd_uid = ?";			
	
}