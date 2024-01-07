import React from 'react'
import infnet from '../resources/images/ic_infnet.png';
import { user } from '../Firebase';

export default function Home() {
    if(!user.isNull) {
        return (
            <div className="container-fluid">
                <div><img src={infnet} className="w-25 m-3" style={{'maxWidth': '200px'}} /></div>
                <div>
                    <h1>Instituto Infnet</h1>
                    <h2>Engenharia de Software</h2>
                </div>
                <div className="p-5">
                    <h3>Projeto de Bloco: Engenharia Disciplinada de Softwares</h3>
                    <div><a>Aluno: Nelson Kenji Idehara</a></div>
                    <div><a>Professor: Armênio Torres Santiago Cardoso</a></div>
                </div>
            </div>
        )
    }
}