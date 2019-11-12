<#include "base.ftl">

<#macro title>Chat</#macro>

<#macro content>
    <form method="post" class="mr-auto">
        <div class="container" style="background: #FFFFFF; height: max-content;">
            <div class="row">
                <div class="panel panel-default">
                    <div class="panel-heading">Chat</div>
                    <div class="panel-body">
                        <#list messages as message>
                        <div class="container">
                            <div class="row message-bubble">
                                <p class="text-muted">
                                    <img src="${message.sender.profilePicture!}" width="75" height="75">
                                    ${message.sender.username!}</p>
                                <span>${message.text!}</span>
                                <span>${message.timesent!}</span>
                            </div>
                        </div>
                        </#list>
                        <div class="panel-footer">
                            <div class="input-group">
                                <input type="hidden" name="chatId" value="${chatId}"/>
                                <input type="text" placeholder="type a message" name="new_message" autofocus/>
                    <button class="btn btn-default" type="submit" value="save">Send</button>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</#macro>

<@main/>