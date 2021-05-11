<html>
<#import 'importFiles.ftl' as importFiles>
<head>
    <title>Paths</title>
    <@importFiles.files/>
    <script src="/js/path_buttons_configurer.js"></script>
</head>
<div class="container">

    <label for="from-input">From</label>
    <select id="from-input" name="from">
        <option value="-1">-</option>
        <#list cityList as city>
            <option value="${city.id}">${city.name}</option>
        </#list>
    </select>
    <label for="to-input">To</label>
    <select id="to-input" name="to">
        <option value="-1">-</option>
        <#list cityList as city>
            <option value="${city.id}">${city.name}</option>
        </#list>
    </select>

    <label for="dist">Distance</label>
    <input type="number" name="dist" id="dist">


    <input id="add-button" type="submit" class="btn btn-success" value="Add">

    <table id="path-table">
        <#list pathList as path>
            <tr>
                <td>${path.cityFromName}</td>
                <td>${path.cityToName}</td>
                <td>${path.distance}</td>
                <td><button data-id="${path.id}" class="btn btn-danger delete-button">Delete</button></td>
            </tr>
        </#list>
    </table>

</div>
</html>