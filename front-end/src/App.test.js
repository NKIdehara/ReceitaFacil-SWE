import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import Home from "./pages/Home";
import Receitas from "./pages/Receitas";
import Usuarios from "./pages/Usuarios";

// test('verifica se a tela inicial está renderizada corretamente', () => {
//     render(<Home />, {wrapper: BrowserRouter})
//     expect(screen.getByText(/infnet/i)).toBeInTheDocument()  
// });

// test('verifica se a tela de listagem de receitas está renderizada corretamente', () => {
//     render(<Receitas />, {wrapper: BrowserRouter})
//     expect(screen.getByText(/nome da receita/i)).toBeInTheDocument()  
// });
