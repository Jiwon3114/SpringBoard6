<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"     %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet"  href="/css/common.css" />
<!-- SCSS 문법가능하게하는 라이브러리 
<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>
 -->

<style>
   td {text-align: center;}
   
   tr:first-child {
   	   background-color : black;
   	   font-weight : bold;  
   	   /* SCSS 문법 (sass 문법중에 하나)
   	   : 별도 라이브러리 필요*/
   	   td {
       	   border-color:cyan;
   	   	   color : white;
       }      
   }
   
   td[colspan] {
       text-align : right; 
   }  	   
   
   /*
   tr:first-child  td {
       border-color:white;
   }
   */
   
</style> 

</head>
<body>
  <main>
	<h2>사용자 목록</h2>
	<table>
	  <tr>
	    <td>Userid</td>	 
	    <td>Username</td>
	    <td>Email</td>
	    <td>등급</td>
	    <td>Indate</td>
	    <td>삭제</td>
	    <td>수정</td>
	  </tr>
	  
	  <tr>
	    <td colspan="7">
	      <a href="/Users/WriteForm">새 사용자 추가</a>	 
	    </td>
	  </tr>
	
	  <c:forEach  var="user" items = "${ userList }" >
	  <tr>
	    <td>${ user.userid    }</td>
	    <td>${ user.username  }</td>
	    <td>${ user.email     }</td>	   
	    <td>${ user.memlevel  }</td>	   
	    <td>${ user.indate    }</td>	   
	    <td><a href="/Users/Delete?userid=${user.userid}">삭제</a></td>
	    <td><a href="/Users/UpdateForm?userid=${user.userid}">수정</a></td>
	  </tr>
	  </c:forEach>
	  
	</table>
  </main>	
</body>
</html>




