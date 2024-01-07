import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { BACKEND } from '../App';

    export default function AddUsuario() {

        let navigate = useNavigate();

        const [usuario, setUsuario] = useState({
            nome: "",
            email: "",
            tipoAcesso: 3
        });

        const {nome, email, tipoAcesso} = usuario;

        const onInputChange = (e) => {
            setUsuario({...usuario, [e.target.name]:e.target.value});
        }

        const onSubmit = async (e) => {
            e.preventDefault();
            await axios.post("https://receitafacil-backend.azurewebsites.net/usuario", usuario);
            navigate("/usuarios")
        }

    return (
        <div className="container">
            <div className="py-4">
                <div className="border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-0">Cadastro de Usuários</h2>
                    <form className="my-4" onSubmit={(e) => onSubmit(e)}>
                        <div className="form-group row my-2">
                                <label for="nome" class="col-sm-2 col-form-label">Nome:</label>
                                <div className="col-sm-10">
                                    <input type={"text"} className="form-control" required placeholder="Digite o nome" name="nome" value={nome} onChange={(e) => onInputChange(e)} />
                                </div>
                        </div>
                        <div className="form-group row my-2">
                            <label for="email" className="col-sm-2 col-form-label">e-mail:</label>
                            <div className="col-sm-10">
                                <input type={"text"} className="form-control" required placeholder="Digite o e-mail" name="email" value={email} onChange={(e) => onInputChange(e)} />
                            </div>
                        </div>
                        <div className="form-group row my-2">
                            <label for="tipoAcesso" className="col-sm-2 col-form-label">Tipo de Acesso:</label>
                            <div className="col-sm-10">
                                <select className="form-select" aria-label="Tipo de Acesso" name="tipoAcesso" value="" onChange={(e) => onInputChange(e)} >
                                    <option selected>Selecione o tipo de acesso</option>
                                    <option value="1">Administrador</option>
                                    <option value="2">Usuário</option>
                                    <option value="3">Visitante</option>
                                </select>                        
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
