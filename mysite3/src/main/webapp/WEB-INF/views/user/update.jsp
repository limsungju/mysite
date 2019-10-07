<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">

				<form:form modelAttribute="userVo" id="join-form" method="post" action="${pageContext.servletContext.contextPath }/user/update">
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					<spring:hasBindErrors name="userVo"><!-- Controller에서 Error가 있으면 값이 셋팅, 없으면 다음 줄 실행 -->
						<c:if test='${errors.hasFieldErrors("name") }'> <!-- name 변수에 Error가 있는지 확인 -->
							<p style="font-wigth:bold; color:red; text-align:left; padding:2px 0 0 0">
								<spring:message code='${errors.getFieldError("name").codes[0] }' text='${errors.getFieldError("name").defaultMessage }' /> <!-- spring:message는 messages.properties의 값을 출력해주는 기능 -->
							</p>
						</c:if>
					</spring:hasBindErrors>

					<label class="block-label" for="email">이메일</label>
					<h4>${userVo.email }</h4>
					<form:hidden path="email" />
					
					<label class="block-label">패스워드</label>
					<form:password path='password' />
					
					<label class="block-label">성별</label>
					<p>
						<form:radiobuttons items="${userVo.genders }" path='gender' />
					</p>
					
					
					<input type="submit" value="수정하기">
					
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>