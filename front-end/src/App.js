import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import Receitas from './pages/Receitas';
import Usuarios from './pages/Usuarios';
import AddUsuario from './records/AddUsuario';
import AddReceita from './records/AddReceita';

function App() {
    return (
        <div className="App">
            <Router>
                <Navbar />

                <Routes>
                    <Route exact path="/" element={<Home />} />
                    <Route exact path="/receitas" element={<Receitas />} />
                    <Route exact path="/usuarios" element={<Usuarios />} />
                    <Route exact path="/addUsuario" element={<AddUsuario />} />
                    <Route exact path="/addReceita" element={<AddReceita />} />
                </Routes>
            </Router>
          
        </div>
    );
}

export default App;
