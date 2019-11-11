<#include "base.ftl">

<#macro title>Main Page</#macro>

<#macro content>
    <div>
        <form action="/createpost" method="get">
            <div class="text-center">
                <button type="submit" class="btn btn-danger text-center">Добавить пост</button>
            </div>
        </form>
    </div>

    <div class="text-red text-center">
        <label>Все игры</label>
        <div class="container-fluid">
            <div class="row background-info">
                <div class="col-xs-0 col-sm-1 col-md-1 col-lg-1"></div>
                <div class="col-md-9">
                    <div class="row">
                       <#list posts as post>
                           <br>
                           <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
                               <span><h6 class="centre text-red">left ${post.requiredPlayers} place</h6></span>
                               <a href="#" class="profile"><img
                                       src="${post.game.picture}"></a>
                                        <button type="submit" class="btn btn-danger text-center" name="join">
                                            <a href="http://localhost:8088/join?postId=${post.id}">Присоединиться</a></button>
                                       <button type="submit" class="btn btn-danger text-center" name="chat">
                                           <a href="http://localhost:8088/chat?chatId=${post.chat.id}">Общий чат</a></button>
                           </div>
                           <div class="col-xs-12 col-sm-12 col-md-10 col-lg-8">
                               <div class="text-red text-center" style="">
                                   <h2><label>${post.game.name}</label></h2>
                               </div>
                               <div class="text-red text-center">
                                   <label>Gamers</label>
                               </div>
                               <#list post.gamers as gamer>
                                    <div class="container-fluid">
                                        <div class="row">
                                            <a href="#" class="rounded text-block col-lg-4 col-md-4 col-sm-8 col-xs-12"
                                               style="text-align: center">
                                                <div class="btn btn-link">
                                                    <img class="img-fluid" src="${gamer.profilePicture}">
                                                </div>
                                                <br>
                                                <div class="text-muted btn btn-link">
                                                    <h6>${gamer.username}</h6>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                               </#list>
                           </div>
                       </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>

</#macro>
<@main/>