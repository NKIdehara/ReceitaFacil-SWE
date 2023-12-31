import React from 'react';
import logo from '../resources/images/ic_cook.png';
import { Link } from 'react-router-dom';
import { BsFillHouseFill } from "react-icons/bs";
import { BiExit } from "react-icons/bi";
import { userUID } from '../pages/Login';

export default function Navbar() {
    if(userUID !== null) {
        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                    <div className="container-fluid">
                        <div className="float-start">
                            <div><a className="navbar-brand">Receita Fácil</a></div>
                            <div><img src={logo} className="w-25" /></div>
                        </div>                    
                        <div className="float-end">
                            <Link className="btn btn-outline-light m-2 fa-4x" to="/home"><BsFillHouseFill /> Início</Link>
                            <Link className="btn btn-outline-light m-2" to="/receitas">Receitas</Link>
                            <Link className="btn btn-outline-light m-2" to="/usuarios">Usuários</Link>
                            <Link className="btn btn-outline-light m-2" to="/"><BiExit /> Sair</Link>
                        </div>
                    </div>
                </nav>      
            </div>
        )    
    }
}