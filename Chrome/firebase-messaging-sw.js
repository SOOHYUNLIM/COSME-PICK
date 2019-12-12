
// Initialize Firebase
importScripts('https://www.gstatic.com/firebasejs/7.5.2/firebase-app.js')
importScripts('https://www.gstatic.com/firebasejs/7.5.2/firebase-messaging.js')

var config = {
  apiKey: "AIzaSyB5h1dtyUXyYroO4ZengUlSKCa93-WoRnU",
  authDomain: "jarvis-77f82.firebaseapp.com",
  databaseURL: "https://jarvis-77f82.firebaseio.com",
  projectId: "jarvis-77f82",
  storageBucket: "jarvis-77f82.appspot.com",
  messagingSenderId: "172501072688",
  appId: "1:172501072688:web:e1d67ee6f83f43711ba6f5",
  measurementId: "G-GRFQ9P114V"
};
firebase.initializeApp(config);

const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function(payload){

  const title = "Hello World";
  const options = {
    body: payload.data.status
  };

  return self.registration.showNotification(title,options);
});
