import React from 'react'
import { Link } from 'react-router-dom';

export default function Receitas() {
    return (
        <div className="container">
            <div>Receitas</div>
            <div className="float-end">
                <Link className="btn btn-outline-dark m-1" to="/">Cancelar</Link>
            </div>
        </div>
    )
}