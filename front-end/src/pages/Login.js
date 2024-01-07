import * as bootstrap from 'bootstrap';
import React, { useState } from 'react';
import ic_cook from '../resources/images/ic_cook.png';
import { auth, user } from '../Firebase';
import { createUserWithEmailAndPassword, signInWithEmailAndPassword } from 'firebase/auth';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [message, setMessage] = useState('')

    const state = { button: 0 };

    const entrar = (e) => {
        e.preventDefault();
        if (state.button === 1) {
            signInWithEmailAndPassword(auth, email, senha)
                .then((userCredential) => {
                    user.setUID(userCredential.user.uid);
                    navigate("/home");
                })
                .catch((error) => {
                    setMessage(error.message);
                    toast.show();
                });
        }
        if (state.button === 2) {
            createUserWithEmailAndPassword(auth, email, senha)
                .then((userCredential) => {
                    user.setUID(userCredential.user.uid);
                    navigate("/home");
                })
                .catch((error) => {
                    setMessage(error.message);
                    toast.show();
                });
        }
    }

    const toast = new bootstrap.Toast(document.getElementById('Toast'));
    
    return (
        <div className="container-fluid">
            <div className="my-4">
                <h1>Receita Fácil</h1>
                <img src={ic_cook} className="w-25 m-1" style={{'maxWidth': '300px'}} />
            </div>

            <div className="container">
                <form onSubmit={entrar}>
                    <div className="form-group row my-3">
                        <div className="col"></div>
                        <label for="usuario" class="col-sm-1 col-form-label">Usuário:</label>
                        <div className="col-sm">
                            <input type={"email"} className="form-control" required placeholder="e-mail" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                        </div>
                        <div className="col"></div>
                    </div>
                    <div className="form-group row my-3">
                        <div className="col"></div>
                        <label for="senha" class="col-sm-1 col-form-label">Senha:</label>
                        <div className="col-sm">
                            <input type={"password"} className="form-control" required placeholder="senha" name="senha" value={senha} onChange={(e) => setSenha(e.target.value)} />
                        </div>
                        <div className="col"></div>
                </div>
                    <button type="submit" className="btn btn-outline-primary m-3 btn-lg" onClick={() => (state.button = 1)} >Login</button>
                    <button type="submit" className="btn btn-outline-primary m-3 btn-lg" onClick={() => (state.button = 2)} >Novo</button>
                </form>


                <div id="Toast" className="toast align-items-center text-bg-danger border-0 position-absolute top-50 start-50 translate-middle" role="alert" aria-live="assertive" aria-atomic="true">
                    <div className="d-flex">
                        <div className="toast-body">
                            {message}
                        </div>
                        <button type="button" className="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                </div>

            </div>
        </div>
    )
}

export default Login