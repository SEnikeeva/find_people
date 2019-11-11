<#include "base.ftl">

<#macro title>Create Post</#macro>

<#macro content>
    <form action="/createpost" method="post" enctype="multipart/form-data">
        <div class="container-fluid" style="padding-top: 100px">
            <br>
            <div class="row">
                <div class="rounded col-md-12 background-login text-red" align="center">
                    <span><h1>Add post</h1></span>
                    <span>And improve yourself</span>
                        <br>
                    <br>
                    <br>
                    <div class="form-group">
                        <label>Game title<span class="required">*</span></label>
                        <input type="text" class="form-control"
                               placeholder="Enter game title" name="title">
                    </div>
                    <div class="form-group">
                        <label>Number of players<span class="required">*</span></label>
                        <input type="number" class="form-control"
                               name="requiredPlayers">
                    </div>
                    <br>
                    <label for="photo">Photo<span class="required">*</span></label>
                    <p><input id="photo" type="file" name="file"/></p>
                    <br>
                    <button type="submit" class="btn btn-danger" value="save" >Add post</button>

                </div>
            </div>
    </form>
</#macro>
<@main/>