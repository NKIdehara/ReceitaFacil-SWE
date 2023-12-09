import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

    export default function AddReceita() {

        let navigate = useNavigate();

        const hoje = new Date();

        const [receita, setReceita] = useState({
            nome: "",
            preparo: "",
            dataReceita: hoje
        });

        const {nome, preparo, dataReceita} = receita;

        const onInputChange = (e) => {
            setReceita({...receita, [e.target.name]:e.target.value});
        }

        const onSubmit = async (e) => {
            e.preventDefault();
            await axios.post("http://localhost:8080/receita", receita);
            navigate("/receitas")
        }

    return (
        <div className="container">
            <div className="py-4">
                <div className="border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-0">Cadastro de Receitas</h2>
                    <form className="my-4" onSubmit={(e) => onSubmit(e)}>
                    <div className="form-group row my-2">
                                <label for="nome" class="col-sm-2 col-form-label">Nome:</label>
                                <div className="col-sm-10">
                                    <input type={"text"} className="form-control" required placeholder="Nome da receita" name="nome" value={nome} onChange={(e) => onInputChange(e)} />
                                </div>
                        </div>
                        <div className="form-group row my-2">
                                <label for="nome" class="col-sm-2 col-form-label">Preparo:</label>
                                <div className="col-sm-10">
                                    <input type={"text"} className="form-control" required placeholder="Preparo da receita" name="preparo" value={preparo} onChange={(e) => onInputChange(e)} />
                                </div>
                        </div>
                        <button type="submit" className="btn btn-outline-primary">Cadastrar</button>
                    </form>
                </div>
                <div className="float-end my-4">
                    <Link className="btn btn-outline-dark m-1" to="/receitas">Cancelar</Link>
                </div>
            </div>
        </div>
    )
}
