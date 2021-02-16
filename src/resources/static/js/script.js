function send() {
  var time = +document.getElementById("time_restriction").value
  var views = +document.getElementById("views_restriction").value
  let object = {
    "code": document.getElementById("code_snippet").value,
    "time": time,
    "originalTime": time,
    "views": views,
    "hasViewRestriction": views > 0
  };
  let json = JSON.stringify(object);
  let xhr = new XMLHttpRequest();
  xhr.open("POST", '/api/code/new', false)
  xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
  xhr.send(json);
  if (xhr.status == 200) {
    alert("Success!");
  }
}