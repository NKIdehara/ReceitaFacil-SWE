import React from 'react';
import logo from '../resources/images/ic_cook.png';
import { Link } from 'react-router-dom';
import { If, Then } from 'react-if';
import { BsFillHouseFill } from "react-icons/bs";
import { BiExit } from "react-icons/bi";
import { user } from '../Firebase';

export default function Navbar() {
    if(!user.isNull) {
        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                    <div className="container-fluid">
                        <div className="float-start">
                            <div><p className="navbar-brand">Receita Fácil</p></div>
                            <div><img alt="" src={logo} className="w-25" /></div>
                        </div>                    
                        <div className="float-end">
                            <Link className="btn btn-outline-light m-2 fa-4x" to="/home"><BsFillHouseFill /> Início</Link>
                            <Link className="btn btn-outline-light m-2" to="/receitas">Receitas</Link>
                            <If condition={user.getTipoAcesso === 1}><Then>
                                <Link className="btn btn-outline-light m-2" to="/usuarios">Usuários</Link>
                            </Then></If>
                            <Link className="btn btn-outline-light m-2" to="/logout"><BiExit /> Sair</Link>
                        </div>
                    </div>
                </nav>      
            </div>
        )    
    }
}