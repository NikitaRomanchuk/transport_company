<html>
<#import 'importFiles.ftl' as importFiles>
<head>
    <title>Passage</title>
    <@importFiles.files/>
    <script src="/js/ticket_button_configurer.js"></script>
</head>
<div class="container">
    <p>${passage.cityFromName}</p>
    <p>${passage.cityToName}</p>
    <p>${passage.start}</p>
    <p>${passage.cost} руб</p>
    <h2>Path:</h2>
    <#list cities as city>
        <p>${city.name}</p>
    </#list>
    <button id="book-btn" data-id="${passage.id}" class="btn">Book</button>
    <button id="pay-btn" data-id="${passage.id}" class="btn disabled">Pay</button>
</div>
</html>