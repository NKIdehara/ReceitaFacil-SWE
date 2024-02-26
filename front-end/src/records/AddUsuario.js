import * as bootstrap from 'bootstrap';
import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { BACKEND } from '../App';

    export default function AddUsuario() {

        let navigate = useNavigate();

        const [message, setMessage] = useState('')

        const [usuario, setUsuario] = useState({
            nome: "",
            email: "",
            endereco: "",
            tipoAcesso: 0,
            senha: ""
        });

        const {nome, email, endereco, tipoAcesso, senha} = usuario;

        const onInputChange = (e) => {
            setUsuario({...usuario, [e.target.name]:e.target.value});
        }

        const validateEmail = (email) => {
            return email.match(
              /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            );
        };

        const onSubmit = async (e) => {
            e.preventDefault();
            if (!validateEmail(usuario.email)) {
                setMessage("email inválido!");
                let toast = new bootstrap.Toast(document.getElementById('Toast'));
                toast.show();                
            } else if (usuario.senha.length < 6) {
                setMessage("Senha deve possuir mais de 6 caracteres!");
                let toast = new bootstrap.Toast(document.getElementById('Toast'));
                toast.show();
            } else {
                try {
                    await axios.post(BACKEND.concat("/novousuario"), usuario);
                } catch(err) {
                    setMessage(err.message);
                    let toast = new bootstrap.Toast(document.getElementById('Toast'));
                    toast.show();
                }
                navigate("/usuarios")    
            }
        }

    let toast = new bootstrap.Toast(document.getElementById('Toast'));
    
    return (
        <div className="container">
            <div className="py-4">
                <div className="border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-0">Cadastro de Usuários</h2>
                    <form className="my-4" onSubmit={(e) => onSubmit(e)}>
                        <div className="form-group row my-2">
                                <label htmlFor="nome" className="col-sm-2 col-form-label">Nome:</label>
                                <div className="col-sm-10">
                                    <input type={"text"} className="form-control" required placeholder="Digite o nome" name="nome" value={nome} onChange={(e) => onInputChange(e)} />
                                </div>
                        </div>
                        <div className="form-group row my-2">
                            <label htmlFor="email" className="col-sm-2 col-form-label">e-mail:</label>
                            <div className="col-sm-10">
                                <input type={"text"} className="form-control" required placeholder="Digite o e-mail" name="email" value={email} onChange={(e) => onInputChange(e)} />
                            </div>
                        </div>
                        <div className="form-group row my-2">
                                <label htmlFor="endereco" className="col-sm-2 col-form-label">Endereço:</label>
                                <div className="col-sm-10">
                                    <input type={"text"} className="form-control" required placeholder="Digite o endereço" name="endereco" value={endereco} onChange={(e) => onInputChange(e)} />
                                </div>
                        </div>
                        <div className="form-group row my-2">
                            <label htmlFor="tipoAcesso" className="col-sm-2 col-form-label">Tipo de Acesso:</label>
                            <div className="col-sm-10">
                                <select className="form-select" aria-label="Tipo de Acesso" name="tipoAcesso" value={tipoAcesso} onChange={(e) => onInputChange(e)} >
                                    <option value="0">Selecione o tipo de acesso</option>
                                    <option value="1">Administrador</option>
                                    <option value="2">Usuário</option>
                                </select>                        
                            </div>
                        </div>
                        <div className="form-group row my-2">
                                <label htmlFor="senha" className="col-sm-2 col-form-label">Senha:</label>
                                <div className="col-sm-10">
                                    <input type={"password"} className="form-control" required placeholder="Digite a senha" name="senha" value={senha} onChange={(e) => onInputChange(e)} />
                                </div>
                        </div>

                        <div id="Toast" className="toast align-items-center text-bg-danger border-0 position-absolute top-50 start-50 translate-middle" role="alert" aria-live="assertive" aria-atomic="true">
                            <div className="d-flex">
                                <div className="toast-body">{message}</div>
                                <button type="button" className="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                            </div>
                        </div>

                        <button type="submit" className="btn btn-outline-primary">Cadastrar</button>
                    </form>
                </div>
                <div className="float-end my-4">
                    <Link className="btn btn-outline-dark m-1" to="/usuarios">Cancelar</Link>
                </div>
            </div>
        </div>
    )
}