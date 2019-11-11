<#include "base.ftl">

<#macro title>Join</#macro>

<#macro content>

<form method="get" class="form-inline md-form form-sm mt-0 mr-auto">
    <i class="fas fa-search" aria-hidden="true"></i>
    <input class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Поиск по играм"
        aria-label="Search" id="query" oninput="f()">
</form>

<div id="res"></div>

    <script type="application/javascript">
        function f() {
            if ($("#query").val().length >= 1) {
                $.ajax({
                    url: "/s",
                    data: {"query": $("#query").val()},
                    dataType: "json",
                    success: function (post) {
                        if (post.objects.length > 0) {
                            $("#res").html("");
                            for (var i = 0; i < post.objects.length; i++) {
                                $("#res").append("<li><a href='http://localhost:8088/post?gameName=" + post.objects[i].game.name + "'>" + post.objects[i].game.name + "</a></li>");
                            }
                        } else {
                            $("#res").html("No results..");
                        }
                    }
                })
            }
            else {
                $("#res").html("");
            }
        }
    </script>
</#macro>

<@main/>