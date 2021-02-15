<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Latest</title>
    <link href="/css/latest-code.css" rel="stylesheet">
    <link rel="stylesheet" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <div class=stage>
        <#list codes as codeContainer>
            <span class=date id="load_date">${codeContainer.date}</span>
            <#if codeContainer.views gt 0>
                <br/>
                <span>${codeContainer.remainingViews()} more views allowed</span>
            </#if>
            <#if codeContainer.time gt 0>
                <br/>
                <span>The code will be available for ${codeContainer.remainingTime()} seconds</span>
            </#if>
            <pre id="code_snippet"><code>${codeContainer.code}</code></pre>
        </#list>
    </div>
</body>
</html>