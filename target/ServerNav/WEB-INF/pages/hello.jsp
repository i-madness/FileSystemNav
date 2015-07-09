<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
	<h1>${garrr}</h1>
	<form id="idForm">
		<label for="pathInput">Путь к папке: </label><input name="id" id="pathInput" value="C:" type="text" />
		<input type="submit" value="Вывести папку" /> <br /><br/>
		<div id="personIdResponse"> </div>
	</form>

	<table id="folder-view">
		<thead>
			<td>/</td>
			<td>Имя</td>
			<td>Тип</td>
		</thead>

	</table>

	<script type="text/javascript">
		// Request Person by ID AJAX
		$('#idForm').submit(function(e) {
			var pathToDir = $('#pathInput').val();
			//if(!validatePersonId(personId))
			//	return false;
			$.get('/dir/' + pathToDir, function(directory) {
				$('#personIdResponse').text('Name: '+directory.name + ' ___ Path: ' + directory.path);
				$('#folder-view').empty();
				for (var f in directory.files) {
					$('#folder-view').append("<tr><td></td><td>" + f + "</td><td>Файл</td>")
				}
				for (var d in directory.subdirectories) {
					$('#folder-view').append("<tr><td></td><td>" + d + "</td><td>Папка</td>")
				}
			});
			e.preventDefault(); // prevent actual form submit
		});
	</script>
</body>
</html>