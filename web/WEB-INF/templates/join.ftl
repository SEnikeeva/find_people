<#include "base.ftl">

<#macro title>Join</#macro>

<#macro content>
    <form action="/join " method="post">
        <div class="container-fluid" style="padding-top: 100px">
            <br>
            <button type="submit" class="btn btn-danger"  value="save">Присоединиться</button>
            <label>
                <input class="checkbox" name="join" value="" type="checkbox">
                <span>Присоединиться?</span>
            </label>
    </form>
</#macro>

<@main/>