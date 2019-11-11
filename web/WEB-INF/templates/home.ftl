<#include "base.ftl">

<#macro title>Home</#macro>

<#macro content>
     <form action="/login" method="get">
         <div class="text-center">
             <button type="submit" class="btn btn-danger text-center">Войти</button>
         </div>
     </form>
</#macro>

<@main/>