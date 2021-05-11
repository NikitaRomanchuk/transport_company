<html>
<#import 'importFiles.ftl' as importFiles>
<#import 'spring.ftl' as spring>
<head>
    <@importFiles.files/>
    <title>Tickets</title>
    <script src="/js/passages_configurer.js"></script>
</head>
<div class="container">
    <label for="from">From</label>
    <select id="from" name="from">
        <option value="-1">-</option>
        <#list cityList as city>
            <option value="${city.id}">${city.name}</option>
        </#list>
    </select>
    <label for="to">To</label>
    <select id="to" name="to">
        <option value="-1">-</option>
        <#list cityList as city>
            <option value="${city.id}">${city.name}</option>
        </#list>
    </select>
    <button class="btn btn-success" id="request-button">Find</button>
    <table id="passage-table">

    </table>
</div>
</html>