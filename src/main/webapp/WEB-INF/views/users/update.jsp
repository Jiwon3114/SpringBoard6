<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet"  href="/css/common.css" />

<style>
	
	td {
	   padding : 10px;
	   width : 700px;
	   text-align: center; 	   
	}
	
	td:nth-of-type(1) {
	   width: 200px;
	} 
	
	input { width : 100%; }
	input[type=submit] { width:100px; }
	
    input[readonly]  { background: #e0e0e0; }


</style>

</head>
<body>
  <main>
	<h2>사용자 수정</h2>
	<form action="/Users/Update" method="POST">
	  <table>
	    <tr>
	      <td>아이디(6~12)</td>
	      <td><input type="text" name="userid"  maxlength="12" 
	          value="${ user.userid }" readonly /></td>
	    </tr>
	    <tr>
	      <td>이전 암호</td>
	      <td><input type="password"  id="pwd1" /></td>
	    </tr>
	    <tr>
	      <td>새 암호</td>
	      <td><input type="password" name="passwd" id="pwd2"/></td>
	    </tr>
	    <tr>
	      <td>새 암호확인</td>
	      <td><input type="password" id="pwd3" /></td>
	    </tr>
	    <tr>
	      <td>이름</td>
	      <td><input type="text" name="username" 
	          value="${ user.username }" /></td>
	    </tr>
	    <tr>
	      <td>이메일</td>
	      <td><input type="text" name="email"
	         value="${ user.email }" /></td>
	    </tr>
	    <tr>
	      <td>포인트</td>
	      <td><input type="text" 
	         value="${ user.upoint }" readonly /></td>
	    </tr>
	    <tr>
	      <td>가입일</td>
	      <td><input type="text" 
	         value="${ user.indate }" readonly /></td>
	    </tr>
	    <tr>	      
	      <td colspan="2">
	      <input type="submit" value="등록" />
	      </td>
	    </tr>
	  </table>
	</form>
  </main>	
    
  <script>

    const  formEl = document.querySelectorAll("form")[0];
    
    formEl.addEventListener('submit', function(e) {
    	// alert('ok')  
    	
    	// 이전 암호
    	const  inputEl2 = document.querySelector('#pwd1')
    	if( inputEl2.value.trim() == '' ) {
    		alert('이전 암호가 입력되지 않았습니다')
    		e.stopPropagation();
    		e.preventDefault();
    		inputEl2.focus();
    		return false
    	}
    	
    	// db 의 이전암호 ${ user.passwd } 와 입혁한 이전암호가 일치하는지 체크
    	if(  '${ user.passwd }' != inputEl2.value  ) {
    		alert('이전 암호가 틀립니다')
    		e.stopPropagation();
    		e.preventDefault();
    		inputEl2.focus();
    		return false 
    	}  // `\${   }` : 자바스크립트 템플릿 문자이 이상발생 : jsp
    	
    	// 새 암호
    	const  inputEl3 = document.querySelector('#pwd2')
    	if( inputEl3.value.trim() == '' ) {
    		alert('새 암호가 입력되지 않았습니다')
    		e.stopPropagation();
    		e.preventDefault();
    		inputEl3.focus();
    		return false
    	}
    	
    	// 새 암호확인
    	const  inputEl4 = document.querySelector('#pwd3')
    	if( inputEl4.value.trim() == '' ) {
    		alert('새 암호확인이 입력되지 않았습니다')
    		e.stopPropagation();
    		e.preventDefault();
    		inputEl4.focus();
    		return false
    	}
    	
    	// 암호 == 암호확인
    	if( inputEl3.value != inputEl4.value ) {
    		alert('암호가 일치하지 않습니다')
    		e.stopPropagation();
    		e.preventDefault();
    		inputEl4.focus();
    		return false
    	}
		
    	// 이름
    	const  inputEl5 = document.querySelector('[name="username"]')
    	if( inputEl5.value.trim() == '' ) {
    		alert('이름이 입력되지 않았습니다')
    		e.stopPropagation();
    		e.preventDefault();
    		inputEl5.focus();
    		return false
    	} 
    	
    	// 이메일
    	const  inputEl6 = document.querySelector('[name="email"]')
    	if( inputEl6.value.trim() == '' ) {
    		alert('이메일이 입력되지 않았습니다')
    		e.stopPropagation();
    		e.preventDefault();
    		inputEl6.focus();
    		return false
    	} 
    })
    

  </script>
  
  
  
</body>
</html>



