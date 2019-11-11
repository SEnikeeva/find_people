<#include "base.ftl">

<#macro title>Registration</#macro>

<#macro content>
    <form action="/registration" method="post"  enctype="multipart/form-data">
        <div class="container-fluid" style="padding-top: 10px">
            <br>
                <div class="row">
                    <div class="rounded col-md-12 background-login" align="center">
                        <div class="text-red">
                            <span><h1>Регистрация</h1></span>
                            <br>
                            <br>
                            <br>
                            <div class="form-group">
                                <label for="inputUsername">Username<span class="required">*</span></label>
                                <input type="text" class="form-control" id="inputUsername" placeholder="Enter username"
                                       name="username" required pattern="^([a-zа-яё]+)$">
                            </div>
                            <div class="form-group">
                                <label for="inputPasswordFirst">Password<span class="required">*</span></label>
                                <input type="password" class="form-control" id="inputPasswordFirst" placeholder="Password"
                                       name="password" pattern="^\S{8,}" required>
                            </div>
                            <div class="form-group">
                                <label for="inputPasswordSecond">Password again<span class="required">*</span></label>
                                <input type="password" class="form-control" id="inputPasswordSecond" placeholder="Password"
                                       name="password" pattern="^\S{8,}$"
                                       onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');"  required>
                            </div>
                            <br>
                            <label for="photo">Photo<span class="required">*</span></label>
                            <p><input id="photo" type="file" name="file"/></p>
                            <br>
                            <button type="submit" class="btn btn-danger"  value="save">Register</button>
                            <label>
                                <input class="checkbox" name="rememberme" value="" type="checkbox">
                                <span>Remember me</span>
                            </label>
                        </div>
                    </div>
                </div>

        </div>

    </form>
<script>
    function vusr() {

        uname = document.getElementById("inputUsername").text();
        alert('sdsfsdf');
        alert(uname)
    }
</script>
</#macro>

<@main/>