import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getStorage } from "firebase/storage";

const firebaseConfig = {
    apiKey: process.env.REACT_APP_API_KEY,
    authDomain: process.env.REACT_APP_AUTH_DOMAIN,
    projectId: process.env.REACT_APP_PROJECT_ID,
    storageBucket: process.env.REACT_APP_STORAGE_BUCKET,
    messagingSenderId: process.env.REACT_APP_MESSAGING_SENDER_ID,
    appId: process.env.REACT_APP_APP_ID,
    measurementId: process.env.REACT_APP_MEASUREMENT_ID,
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
// console.log(firebaseConfig);

// Firebase Authentication
export const auth = getAuth(app);

// Firebase Storage
export const storage = getStorage(app);

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
    },

    setTipoAcesso: function (tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    },

    get getTipoAcesso() {
        return this.tipoAcesso;
    }
}