<%@ include file="common/header.jsp" %>
<main role="main" class="container">
	<div class="starter-template">
		<h1><spring:message code="app.common.title" /></h1>
		<p class="lead">
			You can choose your default language from below.<br>
			<a href="?lang=en"><spring:message code="app.common.lang.english" /></a> | 
			<a href="?lang=hi"><spring:message code="app.common.lang.hindi" /></a>
			<br>
			Use this document as a way to quickly start any new project.<br>
			All you get is this text and a mostly barebones HTML document.
		</p>
	</div>
</main>
<%@ include file="common/footer.jsp" %>
