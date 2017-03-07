<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Настройки</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <link href="/static/css/bootstrap.css" rel="stylesheet">
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
        <li class="active"><a href="#">Настройки</a></li>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</div>

<div>
  <div id="message" class="alert alert-success col-md-4 col-md-offset-4" style="display: none">
    Изменения успешно сохранены
  </div>
</div>

<div class="container col-md-8 col-md-offset-2">
  <h2>Настройки:</h2><hr/>
  <form id="prefForm">
    <div class="row prefs">
      <div class="col-md-4">Начальная папка</div>
      <div class="col-md-4"><input data-toggle="tooltip" data-placement="right" title="Введите директорию в формате: Root:/Dir/SubDir" id="initial" value="${InitialDir}"></div>
    </div>
    <hr/>
    <div class="row prefs">
      <div class="col-md-4">Максимальный уровень вложенности:</div>
      <div class="col-md-4"><input data-toggle="tooltip" data-placement="right" title="Введите четырёхзначное число" id="nesting" pattern="^[0-9]{1,4}" value="${MaxNestingLevel}"></div>
    </div>
    <hr/>
    <div class="row prefs">
      <div class="col-md-4">Отображать скрытые файлы</div>
      <div class="col-md-4"><input id="hidden" type="checkbox" <c:if test="${ShowHidden}"><%out.print("checked");%></c:if>></div><br/>
    </div>
    <hr/>
    <div class="row prefs">
      <div class="col-md-4">Отображать только папки</div>
      <div class="col-md-4"><input id="folders" type="checkbox" <c:if test="${ShowFoldersOnly}"><%out.print("checked");%></c:if>></div><br/>
    </div>
    <hr/>
    <div>
      <input type="submit" formaction="prefForm" class="btn btn-primary"    value="Сохранить изменения">
      <input type="button" class="btn btn-info" onclick="backToDefaults()"  value="Восстановить значения по умолчанию">
      <input type="button" class="btn" onclick="window.location.href='/'"   value="Отменить">
    </div>
  </form>
  <br/>

</div><!-- /.container -->

<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/preferences.js"></script>
</body>
</html>