<%@ include file="common/header.jsp" %>
<main role="main" class="container">
	<div class="row">
		<div class="col-12 text-center">
			<h1 class="display-4 fw-bold text-danger">Error!</h1>
		</div>
	</div>
	<div class="row">
		<div class="col">
			<c:if test="${ resourceError ne null }">
				<div class="alert alert-danger">
					${ resourceError }
					<br>
					<a class="alert-link" href="<c:url value="/products" />">Back to Products</a>
				</div>
			</c:if>
			<c:if test="${ error ne null }">
				<div class="alert alert-danger">
					${ error }
					<br>
					<a class="alert-link" href="<c:url value="/products" />">Back to Products</a>
				</div>
			</c:if>
		</div>
	</div>
</main>
<%@ include file="common/footer.jsp" %>
