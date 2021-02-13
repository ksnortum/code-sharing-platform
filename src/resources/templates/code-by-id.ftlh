<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Code</title>
    <link href="/css/code-by-id.css" rel="stylesheet">
    <link rel="stylesheet" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <div class=stage>
        <span class=date id="load_date">${codeContainer.date}</span>
        <pre id="code_snippet"><code>${codeContainer.code}</code></pre>
    </div>
</body>
</html>