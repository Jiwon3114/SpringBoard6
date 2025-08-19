<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet"  href="/css/common.css" />

<style>
  #table {
  margin-bottom : 150px; 
  td {
     text-align : center;
     padding    : 10px;
     border     : 1px solid silver;
     
     &:nth-of-type(1) {
         width             :  150px;
         background-color  :  black;
         color             :  white;
     }
     &:nth-of-type(2) { width : 250px; }
     &:nth-of-type(3) {
         width             :  150px;
         background-color  :  black;
         color             :  white;
     }
     &:nth-of-type(4) { width : 250px;  }
  }  
  
  tr:nth-of-type(3) td:nth-of-type(2) {
     text-align : left;
  }
  
  tr:nth-of-type(4) td:nth-of-type(2) {
     height     : 250px;
     width      : 600px; 
     text-align : left;
     vertical-align: baseline;      
  }
  
  tr:last-of-type  td {
     background: white;
     color :     black; 
  }
   
 }
 
 /* class="btn btn-dark btn-sm" */
 a.btn.btn-dark.btn-sm:hover {
    text-decoration: none;    
 }

</style>

</head>
<body>
  <main>
    
    <!-- 메뉴 리스트 -->
   <%@include file="/WEB-INF/include/menus.jsp" %>

	<h2>${ menuDTO.menu_name } 게시글 내용 보기</h2>
	<table id="table">
		<tr>
			<td>글번호</td>
			<td>${ board.idx }</td>
			<td>조회수</td>
			<td>${ board.hit }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${ board.writer}</td>
			<td>작성일</td>
			<td>${ board.regdate}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3">${ board.title }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3">${ board.content } </td>
		</tr>
		<tr>
			<td colspan="4">
			[<a href="/Board/WriteForm?menu_id=${board.menu_id}">새글 쓰기</a>]&nbsp;&nbsp;
			
		  <c:if test="${ board.writer eq login.userid }">	
			[<a href="/Board/UpdateForm?idx=${board.idx}&menu_id=${board.menu_id}">수정</a>]&nbsp;&nbsp;
			[<a href="/Board/Delete?idx=${board.idx}&menu_id=${board.menu_id}">삭제</a>]&nbsp;&nbsp;
		  </c:if>	
			
			[<a href="/Board/List?menu_id=${board.menu_id}">목록</a>]&nbsp;&nbsp; 
			[<a href="/">Home</a>]			
			</td>
		</tr>
	</table>
  </main>	
    
  <script>
			const goListEl = document.getElementById('goList')
			goListEl.onclick = function() {
				location.href = '/Board/List?menu_id=${ menuDTO.menu_id }'
			}

			const formEl = document.querySelectorAll("form")[0];
			const titleEl = document.querySelector('[name="title"]');
			const writerEl = document.querySelector('[name="writer"]');
			formEl.addEventListener('submit', function(e) {
				if (titleEl.value == "") {
					alert("제목이 입력되지 않았습니다")
					titleEl.focus()
					e.preventDefault();
					e.stopPropagation();
					return false;
				}

				if (writerEl.value == "") {
					alert("작성자가 입력되지 않았습니다")
					writerEl.focus()
					e.preventDefault();
					e.stopPropagation();
					return false;
				}

				return true;
			})
		</script>
  
  
  
</body>
</html>



