//https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/#non-web-application-flow
var clientId = 'b372136ea22';
var authorizationUrlBase = 'https://github.com/login/oauth/authorize';
var redirectUri = 'http://localhost:8125/oauth-redirect-handler.html';
var scope = 'repo';

function startOauth() {
    // generate a pseudo-random number for state
    var rand = Math.random();
    var dateTime = new Date().getTime();
    var state = rand * dateTime;
    var url = authorizationUrlBase;
    url += '?response_type=token'
        + '&redirect_uri=' + encodeURIComponent(redirectUri)
        + '&client_id=' + encodeURIComponent(clientId)
        + '&scope=' + encodeURIComponent(scope)
        + '&state=' + encodeURIComponent(state);
    window.location.replace(url);
}

//update page if already have responce from secured api
let url = new URL(location.href);
let searchParams = new URLSearchParams(url.search);
var repos = searchParams.getAll('repo');
var div = document.getElementById('repos-holder');
for (var repo of repos) {
    var paragraph = document.createElement("p");
    paragraph.textContent = repo;
    div.appendChild(paragraph);
}