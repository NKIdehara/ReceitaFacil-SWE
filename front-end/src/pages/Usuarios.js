import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { If, Else, Then } from 'react-if';
import { user } from '../Firebase';
import Spinner from '../layout/Spinner';
import { BACKEND } from '../App';

export default function Usuarios() {
    const [usuarios, setUsuarios] = useState([]);
    useEffect( () => {
        loadUsuarios();
    }, []);
    const loadUsuarios = async() => {
        const result = await axios.get(BACKEND.concat("/usuarios"));
        setUsuarios(result.data);
        setEspera(false);
    }

    async function deleteUsuario(uid) {
        setEspera(true);
        const result = await axios.post(BACKEND.concat("/apagausuario"), uid);
        const novaLista = usuarios.filter(usuario => usuario.uid !== uid);
        setUsuarios(novaLista);
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
                        <th scope="col">Nome</th>
                        <th scope="col">e-mail</th>
                        <th scope="col">Endereço</th>
                        <th scope="col">Tipo de Acesso</th>
                        <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody className="text-start">
                        {
                            usuarios.map((usuario, idUsuario) => (
                                <tr>
                                <th scope="row" key={idUsuario}>{idUsuario+1}</th>
                                <td>{usuario.nome}</td>
                                <td>{usuario.email}</td>
                                <td>{usuario.endereco}</td>
                                <td className="text-center">
                                    <If condition={usuario.tipoAcesso === 1}>
                                        <Then>{"Administrador"}</Then>
                                        <Else><If condition={usuario.tipoAcesso === 2}>
                                            <Then>{"Usuário"}</Then>
                                            <Else>{"Visitante"}</Else>
                                        </If></Else>
                                    </If>
                                </td>
                                <If condition={(user.tipoAcesso === 1) && (user.UID !== usuario.uid)}>
                                    <Then><td><button type="button" class="btn btn-light" onClick={() => deleteUsuario(usuario.uid)}>❌</button></td></Then>
                                    <Else><td></td></Else>
                                </If>
                                </tr>
                            ))
                        }
                    </tbody>
                    </table>
                    {espera && <Spinner />}
                 </div>

                <div className="float-end">
                    {/* <Link className="btn btn-outline-dark m-1" to="/addUsuario">Novo Usuário</Link> */}
                    <Link className="btn btn-outline-dark m-1" to="/home">Cancelar</Link>
                </div>
            </div>
        )
    }
}