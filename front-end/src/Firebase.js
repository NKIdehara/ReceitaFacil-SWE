// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getAuth } from "firebase/auth";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: process.env.FRONTEND_API_KEY,
  authDomain: process.env.FRONTEND_AUTH_DOMAIN,
  projectId: process.env.FRONTEND_PROJECT_ID,
  storageBucket: process.env.FRONTEND_STORAGE_BUCKET,
  messagingSenderId: process.env.FRONTEND_MESSAGING_SENDER_ID,
  appId: process.env.FRONTEND_APP_ID,
  measurementId: process.env.FRONTEND_MEASUREMENT_ID
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

// Initialize Firebase Authentication and get a reference to the service
export const auth = getAuth(app);

export var userUID = null;

export const user = {
  UID: null,
  tipoAcesso: 0,

  get getUID() {
    return this.UID;
  },

  setUID: function (UID) {
    this.UID = UID;
  },

  get isNull() {
    if(this.UID == null) {
      return true;
    }
    return false;
  }
}