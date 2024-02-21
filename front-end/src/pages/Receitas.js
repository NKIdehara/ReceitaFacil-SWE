import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { If } from 'react-if';
import axios from 'axios';
import { user } from '../Firebase';
import Spinner from '../layout/Spinner';
import { BACKEND } from '../App';

export default function Receitas() {
    const [receitas, setReceitas] = useState([]);
    useEffect( () => {
        if (user.getUID === 0) {
            loadReceitasALL();
        } else {
            loadReceitas();
        }
    }, []);

    const loadReceitasALL = async() => {
        const result = await axios.get(BACKEND.concat("/receitasALL"));
        setReceitas(result.data);
        setEspera(false);
    }
    const loadReceitas = async() => {
        const result = await axios.post(BACKEND.concat("/receitas"), user.getUID);
        setReceitas(result.data);
        setEspera(false);
    }

    async function deleteReceita(id) {
        setEspera(true);
        const result = await axios.post(BACKEND.concat("/apagarreceita"), id);
        const novaLista = receitas.filter(receita => receita.id !== id);
        setReceitas(novaLista);
        setEspera(false);
    }

    const [espera, setEspera] = useState(true);    

    if(!user.isNull) {
        return (
            <div className="container">
                <div className="py-4 ">

                    <table className="table table-hover shadow">
                    <thead className="table-secondary">
                        <tr>
                        <th scope="col">#</th>
                        <th scope="col">Foto</th>
                        <th scope="col">Data</th>
                        <th scope="col">Nome da Receita</th>
                        <th scope="col">Ingredientes</th>
                        <th scope="col">Preparo</th>
                        <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody className="text-start">
                        {
                            receitas.map((receita, idReceita) => (
                                <tr>
                                <th scope="row" key={idReceita}>{idReceita+1}</th>
                                <td><img src={receita.figura} style={{'maxWidth': '100px'}}/></td>
                                <td>{receita.dataReceita}</td>
                                <td>{receita.nome}</td>
                                <td style={{whiteSpace: "pre-wrap"}}>{receita.ingredientes}</td>
                                <td style={{whiteSpace: "pre-wrap"}}>{receita.preparo}</td>
                                <If condition={user.getUID !== 0}>
                                    <td><button type="button" class="btn btn-light" onClick={() => deleteReceita(receita.id)}>❌</button></td>
                                </If>
                                </tr>
                            ))
                        }
                    </tbody>
                    </table>
                    {espera && <Spinner />}                    
                </div>

                <div className="float-end">
                    <If condition={user.getUID !== 0}>
                        <Link className="btn btn-outline-dark m-1" to="/addReceita">Nova Receita</Link>
                    </If>
                    <Link className="btn btn-outline-dark m-1" to="/home">Cancelar</Link>
                </div>
            </div>
       )
    }
}