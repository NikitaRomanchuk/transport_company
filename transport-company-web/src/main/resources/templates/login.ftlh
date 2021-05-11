<html>
<#import 'importFiles.ftl' as importFiles>
<#import 'spring.ftl' as spring>
<head>
    <title>Login</title>
    <@importFiles.files/>
</head>
<div class="container">
    <form method="post" action="/login">
        <input name="email" type="email">
        <input name="password" type="password">
        <label>
            <input type="checkbox" name="remember-me">Запомни меня
        </label>

        <input type="submit" value="login">
    </form>
    <a href="https://github.com/login/oauth/authorize?client_id=${clientId}&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Foauth">
        Sign In GitHub
    </a>
    <a href="/register">
        Register
    </a>
</div>
</html>