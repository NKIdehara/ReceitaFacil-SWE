import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import Home from "./pages/Home";

test('verifica se a tela inicial está renderizada corretamente', () => {
    render(<Home />, {wrapper: BrowserRouter})

    expect(screen.getByText(/infnet/i)).toBeInTheDocument()
  
});