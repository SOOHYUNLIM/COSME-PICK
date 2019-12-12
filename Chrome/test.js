$(document).ready(function () {
    navigator.serviceWorker.register('firebase-messaging-sw.js')
    getClientToken()

    $(".wantedPrice").on('keyup', function () {
        inputNumberFormat(this)
    }).on('invalid',function () {
        this.setCustomValidity('원하는 가격을 입력해주세요!')
    })

    getCurrentTabUrl(function (url) {
        $.ajax({
            url: "http://localhost:8080/cosme-pick/check",
            type: "get",
            dataType: "json",
            data: {url: encodeURIComponent(url)}
        }).done(function (data) {
            rederURL(data)
            $(".waitPage").remove()
            $(".content").css("display","block")
            $(".button").on('click', function (e) {
                // e.preventDefault();
                var pickProduct = {title: data.title, price: $(".wantedPrice").val().replace(',',''), mid: "user00"}
                $.ajax({
                    url: "http://localhost:8080/cosme-pick/pick",
                    type: "post",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(pickProduct)
                }).done(function (data) {
                    alert('등록되었습니다!')
                    window.open('about:blank','_self').self.close()
                })
                return false
            })
        })
    })
})

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

function inputNumberFormat(obj) {
    obj.value = comma(uncomma(obj.value));
}

function getCurrentTabUrl(callback) {
    var queryInfo = {active: true, currentWindow: true}
    chrome.tabs.query(queryInfo, function (tabs) {
        var tab = tabs[0]
        var url = tab.url
        callback(url)
    })
}

function rederURL(data) {
    document.getElementById("title").textContent = data.title
    document.getElementById("productImg").src = data.img
}

function getClientToken() {
    var result = null
    // Initialize Firebase
    var config = {
        apiKey: "AIzaSyB5h1dtyUXyYroO4ZengUlSKCa93-WoRnU",
        authDomain: "jarvis-77f82.firebaseapp.com",
        databaseURL: "https://jarvis-77f82.firebaseio.com",
        projectId: "jarvis-77f82",
        storageBucket: "jarvis-77f82.appspot.com",
        messagingSenderId: "172501072688",
        appId: "1:172501072688:web:e1d67ee6f83f43711ba6f5",
        measurementId: "G-GRFQ9P114V"
    }
    firebase.initializeApp(config)
    const messaging = firebase.messaging()
    console.log(window.location.href)

    messaging.requestPermission()
        .then(function () {
            console.log("Have permission")
            return messaging.getToken()
        })
        .then(function (token) {
            console.log(token)
            result = token
        })
        .catch(function (arr) {
            console.log("Error Occured" + arr)
        })

    return result
}