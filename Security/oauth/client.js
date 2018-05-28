https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/#non-web-application-flow

var clientId = 'b372136ea22e0f61b';
var authorizationUrlBase = 'https://github.com/login/oauth/authorize';
var redirectUri = 'http://localhost';
var scope = 'repo';
var state;
function startOauth() {
    // generate a pseudo-random number for state
    var rand = Math.random();
    var dateTime = new Date().getTime();
    state = rand * dateTime;
    var url = authorizationUrlBase;
    url += '?response_type=token'
        + '&redirect_uri=' + encodeURIComponent(redirectUri)
        + '&client_id=' + encodeURIComponent(clientId)
        + '&scope=' + encodeURIComponent(scope)
        + '&state=' + encodeURIComponent(state);
    window.location.replace(url);
}
function fillInRepos() {
    let url = new URL(location.href);
    let searchParams = new URLSearchParams(url.search);
    var code = searchParams.get('code');
    var state = searchParams.get('state');
    if (code == null || state == null) {
        return;
    }

    console.log();
    var div = document.getElementById('repos-holder');
    div.textContent = "Hello";
}
fillInRepos();