# 제로베이스 백엔드 스쿨_30기_신주연_[마스터반] Mission 1

## 개요
이 프로젝트는 제로베이스 백엔드 스쿨 **Mission 1**을 수행하기 위해 작성되었습니다.  
`Dynamic Web Project`를 기반으로 구현하였으며, 아래와 같은 파일 및 개발 환경을 포함합니다.


---

## 개발 환경
- **IDE:** Eclipse  
- **Project Type:** Dynamic Web Project  
- **Dynamic Web Module Version:** 3.1  
- **Application Server:** Apache Tomcat 9.0.97  
- **Java Version:** JDK 11.0.25+9  
- **Database:** SQLite (파일 기반)

---

## 프로젝트 설정
### 1. Eclipse 설정
1. **프로젝트 생성**  
   - Eclipse에서 **Dynamic Web Project**로 새 프로젝트 생성.
   - **Dynamic Web Module Version**을 3.1로 설정.

2. **Tomcat 서버 등록**  
   - **Window > Preferences > Server > Runtime Environments**에서 Tomcat 9.0.97을 추가.  
   - Tomcat 서버 설치 디렉터리를 설정.

3. **JDK 11 설정**  
   - **Window > Preferences > Java > Installed JREs**에서 JDK 11 추가.  
   - 프로젝트에서 JDK 11을 기본 JRE로 선택.

### 2. 데이터베이스 설정
1. 프로젝트 루트 경로에 위치한 **public_wifi.db** 파일 사용.  
   - SQLite 연결을 위해 JDBC 드라이버를 설정합니다.  
   - 예제 코드:  
     ```java
     String dbUrl = "jdbc:sqlite:public_wifi.db";
     Connection connection = DriverManager.getConnection(dbUrl);
     ```

---

## 실행 방법
1. Eclipse에서 프로젝트를 불러옵니다.
2. Tomcat 서버를 시작합니다.
3. 웹 브라우저에서 애플리케이션에 접속합니다.

