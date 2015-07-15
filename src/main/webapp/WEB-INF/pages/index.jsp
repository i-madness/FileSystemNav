<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<link href="/static/css/bootstrap.css" rel="stylesheet">
	<title>Навигация по ФС сервера</title>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">Server FS navigation</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/preferences">Настройки</a></li>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div>

	<div class="container col-md-8 col-md-offset-2">
		<form id="idForm">
			<label for="pathInput">Путь к папке: </label>
			<input id="pathInput" value="${initialDirectory}" pattern="((\S+\s*)+[/])+" type="text" style="width: 30%; padding: 6px; margin: 15px" />
			<input class="btn btn-primary" type="submit" value="Вывести папку" /> <br />
			<div>Текущая папка: <span id="folderResponse">C:/</span></div>
			<div><span class="glyphicon glyphicon-arrow-up"></span><span id="parentUp"></span></div>
		</form>

		<table id="folder-view" class="table table-bordered table-hover">
		</table>
	</div><!-- /.container -->




	<div class="modal fade bs-example-modal-lg" id="fileView" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="#fileName"></h4>
				</div>
				<div class="modal-body" id="fileContent">
					...
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
				</div>
		</div>
	</div>

	<script type="text/javascript">
		var currentDir='${initialDirectory}';

		// linking folders in table
		$('body').on('click','.folderLink', function() {
			currentDir = $(this).children('.tdName').text();
			$('#pathInput').val($('#pathInput').val()+currentDir+'/')
			$('#idForm').submit();
		});

		// linking parent folder
		$('body').on('click','#parentUp', function() {
			var dir = $('#pathInput').val();
			if (dir.length > 3) {
				var pos = dir.length - 2;
				while (dir[pos] != '/')
					pos--;
				$('#pathInput').val(dir.substr(0, pos+1))
				$('#idForm').submit();
			}
		});

		$('body').on('click','.fileLink', function() {
			currentDir = $('#pathInput').val();
			$.get('/file/'+currentDir.split("/").join("-_-")+$(this).children('.tdName').text().replace('.','-__-'), function(ReadableFile) {
				$('#fileName').text(ReadableFile.name);
				$('#fileContent').empty();
				var content;
				for (var i = 0; i < ReadableFile.content.length; i++)
					$('#fileContent').append('<p>'+ReadableFile.content[i]+'</p>')
			});
			$("#fileView").modal()
		});

		$('#idForm').submit(function(e) {
			currentDir = $('#pathInput').val();
			$.get('/dir/' + currentDir.split("/").join("-_-"), function(directory) {
				$('#folderResponse').text(directory.name);
				$('#parentUp').text(directory.parent==null ? '(корневая директория)' : directory.parent);
				$('#folder-view').empty();
				$('#folder-view').append('<thead><td>/</td><td>Имя</td><td>Тип</td></thead>')
				if (directory.files!==undefined) {
					for (var i = 0; i < directory.files.length; i++) {
						$('#folder-view').append('<tr class="fileLink"><td align="center"><span class="glyphicon glyphicon-file"></span></td><td class="tdName">' + directory.files[i] + '</td><td align="center">Файл</td>')
					}
				}
				if (directory.subdirectories!==undefined) {
					for (var i = 0; i < directory.subdirectories.length; i++) {
						$('#folder-view').append('<tr class="folderLink"><td align="center"><span class="glyphicon glyphicon-folder-close"></span></td><td class="tdName">' + directory.subdirectories[i] + '</td><td align="center">Папка</td>')
					}
				}
			});
			e.preventDefault(); // prevent form submit
		});

		// submitting data after page loads
		$('#idForm').submit();
	</script>
	<script src="/static/js/bootstrap.min.js"></script>
</body>
</html>