var clientId = 'b3721e1fb';
var clientSecret = 'eaff6141513e4scafc71543aabf5c';
var accessTokenUrl = "https://github.com/login/oauth/access_token";
var redirectUrl = 'http://localhost:8125/oauth-redirect-handler.html';
var repoListUrl = "https://api.github.com/user/repos"

let url = new URL(location.href);
let searchParams = new URLSearchParams(url.search);
var code = searchParams.get('code');
var state = searchParams.get('state');

if (code != null && state != null) {
    var requestHeaders = {
        'Content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
    };
    var params = {
        "client_id": clientId,
        "client_secret": clientSecret,
        "code": code,
        "state": state,
        "redirect_uri": redirectUrl
    };
    //exchange code to token
    post(accessTokenUrl, requestHeaders, toBody(params),
        function (text) {
            if (text == undefined || text == null || text == "") {
                console.log("ERROR: response is empty");
                return;
            }
            var token = JSON.parse(text).access_token;
            if (token == null || token == undefined) {
                console.log("ERROR: code exchange to token failed, response is " + text);
                return;
            }
            //call to secured github api rest
            get(repoListUrl, { "Authorization": "token " + token },
                function (text) {
                    if (text == undefined || text == null || text == "") {
                        console.log("ERROR: response is empty");
                        return;
                    }
                    var repos = JSON.parse(text);
                    if (!Array.isArray(repos)) {
                        console.log("ERROR: secured API call failed, response is " + text);
                        return;
                    }
                    var index = "/";
                    if (repos.length != 0) {
                        index += "?";
                        index += repos.map(repo => repo.name)
                            .map(name => "repo=" + name)
                            .reduce((a, b) => a + "&" + b)
                    }
                    window.location.replace(index);

                }
            );
        }
    );

}

function toBody(params) {
    var body = "";
    for (var key of Object.keys(params)) {
        if (body != "") {
            body += "&";
        }
        body += key + "=" + params[key];
    }
    return body;
}

function post(url, headers, body, onReceive) {
    request("POST", url, headers, body, onReceive)
}

function get(url, headers, onReceive) {
    request("GET", url, headers, null, onReceive)
}

function request(protocol, url, headers, body, onReceive) {
    var xhr = new XMLHttpRequest();
    xhr.open(protocol, url, true);
    for (var key of Object.keys(headers)) {
        xhr.setRequestHeader(key, headers[key]);
    }
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            onReceive(this.responseText);
        }
    };
    xhr.send(body);
}