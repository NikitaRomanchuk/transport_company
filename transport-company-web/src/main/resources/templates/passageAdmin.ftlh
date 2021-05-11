<html>
<#import 'importFiles.ftl' as importFiles>
<head>
    <title>Paths</title>
    <@importFiles.files/>
    <script src="/js/passage_admin_buttons_configurer.js"></script>
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

    <label for="cost">Distance</label>
    <input type="number" name="cost" id="cost">
    <input type="date" id="start">

    <input id="add-button" type="submit" class="btn btn-success" value="Add">

    <table id="passage-table">
        <#list passagesList as passage>
            <tr>
                <td>${passage.cityFromName}</td>
                <td>${passage.cityToName}</td>
                <td>${passage.cost} руб</td>
                <td>${passage.start}</td>
                <td><button data-id="${passage.id}" class="btn btn-danger delete-button">Delete</button></td>
            </tr>
        </#list>
    </table>

</div>
</html>