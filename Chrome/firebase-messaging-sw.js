
// Initialize Firebase
importScripts('https://www.gstatic.com/firebasejs/7.5.2/firebase-app.js')
importScripts('https://www.gstatic.com/firebasejs/7.5.2/firebase-messaging.js')

var config = {
 
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
