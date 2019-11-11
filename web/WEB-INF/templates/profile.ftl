<#include "base.ftl">

<#macro title>Profile</#macro>

<#macro content>
   <div class="container-fluid">
       <div class="row background-info">
           <div class="col-xs-0 col-sm-1 col-md-2"></div>
           <div class="col-md-8">
               <div class="row">
                   <div class="col-xs-4 col-sm-12 col-md-10 col-lg-4">
                       <a href="#" class="profile"> <img src="${user.profilePicture!}" width="200"/></a>
                       <br>
                           <form action="/settings" method="get">
                               <div class="text-center  ml-auto">
                                   <button type="submit" class="btn btn-danger text-center">Редактировать</button>
                               </div>
                           </form>
                       <p>
                   </div>
                   <div class="col-xs-12 col-sm-12 col-md-10 col-lg-8">
                       <div class="text-red text-center">
                           <label>${user.username}</label>
                       </div>
                       <hr>
                       <div class="text-red text-center">
                           <label>Ваши посты</label>
                           <div class="container-fluid">
                               <div class="row background-info">
                                   <div class="col-xs-0 col-sm-1 col-md-1 col-lg-1"></div>
                                   <div class="col-md-9">
                                       <div class="row">
                                           <#list posts as post>
                                               <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
                                                   <span><h6 class="centre text-red">left ${post.requiredPlayers} place</h6></span>
                                                   <a href="#" class="profile"><img
                                                           src="${post.game.picture}"></a>
                                                   <p>
                                                   <div class="text-center">
                                                   <button type="submit" class="btn btn-danger text-center">
                                                       <a href="http://localhost:8088/chat?chatId=${post.chat.id}">Общий чат</a></button>
                                                   </div>
                                               </div>
                                               <div class="col-xs-12 col-sm-12 col-md-10 col-lg-8 ml-auto">
                                                   <div class="text-red text-center" style="margin-left: 200px">
                                                       <h4><label>${post.game.name}</label></h4>
                                                   </div>
                                                   <div class="text-red text-center" style="margin-left: 200px">
                                                       <label>Gamers</label>
                                                   </div>
                                                   <#list post.gamers as gamer>
                                                            <div class="row" style="margin-left: 200px">
                                                                <div class="btn btn-link">
                                                                    <img class="img-fluid" src="${gamer.profilePicture}" width="100" height="100">
                                                                </div>
                                                                <br>
                                                                <div class="text-muted btn btn-link">
                                                                    <h6>${gamer.username}</h6>
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
                       <form action="/createpost" method="get">
                           <div class="text-center">
                               <button type="submit" class="btn btn-danger text-center">Добавить пост</button>
                           </div>
                       </form>
                   </div>
               </div>
           </div>
       </div>
   </div>
</#macro>

<@main/>