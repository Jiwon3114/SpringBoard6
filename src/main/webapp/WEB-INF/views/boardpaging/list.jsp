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

  #table {
	td {
		padding: 10px;
		text-align: center; 
	}  
	
	td:nth-of-type(1) { width : 100px;  }
	td:nth-of-type(2) { width : 400px; text-align:left; }
	td:nth-of-type(3) { width : 100px;  }
	td:nth-of-type(4) { width : 100px;  }
	td:nth-of-type(5) { width : 100px;  }
	
	tr:first-child {
		background: #333;
		color: white;
		font-weight: 700;   /* 700 : bold */ 
		td {
		   border-color:silver;
		}
		& > td:nth-of-type(2) { text-align: center;  }
	}
	
	tr:nth-of-type(2) td {
		text-align: right;		
	}
  
  }
   
   
</style> 

</head>
<body>
  <main>	

    <!-- 메뉴 리스트 -->
    <%@include file="/WEB-INF/include/menuspaging.jsp" %>
	
	<%-- <h2>${ menu_id }게시물 목록</h2> --%>
	<h2>${ menuDTO.menu_name } 게시물 목록</h2> 
	<%-- <h2>${ param.menu_id }게시물 목록</h2 --%>
	
	<!--  게시물 목록 -->
	<table id="table">
	  <tr>
	    <td>번호</td>	 
	    <td>제목</td>
	    <td>작성자</td>
	    <td>날짜</td>
	    <td>조회수</td>
	  </tr>
	  
	  <tr>
	    <td colspan="5">
	      <a href="/BoardPaging/WriteForm?nowpage=${nowpage}&menu_id=${ menuDTO.menu_id }">
	      새 게시물 추가
	      </a>	 
	    </td>
	  </tr>
	
	  <c:forEach  var="board" items = "${ response.list }" >
	  <tr>
	    <td>${ board.idx      }</td>
	    <td>
	      <a href="/BoardPaging/View?idx=${board.idx}&nowpage=${nowpage}&menu_id=${menu_id}">
	    	${ board.title    }
	      </a>	
	    </td>
	    <td>${ board.writer   }</td>	   
	    <td>${ board.regdate  }</td>	   
	    <td>${ board.hit      }</td>	   
	  </tr>
	  </c:forEach>
	  
	</table>
	
	<!-- 페이지 번호 목록 -->
	 <%@include file="/WEB-INF/include/paging.jsp" %>
	
  </main>	
</body>
</html>




