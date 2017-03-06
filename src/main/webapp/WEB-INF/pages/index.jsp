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
			<input id="pathInput" value="${initialDirectory}" pattern="((\S+\s*)+[/])+" placeholder="C:\Directory\Subdirectory\" data-toggle="tooltip" data-placement="bottom" title="Введите директорию в формате: Root:/Dir/SubDir" type="text" />
			<input class="btn btn-primary" type="submit" value="Вывести папку" /> <br />
			<div>Текущая папка: <span id="folderResponse">${initialDirectory}</span></div>
			<div class="btn-group dropdown">
				<button class="btn btn-default" id="parentBtn">
					<span class="glyphicon glyphicon-arrow-up"></span><span id="parentUp"></span>
				</button>
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownParent" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					<span class="caret"></span>
				</button>
				<ul id="parentMenu" class="dropdown-menu" aria-labelledby="dropdownMenu1">

				</ul>
			</div>

		</form>

		<table id="folder-view" class="table table-bordered table-hover">
		</table>
	</div><!-- /.container -->

	<div class="modal fade bs-example-modal-lg" id="fileView" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Просмотр файла: <span id="fileName"></span></h4>
				</div>
				<div class="modal-body" id="fileContent">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
        window.currentDir='${initialDirectory}';
	</script>
	<script src="/static/js/bootstrap.min.js"></script>
	<script src="/static/js/index.js"></script>
</body>
</html>