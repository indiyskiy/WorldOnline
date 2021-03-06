<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" media="screen" href="css/main.css">
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/spoiler.css" type="text/css"/>
    <title>weather</title>
</head>
<body>
<header>
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <div class="span8">
                    <span class="brand">${title}</span>
                </div>
                <ul class="nav">
                    <li><a href="index">На главную</a></li>
                    <li><a href="allcards">Все карточки</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<div class="container container-lower">
    Погода на ${time}
    <br/>
    Температура ${temperature}
    <br/>
    Облачность - ${cloudiness}
    <br/>
    Ветер ${wind}
    <br/>
    Осадки: ${precipitation}
</div>
</body>
</html>
