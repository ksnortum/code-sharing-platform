<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Latest</title>
    <link href="/css/latest-code.css" rel="stylesheet">
</head>
<body>
    <div class=stage>
        <#list codes as codeContainer>
            <span class=date id="load_date">${codeContainer.date}</span>
            <pre id="code_snippet">${codeContainer.code}</pre>
            <br/>
        </#list>
    </div>
</body>
</html>