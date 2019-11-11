<#include "base.ftl">

<#macro title>Join</#macro>

<#macro content>
    <div class="row">
       <br>
       <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
           <span><h6 class="centre text-red">left ${post.requiredPlayers} place</h6></span>
           <a href="#" class="profile"><img
                   src="${post.game.picture}"></a>
           <div>
               <button type="submit" class="btn btn-danger text-center" name="join">
                   <a href="http://localhost:8088/join?postId=${post.id}">Присоединиться</a></button>
               <button type="submit" class="btn btn-danger text-center" name="chat">
                   <a href="http://localhost:8088/chat?chatId=${post.chat.id}">Общий чат</a></button>
           </div>

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
    </div>
</#macro>

<@main/>