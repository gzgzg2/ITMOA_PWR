&#128209; 프로젝트 소개
=============
- ### __주제__
  - #### IT학원 정보 공유 플랫폼 개발
- ### __프로젝트 기획의도__
  - #### 학원은 광고의 수단, 학생은 쉽게 정보를 얻을 수 있는 공간 제작
- ### __나의 역할__ 
  - #### 5인으로 구성된 팀의 팀장
  - #### JSP Servlet Mapping, MVC2 모델을 활용하여 DTO, DAO 작성과 관리 및 프로젝트 오류수정과 전체적인 디자인 
  - #### Web 서비스에 필요한 쿼리문 작성 <interface 활용>
  - #### Connection pool을 활용하여 요청할 때마다 이뤄지는 불필요한 커넥션 해결
  - #### 로그인 했을 경우 필요한 데이터를 Session에 저장 
  - #### 회원가입, 로그인, 로그아웃, 마이페이지, 관리자페이지 담당

<hr>

####  &#127916; 사용자 페이지 담당기능 시연 

![user_gif](https://user-images.githubusercontent.com/56028408/78549744-d29c0580-783d-11ea-87d4-ad72a6a34ebc.gif)

- #### 회원가입
    - ##### form 에서 submit한 Parameter를 Command에 Request하여, Null이 아닌지와 자료형이 맞는지를 체크하고<br> 검증된 Parameter를 정규표현식으로 2차 검증 후, 중복된 데이터가 아니면 회원가입 승인
  - ##### 다음 우편번호 API 사용
  - ##### 정규표현식
    - ##### 비밀번호 정규식 : 8글자 이상 20글자 이하, 특수문자와 대문자, 숫자는 필수 입력 항목
        - ##### ```` ^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,20} ````
    - ##### 아이디 정규식 : 6글자 이상, 특수문자는 "_" 만 사용가능하고 반복되는 문자 사용 불가능
        - ##### ```` ^[a-zA-Z]{1}[a-zA-Z0-9_]{6,20}$ ````
  - ##### javaScript를 사용하여 데이터 폼 검증
    - ##### onSubmit 함수를 사용하여 데이터가 하나라도 입력되지 않았으면 return false로 submit이 실행되지 않음.
  <br>
- #### 로그인, 로그아웃
  - #### 로그인
    - ##### 로그인 요청 시, Controller에서 Parameter 검증 후 Parameter가 DAO의 login 메소드를 실행시킨 뒤, <br> DB에 저장된 정보와 일치하면 Login Session을 부여하고 Session이 정상적으로 부여됐을 때 로그인 요청을 승인한다.

  - #### 로그아웃 
    - ##### 로그아웃 요청 시, Controller에서 Session을 삭제한다. Session이 정상적으로 삭제되었으면 로그아웃 성공

  <br>
- #### 마이페이지
  - ##### MypageAction.Jsp를 거쳐 jstl core 를 사용하여 LoginSession을 보유했을 경우 마이페이지로 이동하고<br> Session이 없을 경우, 로그인 페이지로 이동함
  - ##### 다음 우편번호 API 사용
  - ##### enctype을 multipart/form-data로 설정하여 이미지 파일을 입력받고 Command에서 파일형식과 용량을 체크한다.<br> 검증이 완료되면 이미지 업로드 성공
  - ##### onSubmit 함수를 사용하여 입력한 비밀번호가 DB에 저장된 비밀번호와 같으면 변경요청을 승인하고 <br>정보가 다를경우 return false로 submit이 실행되지 않음.
  - ##### 학원 상세페이지에서 찜한 학원 목록을 테이블에 출력

<hr>

####  &#127916; 관리자 페이지 담당기능 시연 

![admin_gif](https://user-images.githubusercontent.com/56028408/78550197-9d43e780-783e-11ea-8861-ca705411c5da.gif)
- #### 관리자페이지 로그인
  - ##### Command에서 DB에 저장된 정보와 일치한지 확인하고,<br> Jstl core 라이브러리를 활용하여 Session에 담긴 level이 관리자 레벨일 경우 관리자페이지로 이동함
- #### 과정관리
  - ##### Command에서 Request 객체에 담긴 학원 명을 질의문에 입력하여 실행시킨 뒤, 해당하는 학원이 진행하고 있는 과정을 출력한다.  
- #### 과정정보변경
  - ##### 변경할 과정을 클릭하여 데이터를 수정한다. Submit된 데이터는 Command에서<br> Null 처리와 자료형이 일치하는지 확인하고, 검증된 데이터일 경우 DB에 저장한다.   
  - ##### 삭제할 과정의 id 값을 Command로 Request 하여 정확한 id 값일 경우, DB에서 해당 id의 과정을 삭제한다.

<br>
<hr>

## ITMOA 개발범위 화면 목록 

#### 사용자

<img width="840" alt="ITMOA_개발화면범위_사용자" src="https://user-images.githubusercontent.com/56028408/78647414-87452e00-78f5-11ea-86e5-324c6be83784.png">

#### 관리자

<img width="836" alt="ITMOA_개발화면범위_관리자" src="https://user-images.githubusercontent.com/56028408/78647475-9b892b00-78f5-11ea-8f02-70cc96ba1815.png">

<hr>

## ITMOA 데이터베이스 테이블 설계


<img width="686" alt="ITMOA_DB설계2" src="https://user-images.githubusercontent.com/56028408/78646934-c8890e00-78f4-11ea-9a8c-93c3f4f98bb5.png">


<img width="699" alt="ITMOA_DB설계" src="https://user-images.githubusercontent.com/56028408/78646871-adb69980-78f4-11ea-8d67-a7a1b60b79fe.png">

