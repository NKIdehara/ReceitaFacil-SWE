import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Routes, Route, Outlet } from 'react-router-dom';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import Receitas from './pages/Receitas';
import Usuarios from './pages/Usuarios';
import AddUsuario from './records/AddUsuario';
import AddReceita from './records/AddReceita';
import Login, { userUID } from './pages/Login';
import { Else, If } from 'react-if';

function App() {
    const tipoAcesso = 0;
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/" element={<Login />} />
                    <Route element={
                        <>
                        <Navbar />
                        <Outlet />
                        </>
                    }>
                        <Route path="/home" element={<Home />} />
                        <Route path="/receitas" element={<Receitas />} />
                        <Route path="/usuarios" element={<Usuarios />} />
                        <Route path="/addUsuario" element={<AddUsuario />} />
                        <Route path="/addReceita" element={<AddReceita />} />
                    </Route>
                </Routes>
            </Router>
        </div>
    );
}

export default App;
