import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { If, Else, Then } from 'react-if';

export default function Usuarios() {
    const [usuarios, setUsuarios] = useState([]);
    useEffect( () => {
        loadUsuarios();
    }, []);
    const loadUsuarios = async() => {
        const result = await axios.get("http://localhost:8080/usuarios");
        // console.log(result.data);
        setUsuarios(result.data);
    }

    return (
        <div className="container">
            <div className="py-4 ">
                <table className="table table-hover shadow">
                <thead className="table-secondary">
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">e-mail</th>
                    <th scope="col">Tipo de Acesso</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        usuarios.map((usuario, idUsuario) => (
                            <tr>
                            <th scope="row" key={idUsuario}>{idUsuario+1}</th>
                            <td>{usuario.nome}</td>
                            <td>{usuario.email}</td>
                            <td>
                                <If condition={usuario.tipoAcesso == 1}>
                                    <Then>{"Administrador"}</Then>
                                    <Else><If condition={usuario.tipoAcesso == 2}>
                                        <Then>{"Usuário"}</Then>
                                        <Else>{"Visitante"}</Else>
                                    </If></Else>
                                </If>
                            </td>
                            </tr>
                        ))
                    }
                </tbody>
                </table>
            </div>

            <div className="float-end">
                <Link className="btn btn-outline-dark m-1" to="/addUsuario">Novo Usuário</Link>
                <Link className="btn btn-outline-dark m-1" to="/">Cancelar</Link>
            </div>
        </div>
    )
}
