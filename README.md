&#128209; 프로젝트 소개
=============
- ### __주제__
  - #### IT학원 정보 공유 플랫폼 개발
- ### __프로젝트 기획의도__
  - #### 학원은 광고의 수단, 학생은 쉽게 정보를 얻을 수 있는 공간 제작
- ### __나의 역할__ 
  - #### 5인으로 구성된 팀의 팀장
  - #### 전체적인 디자인과 프로젝트 오류수정 및 JSP Servlet Mapping, MVC2 모델을 활용하여 DTO, DAO 작성과 관리
  - #### 웹 서비스에 필요한 쿼리문 작성 <interface 활용>
  - #### Connection pool을 활용하여 요청할 때마다 이뤄지는 불필요한 커넥션 해결
  - #### 회원가입, 로그인, 로그아웃, 마이페이지, 관리자페이지 담당

<hr>

####  &#127916; 사용자 페이지 담당기능 시연 

![user_gif](https://user-images.githubusercontent.com/56028408/78549744-d29c0580-783d-11ea-87d4-ad72a6a34ebc.gif)

- #### 회원가입
  - #### 다음 우편번호 API 사용
  - #### 정규표현식을 사용하여 아이디, 비밀번호, 이메일 검증
    - ##### 비밀번호 정규식 : 8글자 이상, 특수문자와 대문자, 숫자는 필수 입력 항목
    - ##### 아이디 정규식 : 6글자 이상, 반복되는 문자 사용 불가능
  - #### javaScript를 사용하여 데이터 폼 검증
    - ##### 데이터 미 입력 시 회원가입 불가
  <br>
- #### 로그인, 로그아웃
  - ##### 로그인 성공 시 Session을 부여하여 권한과 로그인 여부를 체크 함
  - ##### 로그아웃 시 Session 제거
  <br>
- #### 마이페이지
  - ##### 마이페이지는 User Session을 보유한 사용자만 이용가능
  - ##### 다음 우편번호 API 사용
  - ##### COS 라이브러리를 활용하여 프로필 이미지 변경기능 구현
  - ##### 폼 검증을 활용하여 입력한 비밀번호가 올바를 경우에만 정보 변경 가능
  - ##### 학원 상세페이지에서 찜한 학원 목록을 테이블에 출력

<hr>

####  &#127916; 관리자 페이지 담당기능 시연 

![admin_gif](https://user-images.githubusercontent.com/56028408/78550197-9d43e780-783e-11ea-8861-ca705411c5da.gif)

- #### 과정관리
  - ##### 해당 학원에 등록된 과정을 출력
- #### 과정정보변경
  - ##### Months1은 필수 입력 항목이므로 미 입력 시 정보 변경 불가능 
  - ##### 클릭한 과정의 정보를 변경하고 삭제할 수 있음

