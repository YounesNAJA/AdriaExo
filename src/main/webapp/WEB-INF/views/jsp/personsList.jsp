<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<spring:url value="/resources/core/css/home.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />

<spring:url value="/resources/core/js/bootstrap.min.js"
	var="bootstrapJs" />
<spring:url value="/resources/core/js/jquery.min.js" var="jquery" />
<spring:url value="/resources/core/js/personUtil.js" var="coreJs" />

<script src="${jquery}"></script>
<script src="${bootstrapJs}"></script>
<script src="${coreJs}"></script>
<title>Personne - Accueil</title>
</head>
<body>
	<br />
	<div class="container">
		<div class="container">
			<form:form id="personForm" method="POST" action="#"
				modelAttribute="person">

				<form:input type="hidden" path="id" id="id" />

				<div class="form-group">
					<form:label for="completeName" path="completeName">Nom complet</form:label>
					<form:input type="text" path="completeName"
						class="form-control" id="completeName"
						placeholder="Entez votre nom complet" />
				</div>

				<div class="form-group">
					<form:label path="phoneNum" for="phoneNum">Numéro de téléphone</form:label>
					<form:input type="text" path="phoneNum"
						class="form-control" id="phoneNum"
						placeholder="Entez votre numéro de téléphone" />
				</div>

				<div class="form-group">
					<form:label path="email" for="email">Email</form:label>
					<form:input type="email" path="email"
						class="form-control" id="email"
						placeholder="Entez votre email" />
				</div>

				<button id="addPerson" type="submit" class="btn btn-primary">Ajouter</button>

			</form:form>
		</div>

		<hr>

		<div class="container">
			<div class="personsList">
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Nom complet</th>
							<th scope="col">Numéro de téléphone</th>
							<th scope="col">Email</th>
							<th scope="col">#</th>
						</tr>
					</thead>
					<tbody id="personsData">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		personUtil.getAll();
		$('#addPerson').click(function(e) {
			var personObj = {
				"completeName" : $("#completeName").val(),
				"email" : $("#email").val().replace(/\s/g, ''),
				"phoneNum" : $("#phoneNum").val().replace(/\s/g, '')
			};
	
			e.preventDefault();
			$('.invalid-feedback').remove();
	
			personUtil.save(personObj);
		});
		
	</script>
</body>
</html>