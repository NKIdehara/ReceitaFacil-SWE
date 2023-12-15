import { render } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";
import Receitas from "./pages/Receitas";


test("current user is active in sidebar", () => {
    render(
        <MemoryRouter initialEntries={["/receitas"]}>
            <Receitas />
        </MemoryRouter>
    );
    expectUserToBeActive(3);
});
