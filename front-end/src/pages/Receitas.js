import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { user } from '../Firebase';
import Spinner from '../layout/Spinner';

export default function Receitas() {
    const [receitas, setReceitas] = useState([]);
    useEffect( () => {
        loadReceitas();
    }, []);

    const loadReceitas = async() => {
        const result = await axios.post("http://localhost:8080/receitas", user.getUID);
        setReceitas(result.data);
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
                                </tr>
                            ))
                        }
                    </tbody>
                    </table>
                    {espera && <Spinner />}                    
                </div>

                <div className="float-end">
                    <Link className="btn btn-outline-dark m-1" to="/addReceita">Nova Receita</Link>
                    <Link className="btn btn-outline-dark m-1" to="/home">Cancelar</Link>
                </div>
            </div>
       )
    }
}