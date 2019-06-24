<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Сервлет работы с БД автоштрафов">
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
    <div class="row">
        <div class="offset-4 col-4">
            <div class="panel panel-primary">
                <div class="panel-heading"><p><strong>Вход в систему</strong></p></div>
                <div class="panel-body">
                    <form action="Login" method="post">
                        <div class="row form-group has-feedback">
                            <div class="col-11">
                                <input type="username" class="form-control" name="u" placeholder="Логин">
                            </div>
                        </div>
                        <div class="row form-group has-feedback">
                            <div class="col-11">
                                <input type="password" class="form-control" name="p" placeholder="Пароль">
                            </div>
                        </div>
                        <div class="form-group text-center">
                            <label><input type="checkbox" name="rememberMe" value="true"> Запомнить меня на этом компьютере</label>
                        </div>
                        <div class="row">
                            <div class="form-group col-4 offset-4">
                                <button type="submit" value="Login" class="btn btn-primary btn-block btn-flat">Войти</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
