<#include "base.ftl">

<#macro title>Settings</#macro>

<#macro content>
    <form action="/settings" method="post"  enctype="multipart/form-data" style="width: max-content; height: max-content" >
        <div class="container-fluid" style="width: 1500px; height: 1000px">
            <div class="row background-login">
                <div class="col-xs-0 col-sm-1 col-md-2"></div>
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-xs-4 col-sm-12 col-md-10 col-lg-4">
                            <br>
                            <a href="#" class="profile"><img
                                    src="${user.profilePicture}"></a>
                            <p>
                                <div class="text-center" style="padding-left: 40px; color: #FFFFFF">
                                <label for="photo">Изменить картинку</label>
                            <p><input id="photo" type="file" name="file"/></p>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-8">
                                <div class="form-group">
                                    <label for="inputUsername" class="text-red">Username</label>
                                    <input type="text" class="form-control" id="inputUsername" placeholder="Enter username"
                                           name="username" >
                                </div>
                                <div class="form-group">
                                    <label for="inputPasswordFirst">Password</label>
                                    <input type="password" class="form-control" id="inputPasswordFirst" placeholder="Password"
                                           name="password">
                                </div>
                                <input type="submit" class="btn btn-danger" name ="save" value="Сохранить изменения">
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </form>
</#macro>

<@main/>