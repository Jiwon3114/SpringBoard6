<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 목록 추가</title>

<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet"  href="/css/common.css" /> 

</head>
<body>
  <main>
   <h2>Home</h2>
   <a href="/test">Test</a>
   <hr>
   
   <a href="/Menus/List">메뉴 목록</a><br>
   <a href="/Menus/WriteForm">새 메뉴 추가</a><br>
   <hr>
   
   <a href="/Users/List">사용자 목록</a><br />
   <a href="/Users/WriteForm">새 사용자 추가</a><br />
   <hr>
   
   <a href="/Board/List?menu_id=MENU01">게시물 목록</a><br />
   <a href="/Board/WriteForm?menu_id=MENU01">새 게시물 추가</a><br />
   <hr>
   
   <a href="/BoardPaging/List?nowpage=1&menu_id=MENU01">게시물 목록(페이징)</a><br />
   <a href="/BoardPaging/WriteForm?nowpage=1&menu_id=MENU01">새 게시물 추가(페이징)</a><br />
   <hr>
   
   <div>
      ${ sessionScope.login.username } 님 환영합니다<br>
      당신의 가입일은 ${ login.indate } 입니다<br>
      <a href="/Users/Logout">로그아웃</a>    
   </div> 
 
 
  </main>
   
</body>
</html>







