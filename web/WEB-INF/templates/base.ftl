<#macro content>
</#macro>

<#macro title></#macro>

<#macro main>
    <html>
    <head>
        <meta charset="utf-8">
        <meta name="view-port">
        <link rel='stylesheet' href='/css/bootstrap.min.css'>
        <script src="/js/jquery-3.3.1.min.js"></script>
        <script src="/js/popper.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="/css/add.css">
        <link rel="stylesheet" href="/css/forum.css">
        <link rel="stylesheet" href="/css/gijgo.min.css">
        <link rel="stylesheet" href="/css/help.css">
        <link rel="stylesheet" href="/css/rating.css">
        <link rel="stylesheet" href="/css/sticky-footer.css">
    </head>
    <body >
    <ul class="body_slides" style="height: max-content">
        <li></li>
        <li></li>
        <li></li>
    </ul>
    <nav class="navbar navbar-expand-md navbar-light sticky-top">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarResponsive">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="navbar-item active">
                        <a href="/logout" class="text-warning nav-link">Выйти</a>
                    </li>
                    <li class="navbar-item">
                        <a href="/registration" class="text-warning nav-link">Зарегистрироваться</a>
                    </li>


                    <li class="navbar-item active">
                        <a href="/index" class="text-warning nav-link">Главная</a>
                    </li>
                    <li class="navbar-item">
                        <a href="/profile" class="text-warning nav-link">Профиль</a>
                    </li>
                    <li class="navbar-item">
                        <a href="/statistic" class="text-warning nav-link">Статистика</a>
                    </li>
                    <li class="navbar-item">
                        <a href="/search" class="text-warning nav-link">Поиск</a>
                    </li>
                </ul>
            </div>

        </div>
    </nav>

         <@content/>


    <div class="row padding " >
        <div class="container-fluid padding">

            <div class="navbar-item text-block col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <a href="#" class="text-muted btn btn-link">
                    Напишите нам
                </a>
                <br>
                <a href="#" class="text-muted btn btn-link">
                    Помощь
                </a>
            </div>
        </div>
    </div>
    </body>
    </html>
</#macro>