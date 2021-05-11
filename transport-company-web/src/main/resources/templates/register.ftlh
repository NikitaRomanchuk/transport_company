<html>
<#import 'importFiles.ftl' as importFiles>
<#import 'spring.ftl' as spring>
<head>
    <title>Register</title>
    <@importFiles.files/>
</head>
<div class="container">
    <@spring.bind "userForm"/>
    <#if error??>
        <#list errors as e>
            <p>${e.getDefaultMessage()}<p/>
        </#list>
    </#if>

    <form method="post" action="/register">
        <label>Email</label>
        <@spring.formInput "userForm.email"/>
        <@spring.showErrors "<br>" "error"/>
        <label>Password</label>
        <@spring.formInput "userForm.password"/>
        <@spring.showErrors "<br>" "error"/>
        <label>Password repeat</label>
        <@spring.formInput "userForm.passwordRepeat"/>
        <@spring.showErrors "<br>" "error"/>

        <input type="submit" value="register">
    </form>
    <a href="/login">
        Login
    </a>
</div>
</html>