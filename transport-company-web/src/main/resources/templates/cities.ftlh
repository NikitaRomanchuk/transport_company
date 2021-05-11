<html>
<#import 'importFiles.ftl' as importFiles>
<head>
    <title>Cities</title>
    <@importFiles.files/>
    <script src="/js/city_buttons_configurer.js"></script>
</head>
<div class="container">

    <label for="name-input">Name</label><input id="name-input" name="name">
    <input id="add-button" type="submit" class="btn btn-success" value="Add">

    <table id="city-table">
        <#list cityList as city>
        <tr>
            <td>${city.name}</td>
            <td><button data-id="${city.id}" class="btn btn-danger delete-button">Delete</button></td>
        </tr>
        </#list>
    </table>

</div>
</html>