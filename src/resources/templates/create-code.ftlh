<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Create</title>
    <link href="/css/create-code.css" rel="stylesheet">
</head>
<body>
    <script src="/js/script.js"></script>
    <div class=form>
        <form>
            <textarea id="code_snippet">// Write your code here</textarea>
            <br/>
            Time restriction: <input id="time_restriction" type="number"/>
            <br/>
            Views restriction: <input id="views_restriction" type="number"/>
        </form>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </div>
</body>
</html>