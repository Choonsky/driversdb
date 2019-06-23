<%--
  Created by IntelliJ IDEA.
  User: nemirovsky
  Date: 20.06.2019
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="com.example.driversdb.entity.User"
%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="Сервлет работы с БД поиска водителей">
  <meta name="author" content="Stanislav Nemirovsky">
  <title >Сервлет интерфейса БД поиска водителей</title>
  <link rel="stylesheet" type="text/css" media="all" href="../css/bootstrap.css">
  <link rel="stylesheet" type="text/css" media="all" href="../css/font-awesome.css">
  <link rel="stylesheet" type="text/css" media="all" href="../css/styles.css">
  <script src="js/jquery-3.4.1.min.js"></script>
</head>
<body class="bg-light">

<div class="container align-content-center text-center pt-5">
  <h3 class="mb-3 pt-5 font-weight-normal align-content-center">Сервлет интерфейса БД поиска водителей</h3>

  <% User currentUser = (User)session.getAttribute("currentUser");%>

  <h3 class="mb-3 blue">Добро пожаловать, <strong><%= currentUser.getFullname() + "</strong> в лице <strong>" +
  currentUser.getUsername() + "</strong>!</h3>"%>
  <div class="row">
    <div class="col-2 offset-5">
    <form action="LogoutServlet" method="post">
    <input type="submit" value="Выйти из системы" >
    </form>
    </div>
  </div>
  <div class="row">
    <div class="col-4">
      <div class="panel panel-primary">
        <div class="panel-heading"><p><strong>Поиск по имени</strong></p></div>
        <div class="panel-body">
          <form action="DbSearch" method="post">
            <div class="row form-group has-feedback">
              <div class="col-11">
                <input type="text" class="form-control textField" id="familyName" name="familyName" placeholder="Фамилия" required>
              </div>
            </div>
            <div class="row form-group has-feedback">
              <div class="col-11">
                <input type="text" class="form-control textField" id="firstName" name="firstName" placeholder="Имя" required>
              </div>
            </div>
            <div class="row form-group has-feedback">
              <div class="col-11">
                <input type="text" class="form-control textField" id="secondName" name="secondName" placeholder="Отчество" required>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-4 offset-4">
                <button type="button" name="searchType" value="byDriver" class="ajax-btn btn btn-primary btn-block btn-flat">Искать</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="col-4">
      <div class="panel panel-primary">
        <div class="panel-heading"><p><strong>Поиск по госномеру</strong></p></div>
        <div class="panel-body">
          <form action="DbSearch" method="post">
            <div class="row form-group has-feedback">
              <div class="col-11">
                <input type="text" class="form-control textNumField" id="plate" name="plate" placeholder="Госномер" required>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-4 offset-4">
                <button type="button" name="searchType" value="byCar" class="ajax-btn btn btn-primary btn-block btn-flat">Искать</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="col-4">
      <div class="panel panel-primary">
        <div class="panel-heading"><p><strong>Поиск по городу</strong></p></div>
        <div class="panel-body">
          <form action="DbSearch" method="post">
            <div class="row form-group has-feedback">
              <div class="col-11">
                <input type="text" class="form-control" id="cityName" name="cityName" placeholder="Город" required>
              </div>
            </div>
            <div class="row">
              <div class="form-group col-4 offset-4">
                <button type="button" id="byCity" name="byCity" value="byCity" class="ajax-btn btn btn-primary btn-block btn-flat">Искать</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
    <div class="row">
      <div id="ajaxDbSearchResponse" class="col-10 offset-1">
      </div>
    </div>
</div>

<script>
    $('.ajax-btn').click(function() {
      var regex = new RegExp("^$|^[а-яА-Я]+$");
      var regexNum = new RegExp("^$|^[а-яА-Я0-9]+$");
      var searchType = $(this).val();
      if (searchType == "byCity") {
        var cName = $('#cityName').val();
        if (cName == "") alert ('Введите что-нибудь, пожалуйста!');
        else if (regex.test(cName)) {
          $.ajax({
            url : 'DbSearch',
            contentType : 'text/html; charset=UTF-8',
            data : {
              cityName : cName,
              searchType : 'byCity'
            },
            success : function(responseText) {
              $('#ajaxDbSearchResponse').html(responseText);
            }
          });
        } else alert ('Введите только русские буквы, пожалуйста!');
      } else if (searchType == "byDriver") {
        var fName = $('#firstName').val();
        var sName = $('#secondName').val();
        var famName = $('#familyName').val();
        if ((fName == "") && (famName == "") && (sName == "")) alert ('Введите какие-то буквы, пожалуйста!');
        if (regex.test(fName) && regex.test(famName) && regex.test(sName)) {
          $.ajax({
            url : 'DbSearch',
            contentType : 'text/html; charset=UTF-8',
            data : {
              firstName : fName,
              familyName : famName,
              secondName : sName,
              searchType : 'byDriver'
            },
            success : function(responseText) {
              $('#ajaxDbSearchResponse').html(responseText);
            }
          });
        } else alert ('Введите только русские буквы, пожалуйста!');
      } else {
        var pl = $('#plate').val();
        if (regexNum.test(pl)) {
          $.ajax({
            url : 'DbSearch',
            contentType : 'text/html; charset=UTF-8',
            data : {
              plate : pl,
              searchType : 'byCar'
            },
            success : function(responseText) {
              $('#ajaxDbSearchResponse').html(responseText);
            }
          });
        } else alert ('Введите только русские буквы или цифры, пожалуйста!');
      }
    });

</script>

</body>
</html>